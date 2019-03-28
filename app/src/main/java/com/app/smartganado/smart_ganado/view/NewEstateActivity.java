package com.app.smartganado.smart_ganado.view;

import android.Manifest;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v4.content.FileProvider;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.app.smartganado.smart_ganado.R;
import com.app.smartganado.smart_ganado.model.vo.Estate;
import com.app.smartganado.smart_ganado.utilities.Utilities;
import com.google.gson.Gson;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Date;

public class NewEstateActivity extends AppCompatActivity {

    EditText nameEstateEditText, areaEstateEditText, locacionEstateEditText;
    Button estateButton, estateImgButton;
    String jsonEstate;
    Integer choose, position;
    ImageView photoEstateImageView;
    Utilities utilities;
    Estate newEstate;
    Long userPhone;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        utilities = new Utilities();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_estate);
        nameEstateEditText = (EditText) findViewById(R.id.nameEstateEditText);
        areaEstateEditText = (EditText) findViewById(R.id.areaEstateEditText);
        locacionEstateEditText = (EditText) findViewById(R.id.locationEstateEditText);
        photoEstateImageView = (ImageView) findViewById(R.id.estateImageView);
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.CAMERA}, 1000);
        }

        Bundle estateBundle = getIntent().getExtras();
        if (estateBundle != null) {
            choose = Integer.valueOf(estateBundle.getString("choose"));
            menu(choose, estateBundle);
        }


    }

    public void onInsertEstate(View view) {
//aqui lee de los componentes
        switch (choose) {
            case 1:
                if (locacionEstateEditText.getText().toString() != null && nameEstateEditText.getText().toString() != null && locacionEstateEditText.getText().toString() != null) {
                    newEstate.setName(nameEstateEditText.getText().toString());
                    newEstate.setArea(Double.valueOf(areaEstateEditText.getText().toString()));
                    newEstate.setLocation(locacionEstateEditText.getText().toString());

                    BitmapDrawable bitmapDrawable = ((BitmapDrawable) photoEstateImageView.getDrawable());
                    Bitmap bitmap = bitmapDrawable.getBitmap();
                    ByteArrayOutputStream stream = new ByteArrayOutputStream();
                    bitmap.compress(Bitmap.CompressFormat.JPEG, 2, stream);
                    byte[] imageInByte = stream.toByteArray();

                    newEstate.setPhoto(imageInByte);
                    newEstate.setId(getEstateId(newEstate));
                    Intent newEstateIntent = new Intent();
                    Bundle estateAdd = new Bundle();
                    estateAdd.putSerializable("estate", newEstate);

                    estateAdd.putInt("position", position);
                    newEstateIntent.putExtras(estateAdd);
                    setResult(RESULT_OK, newEstateIntent);
                    this.finish();
                    //Mandar la solicitud al servidor de actualizar
                    //Si la respuesta del cliente es favorable >
                    //If es favorable
                    Toast.makeText(this, "Se inserto", Toast.LENGTH_LONG).show();
                    //Si no
                    // Toast.makeText(this, "Hubo un problema insertando, intentelo más tarde", Toast.LENGTH_LONG).show();
                } else {
                    Toast.makeText(this, "Llene todos los campos", Toast.LENGTH_LONG).show();
                }

                break;
            case 2:
                newEstate.setId(getEstateId(newEstate));
                Intent newCattleIntent = new Intent(this, ViewCattleActivity.class);
                newCattleIntent.putExtra("userPhone", newEstate.getPhoneUser());
                newCattleIntent.putExtra("idEstate", newEstate.getId());
                this.startActivity(newCattleIntent);
                break;
            case 3:

                if (!locacionEstateEditText.getText().toString().isEmpty() && !nameEstateEditText.getText().toString().isEmpty() && !locacionEstateEditText.getText().toString().isEmpty() && !photoEstateImageView.getDrawable().equals(R.drawable.farmdef)) {
                    newEstate.setName(nameEstateEditText.getText().toString());
                    newEstate.setArea(Double.valueOf(areaEstateEditText.getText().toString()));
                    newEstate.setLocation(locacionEstateEditText.getText().toString());

                    BitmapDrawable bitmapDrawable = ((BitmapDrawable) photoEstateImageView.getDrawable());
                    Bitmap bitmap = bitmapDrawable.getBitmap();
                    ByteArrayOutputStream stream = new ByteArrayOutputStream();
                    bitmap.compress(Bitmap.CompressFormat.JPEG, 2, stream);
                    byte[] imageInByte = stream.toByteArray();

                    newEstate.setPhoto(imageInByte);
                    newEstate.setPhoneUser(userPhone);
                    //Manda la solicitud al servidor de agregar
                    //If es favorable
                    Toast.makeText(this, "Se inserto", Toast.LENGTH_LONG).show();
                    //Si no
                    // Toast.makeText(this, "Hubo un problema insertando, intentelo más tarde", Toast.LENGTH_LONG).show();

                    Intent newEstateIntent = new Intent();
                    Bundle estateAdd = new Bundle();
                    estateAdd.putSerializable("estate", newEstate);
                    estateAdd.putInt("chooseEstate", 1);
                    newEstateIntent.putExtras(estateAdd);
                    setResult(RESULT_OK, newEstateIntent);
                    this.finish();
                } else {
                    Toast.makeText(this, "Llene todos los campos", Toast.LENGTH_LONG).show();
                }


        }
    }

    private void menu(Integer choose, Bundle estateBundle) {
        switch (choose) {
            case 1:
                jsonEstate = estateBundle.getString("Estate");
                newEstate = new Gson().fromJson(jsonEstate, Estate.class);
                position = estateBundle.getInt("position");
                nameEstateEditText.setText(newEstate.getName());
                areaEstateEditText.setText(String.valueOf(newEstate.getArea()));
                locacionEstateEditText.setText(newEstate.getLocation());

                photoEstateImageView.setImageBitmap(Utilities.byteToBitmap(newEstate.getPhoto()));
                break;
            case 2:
                jsonEstate = estateBundle.getString("Estate");
                newEstate = new Gson().fromJson(jsonEstate, Estate.class);
                nameEstateEditText.setText(newEstate.getName());
                nameEstateEditText.setEnabled(false);
                areaEstateEditText.setText(String.valueOf(newEstate.getArea()));
                areaEstateEditText.setEnabled(false);
                locacionEstateEditText.setText(newEstate.getLocation());
                locacionEstateEditText.setEnabled(false);
                estateButton = (Button) findViewById(R.id.Save_Finca);
                estateImgButton = (Button) findViewById(R.id.imgButton);
                estateImgButton.setVisibility(View.INVISIBLE);
                photoEstateImageView.setImageBitmap(Utilities.byteToBitmap(newEstate.getPhoto()));
                estateButton.setText("Ver ganado");
                break;
            case 3:
                newEstate = new Estate();

                userPhone = estateBundle.getLong("userPhone");
        }

    }

    private Integer getEstateId(Estate estate) {
        //Acá debe ir el metodo que se conecte con el servidor para obtener la id de la finca
        //Integer idFinca=consulta con el server
        //Return idFinca;
        return null;
    }


    static final int REQUEST_IMAGE_CAPTURE = 1;

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == RESULT_OK) {
            if (requestCode == REQUEST_TAKE_PHOTO) {
                //switch (requestCode) {
                //    case REQUEST_TAKE_PHOTO:
                Bundle extras = data.getExtras();
                Bitmap imageBitmap = (Bitmap) extras.get("data");
                photoEstateImageView.setImageBitmap(imageBitmap);
                //       break;
            } else {
                //    case SELECT_PICTURE:
                Uri path = data.getData();
                InputStream inputStream;
                try {
                    inputStream = getContentResolver().openInputStream(path);
                    Bitmap bitmap = BitmapFactory.decodeStream(inputStream);
                    photoEstateImageView.setImageBitmap(bitmap);

                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                    Toast.makeText(this, "Unale to open image", Toast.LENGTH_LONG).show();
                }
                photoEstateImageView.setImageURI(path);
                //  break;
            }
        }

    }

    private final int SELECT_PICTURE = 10;

    public void menu(View view) {
        final CharSequence[] options = {"Tomar foto", "Elegir de galeria", "Cancelar"};
        final AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Elige una opcion");
        builder.setItems(options, new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int seleccion) {
                if (options[seleccion] == "Tomar foto") {
                    tomarFoto();
                } else if (options[seleccion] == "Elegir de galeria") {
                    Intent intent = new Intent(Intent.ACTION_PICK,
                            MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                    intent.setType("image/");
                    startActivityForResult(intent.createChooser(intent, "Selecciona app de imagen"), SELECT_PICTURE);
                } else if (options[seleccion] == "Cancelar") {
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
        File image = File.createTempFile(imageFileName, ".jpg", storageDir);

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
                Uri photoURI = FileProvider.getUriForFile(this, "com.example.android.fileprovider", photoFile);
                takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT, photoURI.toString());
                startActivityForResult(takePictureIntent, REQUEST_TAKE_PHOTO);
            }
        }
    }


}
