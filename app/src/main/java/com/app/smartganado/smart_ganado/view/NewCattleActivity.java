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
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v4.content.FileProvider;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import com.app.smartganado.smart_ganado.R;
import com.app.smartganado.smart_ganado.model.dao.BreedDAO;
import com.app.smartganado.smart_ganado.model.dao.CattleDAO;
import com.app.smartganado.smart_ganado.model.dao.EstateDAO;
import com.app.smartganado.smart_ganado.model.dao.GenderDAO;
import com.app.smartganado.smart_ganado.model.dao.LotDAO;
import com.app.smartganado.smart_ganado.model.dao.PurposeDAO;
import com.app.smartganado.smart_ganado.model.vo.Breed;
import com.app.smartganado.smart_ganado.model.vo.Cattle;
import com.app.smartganado.smart_ganado.model.vo.Estate;
import com.app.smartganado.smart_ganado.model.vo.Gender;
import com.app.smartganado.smart_ganado.model.vo.Lot;
import com.app.smartganado.smart_ganado.model.vo.Purpose;
import com.app.smartganado.smart_ganado.utilities.Utilities;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Date;

@SuppressWarnings("all")
public class NewCattleActivity extends AppCompatActivity {

    ImageView imageCattle; //imagen vaca

    Cattle cattle;
    Spinner estateSpinner, lotSpinner, breedSpinner, purposeSpinner, genderSpinner;


    EditText TXTcodigo, TXTedad, TXTPeso;
    FloatingActionButton editar;
    Button registrar;
    Button Eliminar;
    ImageButton button;


    private final int SELECT_PICTURE = 10;
    static final int REQUEST_TAKE_PHOTO = 1;
    String mCurrentPhotoPath;

    private ArrayAdapter<Purpose> purposeAdapter;
    private ArrayAdapter<Estate> estateAdapter;
    private ArrayAdapter<Gender> genderAdapter;
    private ArrayAdapter<Breed> breedAdapter;
    private ArrayAdapter<Lot> lotAdapter;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_cattle);

        breedSpinner = findViewById(R.id.RazaTexto);
        purposeSpinner = findViewById(R.id.Proposito);
        genderSpinner = findViewById(R.id.Genero);
        estateSpinner = findViewById(R.id.Finca);
        lotSpinner = findViewById(R.id.lotSpinner);

        TXTcodigo = findViewById(R.id.Codigo);
        TXTedad = findViewById(R.id.EdadGanado);
        TXTPeso = findViewById(R.id.Peso);
        editar = findViewById(R.id.FABEditar);
        registrar = findViewById(R.id.Registrar);
        Eliminar = findViewById(R.id.Eliminar);
        imageCattle = findViewById(R.id.imageView);
        button = findViewById(R.id.imageButton);

        initUI();

        if (ContextCompat.checkSelfPermission(NewCattleActivity.this, Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(NewCattleActivity.this, Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(NewCattleActivity.this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.CAMERA}, 1000);
        }

        Log.i("server", "llego: " + getIntent().getSerializableExtra("Info"));


        if (getIntent().getSerializableExtra("Info") == null) {
            Log.i("server", "Crear");
            editar.setVisibility(View.GONE);
            Eliminar.setVisibility(View.GONE);
        } else {

            Log.i("server", "Editando");
            cattle = (Cattle) getIntent().getSerializableExtra("Info");
            try {

                Thread.sleep(5000);
                TXTcodigo.setText(String.valueOf(cattle.getId()));
                TXTedad.setText(String.valueOf(cattle.getAge()));
                TXTPeso.setText(String.valueOf(cattle.getWeight()));


                breedSpinner.setSelection(2);
                genderSpinner.setSelection(cattle.getIdGender());
                lotSpinner.setSelection(3, false);
                purposeSpinner.setSelection(cattle.getIdPurpose());
                estateSpinner.setSelection(EstateDAO.getPositionID(estateSpinner, cattle.getIdEstate()));
                lotSpinner.refreshDrawableState();

            } catch (InterruptedException e) {
                Log.i("server", e.getMessage());
            }


            TXTcodigo.setEnabled(false);
            TXTedad.setEnabled(false);
            TXTPeso.setEnabled(false);
            breedSpinner.setEnabled(false);
            purposeSpinner.setEnabled(false);
            genderSpinner.setEnabled(false);
            estateSpinner.setEnabled(false);
            lotSpinner.setEnabled(false);
            button.setEnabled(false);
            registrar.setVisibility(View.GONE);
        }
    }

    private void initUI() {

        PurposeDAO purposeDAO = new PurposeDAO();
        purposeAdapter = new ArrayAdapter<>(getApplicationContext(), android.R.layout.simple_spinner_item, purposeDAO.getPurposeList());
        purposeSpinner.setAdapter(purposeAdapter);
        purposeDAO.getPurposeList(purposeAdapter);

        BreedDAO breedDAO = new BreedDAO();
        breedAdapter = new ArrayAdapter<>(getApplicationContext(), android.R.layout.simple_spinner_item, breedDAO.getBreedList());
        breedSpinner.setAdapter(breedAdapter);
        breedDAO.getBreeds(breedAdapter);

        EstateDAO estateDAO = new EstateDAO();
        estateAdapter = new ArrayAdapter<>(getApplicationContext(), android.R.layout.simple_spinner_item, estateDAO.getEstateList());
        estateSpinner.setAdapter(estateAdapter);
        estateDAO.getEstates(1234l, estateAdapter);

        GenderDAO genderDAO = new GenderDAO();
        genderAdapter = new ArrayAdapter<>(getApplicationContext(), android.R.layout.simple_spinner_item, genderDAO.getGenderList());
        genderSpinner.setAdapter(genderAdapter);
        genderDAO.getGenderList(genderAdapter);

        LotDAO lotDAO = new LotDAO();
        lotAdapter = new ArrayAdapter<>(getApplicationContext(), android.R.layout.simple_spinner_item, lotDAO.getLotList());
        lotSpinner.setAdapter(lotAdapter);
        lotDAO.getLotList(lotAdapter);
    }

    public void createCattleAction(View view) {
        if (TXTcodigo.getText().toString().isEmpty() || TXTedad.getText().toString().isEmpty() || TXTPeso.getText().toString().isEmpty()) {
            Toast.makeText(this, "Debes ingresar todos los datos", Toast.LENGTH_SHORT).show();
        } else {

            Cattle newCattle = new Cattle();
            newCattle.setCode(Integer.parseInt(TXTcodigo.getText().toString()));
            newCattle.setAge(Integer.parseInt(TXTedad.getText().toString()));
            newCattle.setWeight(Double.parseDouble(TXTPeso.getText().toString()));


            newCattle.setIdPurpose(((Purpose) purposeSpinner.getSelectedItem()).getId());
            newCattle.setIdGender(((Gender) genderSpinner.getSelectedItem()).getId());
            newCattle.setIdEstate(((Estate) estateSpinner.getSelectedItem()).getId());
            newCattle.setIdBreed(((Breed) breedSpinner.getSelectedItem()).getId());
            newCattle.setIdLot(((Lot) lotSpinner.getSelectedItem()).getId());


            newCattle.setPhoto(Utilities.imageViewToByte(imageCattle));

            Log.i("server: ", "new cattle -> " + newCattle.toString());

            CattleDAO.insertCattle(getApplicationContext(), newCattle);//Insert a new cattle

            //Go to View Cattle
            Intent i = new Intent(getApplicationContext(), ViewCattleActivity.class);
            startActivity(i);
        }
    }

    public void menu(View view) {
        final CharSequence[] options = {"Tomar foto", "Elegir de galeria", "Cancelar"};
        final AlertDialog.Builder builder = new AlertDialog.Builder(NewCattleActivity.this);
        builder.setTitle("Elige una opción");
        builder.setItems(options, new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int seleccion) {

                if (options[seleccion] == "Tomar foto") {
                    tomarFoto();
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

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == RESULT_OK) {
            if (requestCode == REQUEST_TAKE_PHOTO) {
                //Switch (requestCode)
                //Case REQUEST_TAKE_PHOTO:
                Bundle extras = data.getExtras();
                Bitmap imageBitmap = (Bitmap) extras.get("data");
                imageCattle.setImageBitmap(imageBitmap);

            } else {
                //Case SELECT_PICTURE:
                Uri path = data.getData();
                InputStream inputStream;
                try {
                    inputStream = getContentResolver().openInputStream(path);
                    Bitmap bitmap = BitmapFactory.decodeStream(inputStream);
                    imageCattle.setImageBitmap(bitmap);

                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                    Toast.makeText(this, "No se pudo abrir la imagen", Toast.LENGTH_LONG).show();
                }
                imageCattle.setImageURI(path);
            }
        }
    }

    public void Editar(View view) {
        registrar = findViewById(R.id.Registrar);
        FloatingActionButton floatBtEdit = findViewById(R.id.FABEditar);
        floatBtEdit.setVisibility(View.GONE);
        registrar.setVisibility(View.VISIBLE);
        TXTcodigo.setEnabled(true);
        TXTedad.setEnabled(true);
        TXTPeso.setEnabled(true);
        breedSpinner.setEnabled(true);
        purposeSpinner.setEnabled(true);
        genderSpinner.setEnabled(true);
        estateSpinner.setEnabled(true);
        button.setEnabled(true);
        Eliminar.setVisibility(View.INVISIBLE);
    }

    public void Eliminar(View view) {
        Toast.makeText(this, "Eliminar", Toast.LENGTH_LONG).show();
    }
}