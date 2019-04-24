package com.app.smartganado.smart_ganado.view;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.TextInputEditText;
import android.support.v4.content.FileProvider;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

import com.app.smartganado.smart_ganado.R;
import com.app.smartganado.smart_ganado.model.dao.UserAppDAO;
import com.app.smartganado.smart_ganado.model.vo.UserApp;
import com.app.smartganado.smart_ganado.utilities.SHA512;
import com.app.smartganado.smart_ganado.utilities.Utilities;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Date;

@SuppressWarnings("all")
public class NewUserActivity extends AppCompatActivity {
    private UserApp userApp;
    private TextInputEditText editTEmail, editTPassword, editTPhone, editTName;
    private CheckBox checkBoxAdm, checkBoxEmp;
    private ImageButton buttonCamera;
    private Button  buttomCamera, registrar;
    private ImageView imageUser;
    private FloatingActionButton FABeditar;

    private final int SELECT_PICTURE = 10;
    static final int REQUEST_TAKE_PHOTO = 1;
    String mCurrentPhotoPath;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_user);

        editTEmail = findViewById(R.id.editTEmail);
        editTPassword = findViewById(R.id.editTPassword);
        editTPhone = findViewById(R.id.editTPhone);
        editTName = findViewById(R.id.editTName);
        buttonCamera = findViewById(R.id.buttonCamera);
        imageUser = findViewById(R.id.imageUser);
        checkBoxAdm = findViewById(R.id.checkBoxAdm);
        checkBoxEmp = findViewById(R.id.checkBoxEmp);
        FABeditar = findViewById(R.id.FABEditar);
        registrar = findViewById(R.id.Registrar);

        checkBoxEmp.setEnabled(false);//Only administrators

        // Edit and view User
        userApp = (UserApp) getIntent().getSerializableExtra("Info");
        if (getIntent().getSerializableExtra("Info") == null) {
            Log.i("server", "Crear");
            FABeditar.setVisibility(View.GONE);
        } else {

            Log.i("server", "Editando");
            editTEmail.setText(String.valueOf(userApp.getEmail()));
            editTPassword.setText(String.valueOf(userApp.getPassword()));
            editTPhone.setText(String.valueOf(userApp.getPhone()));
            editTName.setText(String.valueOf(userApp.getName()));
            imageUser.setImageBitmap(Utilities.byteToBitmap(userApp.getPhoto()));


            editTEmail.setEnabled(false);
            editTName.setEnabled(false);
            editTPassword.setEnabled(false);
            editTPhone.setEnabled(false);
            checkBoxAdm.setEnabled(false);
            checkBoxEmp.setEnabled(false);try{
            buttomCamera.setEnabled(false);}catch (Exception e){}
            registrar.setVisibility(View.GONE);
        }

    }

    public void onNewUser(View view) {

        String name = editTName.getText().toString();
        String phone = editTPhone.getText().toString();
        String email = editTEmail.getText().toString();
        String password = editTPassword.getText().toString();

        if (!name.isEmpty() && !phone.isEmpty() && !email.isEmpty() && !password.isEmpty() && (checkBoxAdm.isChecked() || checkBoxEmp.isChecked())) {
            UserApp user = new UserApp();

            user.setName(name);
            user.setEmail(email);
            user.setPhone(Long.parseLong(phone));
            user.setPassword(SHA512.encrypt(password));//Encoding password using SHA512
            user.setPhoto(Utilities.imageViewToByte(imageUser));
            user.setIdRol(checkBoxAdm.isChecked() ? 1 : 2);

            UserAppDAO.insertUser(getApplication(), user);//Insertion in db
        } else
            Toast.makeText(getApplicationContext(), "Debes llenar todos los campos", Toast.LENGTH_SHORT).show();
    }

    public void menu(View view) {
        final CharSequence[] options = {"Tomar foto", "Elegir de galeria", "Cancelar"};
        final AlertDialog.Builder builder = new AlertDialog.Builder(NewUserActivity.this);
        builder.setTitle("Elige una opción");
        builder.setItems(options, new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int seleccion) {

                if (options[seleccion] == "Tomar foto") {
                    takePhoto();
                } else if (options[seleccion] == "Elegir de galeria") {
                    Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                    intent.setType("image/");
                    startActivityForResult(intent.createChooser(intent, "Selecciona app de imagen"), SELECT_PICTURE);
                } else if (options[seleccion] == "Cancelar") {
                    dialog.dismiss();
                }
            }
        });

        builder.show();
    }

    public void takePhoto() {
        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        // Ensure that there's a camera activity to handle the intent
        if (takePictureIntent.resolveActivity(getPackageManager()) != null) {
            // Create the File where the photo should go
            File photoFile = null;
            try {
                photoFile = createImageFile();
            } catch (IOException ex) {
                // Error occurred while creating the File
            }
            // Continue only if the File was successfully created
            if (photoFile != null) {
                Uri photoURI = FileProvider.getUriForFile(this, "com.example.android.fileprovider", photoFile);
                takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT, photoURI.toString());
                startActivityForResult(takePictureIntent, REQUEST_TAKE_PHOTO);
            }
        }
    }

    private File createImageFile() throws IOException {
        // Create an image file name
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        String imageFileName = "Backup_" + timeStamp + "_";
        File storageDir = getExternalFilesDir(Environment.DIRECTORY_PICTURES);
        File image = File.createTempFile(imageFileName, ".jpg", storageDir);

        // Save a file: path for use with ACTION_VIEW intents
        mCurrentPhotoPath = image.getAbsolutePath();
        return image;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == RESULT_OK) {
            if (requestCode == REQUEST_TAKE_PHOTO) {
                //Switch (requestCode)
                //Case REQUEST_TAKE_PHOTO:
                Bundle extras = data.getExtras();
                Bitmap imageBitmap = (Bitmap) extras.get("data");
                imageUser.setImageBitmap(imageBitmap);

            } else {
                //Case SELECT_PICTURE:
                Uri path = data.getData();
                InputStream inputStream;
                try {
                    inputStream = getContentResolver().openInputStream(path);
                    Bitmap bitmap = BitmapFactory.decodeStream(inputStream);
                    imageUser.setImageBitmap(bitmap);

                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                    Toast.makeText(this, "No se pudo abrir la imagen", Toast.LENGTH_LONG).show();
                }
                imageUser.setImageURI(path);
            }
        }
    }
    public void Editar(View view) {
        registrar = findViewById(R.id.Registrar);
        FloatingActionButton floatBtEdit = findViewById(R.id.FABEditar);
        floatBtEdit.setVisibility(View.GONE);
        registrar.setVisibility(View.VISIBLE);
        editTPhone.setEnabled(true);
        editTPassword.setEnabled(true);
        editTName.setEnabled(true);
        editTEmail.setEnabled(true);
        checkBoxEmp.setEnabled(true);
        checkBoxAdm.setEnabled(true);
        registrar.setEnabled(true);
    }

}