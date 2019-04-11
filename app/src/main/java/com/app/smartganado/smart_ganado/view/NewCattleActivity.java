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
import com.app.smartganado.smart_ganado.model.dao.UserAppDAO;
import com.app.smartganado.smart_ganado.model.vo.Breed;
import com.app.smartganado.smart_ganado.model.vo.Cattle;
import com.app.smartganado.smart_ganado.model.vo.Estate;
import com.app.smartganado.smart_ganado.model.vo.Gender;
import com.app.smartganado.smart_ganado.model.vo.Lot;
import com.app.smartganado.smart_ganado.model.vo.Purpose;
import com.app.smartganado.smart_ganado.model.vo.UserApp;
import com.app.smartganado.smart_ganado.utilities.Utilities;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Date;

@SuppressWarnings("all")
public class NewCattleActivity extends AppCompatActivity {

    private Spinner estateSpinner, lotSpinner, breedSpinner, purposeSpinner, genderSpinner;
    private EditText editTCode, editTAge, editTWeight;
    private ImageView imageCattle;

    private FloatingActionButton floatButtonEdit;
    private Button buttonAdd, buttonDelete;
    private ImageButton buttonCamera;


    private final int SELECT_PICTURE = 10;
    static final int REQUEST_TAKE_PHOTO = 1;
    private Cattle cattle;
    private UserApp user;

    private String mCurrentPhotoPath;

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

        editTCode = findViewById(R.id.Codigo);
        editTAge = findViewById(R.id.EdadGanado);
        editTWeight = findViewById(R.id.Peso);
        floatButtonEdit = findViewById(R.id.FABEditar);
        buttonAdd = findViewById(R.id.Registrar);
        buttonDelete = findViewById(R.id.Eliminar);
        imageCattle = findViewById(R.id.imageView);
        buttonCamera = findViewById(R.id.buttonCamera);

        user = UserAppDAO.getUser();
        setSpinnersAdaper();

        cattle = (Cattle) getIntent().getSerializableExtra("cattle");

        if (cattle == null) {
            buttonAdd.setText("Registrar");
            floatButtonEdit.setVisibility(View.GONE);
            buttonDelete.setVisibility(View.GONE);
        } else {
            buttonAdd.setText("Actualizar");

            editTCode.setText(String.valueOf(cattle.getCode()));
            editTAge.setText(String.valueOf(cattle.getAge()));
            editTWeight.setText(String.valueOf(cattle.getWeight()));

            getPurposePosition(purposeAdapter, cattle.getIdPurpose());
            getGenderPosition(genderAdapter, cattle.getIdGender());
            getLotPosition(lotAdapter, cattle.getIdLot());
            getEstatePosition(estateAdapter, cattle.getIdEstate());
            getBreedPosition(breedAdapter, cattle.getIdBreed());

            enableButtons(false);
            buttonAdd.setVisibility(View.GONE);
        }
    }

    private int getPurposePosition(ArrayAdapter<Purpose> adapter, int id) {
        for (int i = 0; i < adapter.getCount(); i++) {
            if (adapter.getItem(i).getId() == id)
                purposeSpinner.setSelection(i);
        }
        return -1;
    }

    private int getGenderPosition(ArrayAdapter<Gender> adapter, int id) {
        for (int i = 0; i < adapter.getCount(); i++) {
            if (adapter.getItem(i).getId() == id)
                genderSpinner.setSelection(i);
        }
        return -1;
    }

    private int getLotPosition(ArrayAdapter<Lot> adapter, int id) {
        for (int i = 0; i < adapter.getCount(); i++) {
            if (adapter.getItem(i).getId() == id)
                lotSpinner.setSelection(i);
        }
        return -1;
    }

    private int getEstatePosition(ArrayAdapter<Estate> adapter, int id) {
        for (int i = 0; i < adapter.getCount(); i++) {
            if (adapter.getItem(i).getId() == id)
                estateSpinner.setSelection(i);
        }
        return -1;
    }

    private int getBreedPosition(ArrayAdapter<Breed> adapter, int id) {
        for (int i = 0; i < adapter.getCount(); i++) {
            if (adapter.getItem(i).getId() == id)
                breedSpinner.setSelection(i);
        }
        return -1;
    }

    private void setSpinnersAdaper() {
        purposeAdapter = new ArrayAdapter<>(getApplicationContext(), android.R.layout.simple_spinner_item, PurposeDAO.getPurposeList());
        purposeSpinner.setAdapter(purposeAdapter);

        breedAdapter = new ArrayAdapter<>(getApplicationContext(), android.R.layout.simple_spinner_item, BreedDAO.getBreedList());
        breedSpinner.setAdapter(breedAdapter);

        estateAdapter = new ArrayAdapter<>(getApplicationContext(), android.R.layout.simple_spinner_item, EstateDAO.getEstateList());
        estateSpinner.setAdapter(estateAdapter);

        genderAdapter = new ArrayAdapter<>(getApplicationContext(), android.R.layout.simple_spinner_item, GenderDAO.getGenderList());
        genderSpinner.setAdapter(genderAdapter);

        lotAdapter = new ArrayAdapter<>(getApplicationContext(), android.R.layout.simple_spinner_item, LotDAO.getLotList());
        lotSpinner.setAdapter(lotAdapter);
    }

    private void loadSpinnersInfo() {
        PurposeDAO.getPurposeList(purposeAdapter);
        BreedDAO.getBreeds(breedAdapter);
        EstateDAO.getEstates(user.getPhone(), estateAdapter);
        GenderDAO.getGenderList(genderAdapter);
        LotDAO.getLotList(lotAdapter);
    }

    @Override
    protected void onResume() {
        loadSpinnersInfo();
        super.onResume();
    }

    private void enableButtons(Boolean var) {
        editTCode.setEnabled(var);
        editTAge.setEnabled(var);
        editTWeight.setEnabled(var);

        lotSpinner.setEnabled(var);
        breedSpinner.setEnabled(var);
        purposeSpinner.setEnabled(var);
        genderSpinner.setEnabled(var);
        estateSpinner.setEnabled(var);

        buttonCamera.setEnabled(var);
    }

    public void createCattle(View view) {
        if (editTCode.getText().toString().isEmpty() || editTAge.getText().toString().isEmpty() || editTWeight.getText().toString().isEmpty()) {
            Toast.makeText(this, "Por favor ingresa todos los datos", Toast.LENGTH_SHORT).show();
        } else {

            Cattle newCattle = new Cattle();
            newCattle.setCode(Integer.parseInt(editTCode.getText().toString()));
            newCattle.setAge(Integer.parseInt(editTAge.getText().toString()));
            newCattle.setWeight(Double.parseDouble(editTWeight.getText().toString()));
            newCattle.setPhoto(Utilities.imageViewToByte(imageCattle));

            newCattle.setIdPurpose(((Purpose) purposeSpinner.getSelectedItem()).getId());
            newCattle.setIdGender(((Gender) genderSpinner.getSelectedItem()).getId());
            newCattle.setIdEstate(((Estate) estateSpinner.getSelectedItem()).getId());
            newCattle.setIdBreed(((Breed) breedSpinner.getSelectedItem()).getId());
            newCattle.setIdLot(((Lot) lotSpinner.getSelectedItem()).getId());


            Log.i("server", "creating cattle... " + newCattle.toString());

            CattleDAO.insertCattle(this, newCattle);//Insert a new cattle an returning to ViewCattle
        }
    }

    public void editCattle(View view) {
        FloatingActionButton floatBtEdit = findViewById(R.id.FABEditar);
        floatBtEdit.setVisibility(View.GONE);
        buttonAdd.setVisibility(View.VISIBLE);
        buttonDelete.setVisibility(View.INVISIBLE);
        enableButtons(true);
    }

    public void deleteCattle(View view) {
        Intent intent = new Intent(getApplicationContext(), ViewCattleActivity.class);
        intent.putExtra("id_cattle", String.valueOf(cattle.getId()));
        intent.setFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);
        startActivity(intent);
    }

    public void menu(View view) {
        final CharSequence[] options = {"Tomar foto", "Elegir de galeria", "Cancelar"};
        final AlertDialog.Builder builder = new AlertDialog.Builder(NewCattleActivity.this);
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


}