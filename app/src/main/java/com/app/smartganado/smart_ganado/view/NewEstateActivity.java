package com.app.smartganado.smart_ganado.view;

import android.Manifest;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.design.widget.Snackbar;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v4.content.FileProvider;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import java.text.SimpleDateFormat;
import com.app.smartganado.smart_ganado.R;
import com.app.smartganado.smart_ganado.model.vo.Estate;
import com.app.smartganado.smart_ganado.remote.APIService;
import com.app.smartganado.smart_ganado.remote.APIUtils;
import com.app.smartganado.smart_ganado.view.adapter.Utilities;
import com.google.gson.Gson;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Date;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class NewEstateActivity extends AppCompatActivity {

    EditText editTNombre, editTArea, editTUbic;
    Button estateButton,estateimgButton;
    TextView a;
    String jsonEstate;
    Integer choose;
    ImageView img;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_estate);

        editTNombre = (EditText) findViewById(R.id.Nombre_Finca);
        editTArea = (EditText) findViewById(R.id.Tamaño_Finca);
        editTUbic = (EditText) findViewById(R.id.Direccion_Finca);
        img =(ImageView) findViewById(R.id.estateImageView);
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.CAMERA}, 1000);
        }

        Bundle estateBundle= getIntent().getExtras();

        if (estateBundle!=null) {
            choose=Integer.valueOf(estateBundle.getString("choose"));
            menu(choose, estateBundle);

        }


    }

    public void onInsertEstate(View view) {
        Estate estate;//aqui lee de los componentes

        switch (choose){
            case 1:
                estate = new Estate();
                estate.setName(editTNombre.getText().toString());
                estate.setArea(Double.valueOf(editTArea.getText().toString()));
                estate.setLocation(editTUbic.getText().toString());

                //Mandar la solicitud al servidor de actualizar
                break;
            case 2:
                Intent newEstateIntent =new Intent(this, ViewCattleActivity.class);
                this.startActivity(newEstateIntent);
                break;
            case 3:
                estate = new Estate();
                estate.setName(editTNombre.getText().toString());
                estate.setArea(Double.valueOf(editTArea.getText().toString()));
                estate.setLocation(editTUbic.getText().toString());
                //Manda la solicitud al servidor de agregar
        }
    }
    private void menu(Integer choose, Bundle estateBundle){
        jsonEstate = estateBundle.getString("Estate");
        Estate newEstate= new Gson().fromJson(jsonEstate,Estate.class);
        switch (choose){
            case 1:

                editTNombre.setText(newEstate.getName());
                editTArea.setText(String.valueOf(newEstate.getArea()));
                editTUbic.setText(newEstate.getLocation());
                break;
            case 2:
                editTNombre.setText(newEstate.getName());
                editTNombre.setEnabled(false);
                editTArea.setText(String.valueOf(newEstate.getArea()));
                editTArea.setEnabled(false);
                editTUbic.setText(newEstate.getLocation());
                editTUbic.setEnabled(false);
                estateButton= (Button)  findViewById(R.id.Save_Finca);
                estateimgButton= (Button) findViewById(R.id.imgButton);
                estateimgButton.setVisibility(View.INVISIBLE);
                estateButton.setText("Ver ganado");


                break;


        }

    }

    static final int REQUEST_IMAGE_CAPTURE = 1;
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        if (resultCode == RESULT_OK) {
            if(requestCode == REQUEST_TAKE_PHOTO){
                //switch (requestCode) {
                //    case REQUEST_TAKE_PHOTO:
                Bundle extras = data.getExtras();
                Bitmap imageBitmap = (Bitmap) extras.get("data");
                img.setImageBitmap(imageBitmap);
                //       break;
            }else{
                //    case SELECT_PICTURE:
                Uri path = data.getData();
                InputStream inputStream;
                try {
                    inputStream = getContentResolver().openInputStream(path);
                    Bitmap bitmap = BitmapFactory.decodeStream(inputStream);
                    img.setImageBitmap(bitmap);

                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                    Toast.makeText(this,"Unale to open image", Toast.LENGTH_LONG).show();
                }
                img.setImageURI(path);
                //  break;
            }
        }
    }

    private final int SELECT_PICTURE = 10;
    public void menu(View view){
        final CharSequence[] options = {"Tomar foto", "Elegir de galeria", "Cancelar"};
        final AlertDialog.Builder builder= new AlertDialog.Builder(this);
        builder.setTitle("Elige una opcion");
        builder.setItems(options, new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int seleccion) {
                if(options[seleccion] == "Tomar foto"){
                    tomarFoto();
                }else if (options[seleccion] == "Elegir de galeria"){
                    Intent intent = new Intent(Intent.ACTION_PICK,
                            MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                    intent.setType("image/");
                    startActivityForResult(intent.createChooser(intent, "Selecciona app de imagen"),SELECT_PICTURE);
                }else if(options[seleccion] == "Cancelar"){
                    dialog.dismiss();
                }
            }
        });
        builder.show();
    }
    String mCurrentPhotoPath;
    private File createImageFile() throws IOException {
        // Create an image file name
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        String imageFileName = "Backup_" + timeStamp + "_";
        File storageDir = getExternalFilesDir(Environment.DIRECTORY_PICTURES);
        File image = File.createTempFile(imageFileName, ".jpg",storageDir);

        // Save a file: path for use with ACTION_VIEW intents
        mCurrentPhotoPath = image.getAbsolutePath();
        return image;
    }
    static final int REQUEST_TAKE_PHOTO = 1;
    public void tomarFoto() {
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
                Uri photoURI = FileProvider.getUriForFile(this,"com.example.android.fileprovider", photoFile);
                takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT, photoURI.toString());
                startActivityForResult(takePictureIntent, REQUEST_TAKE_PHOTO);
            }
        }
    }


}
