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
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
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
import com.weiwangcn.betterspinner.library.material.MaterialBetterSpinner;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Date;

@SuppressWarnings("all")
public class NewCattleActivity extends AppCompatActivity {

    private MaterialBetterSpinner estateSpinner, lotSpinner, breedSpinner, purposeSpinner, genderSpinner;
    private EditText editTCode, editTAge, editTWeight;
    private ImageView imageCattle;

    private FloatingActionButton floatButtonEdit;
    private Button buttonAdd, buttonDelete;
    private ImageButton buttonCamera;

    private final int SELECT_PICTURE = 10;
    static final int REQUEST_TAKE_PHOTO = 1;
    private Cattle cattle, newCattle;
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

        breedSpinner = findViewById(R.id.breedSpinner);
        purposeSpinner = findViewById(R.id.purposeSpinner);
        genderSpinner = findViewById(R.id.genderSpinner);
        estateSpinner = findViewById(R.id.estateSpinner);
        lotSpinner = findViewById(R.id.lotSpinner);

        editTCode = findViewById(R.id.editTCode);
        editTAge = findViewById(R.id.editTAge);
        editTWeight = findViewById(R.id.editTWeight);
        floatButtonEdit = findViewById(R.id.FABEdit);
        buttonAdd = findViewById(R.id.buttonAdd);
        buttonDelete = findViewById(R.id.buttonDelete);
        imageCattle = findViewById(R.id.imageView);
        buttonCamera = findViewById(R.id.buttonCamera);

        user = UserAppDAO.getUser();
        setSpinnersAdaper();

        newCattle = new Cattle();
        selectionSpinnerInit();

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
            imageCattle.setImageBitmap(Utilities.getRoundedCornerBitmap(Utilities.byteToBitmap(cattle.getPhoto()), 100));

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
                purposeSpinner.setText(adapter.getItem(i).getName());
        }
        return -1;
    }

    private int getGenderPosition(ArrayAdapter<Gender> adapter, int id) {
        for (int i = 0; i < adapter.getCount(); i++) {
            if (adapter.getItem(i).getId() == id)
                genderSpinner.setText(adapter.getItem(i).getName());
        }
        return -1;
    }

    private int getLotPosition(ArrayAdapter<Lot> adapter, int id) {
        for (int i = 0; i < adapter.getCount(); i++) {
            if (adapter.getItem(i).getId() == id)
                lotSpinner.setText(adapter.getItem(i).getName());
        }
        return -1;
    }

    private int getEstatePosition(ArrayAdapter<Estate> adapter, int id) {
        for (int i = 0; i < adapter.getCount(); i++) {
            Log.i("server", "size: " + adapter.getCount() + " i: " + i);
            if (adapter.getItem(i).getId() == id)
                estateSpinner.setText(adapter.getItem(i).getName());
        }
        return -1;
    }

    private int getBreedPosition(ArrayAdapter<Breed> adapter, int id) {
        for (int i = 0; i < adapter.getCount(); i++) {
            if (adapter.getItem(i).getId() == id)
                breedSpinner.setText(adapter.getItem(i).getName());
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

    private void selectionSpinnerInit(){
        purposeSpinner.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                newCattle.setIdPurpose(purposeAdapter.getItem(position).getId());
            }
        });

        genderSpinner.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                newCattle.setIdGender(genderAdapter.getItem(position).getId());
            }
        });

        estateSpinner.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                newCattle.setIdEstate(estateAdapter.getItem(position).getId());
            }
        });

        breedSpinner.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                newCattle.setIdBreed(breedAdapter.getItem(position).getId());
            }
        });


        lotSpinner.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                newCattle.setIdLot(lotAdapter.getItem(position).getId());
            }
        });
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
        buttonCamera.setEnabled(var);
        lotSpinner.setEnabled(var);
        breedSpinner.setEnabled(var);
        purposeSpinner.setEnabled(var);
        genderSpinner.setEnabled(var);
        estateSpinner.setEnabled(var);


        if (!var) {
            lotSpinner.setDropDownHeight(0);
            breedSpinner.setDropDownHeight(0);
            purposeSpinner.setDropDownHeight(0);
            genderSpinner.setDropDownHeight(0);
            estateSpinner.setDropDownHeight(0);
        } else {
            lotSpinner.setDropDownHeight(WindowManager.LayoutParams.WRAP_CONTENT);
            breedSpinner.setDropDownHeight(WindowManager.LayoutParams.WRAP_CONTENT);
            purposeSpinner.setDropDownHeight(WindowManager.LayoutParams.WRAP_CONTENT);
            genderSpinner.setDropDownHeight(WindowManager.LayoutParams.WRAP_CONTENT);
            estateSpinner.setDropDownHeight(WindowManager.LayoutParams.WRAP_CONTENT);
        }
    }

    public void createCattle(View view) {
        if (editTCode.getText().toString().isEmpty() || editTAge.getText().toString().isEmpty() || editTWeight.getText().toString().isEmpty()) {
            Toast.makeText(this, "Por favor ingresa todos los datos", Toast.LENGTH_SHORT).show();
        } else {

            newCattle.setCode(Integer.parseInt(editTCode.getText().toString()));
            newCattle.setAge(Integer.parseInt(editTAge.getText().toString()));
            newCattle.setWeight(Double.parseDouble(editTWeight.getText().toString()));
            newCattle.setPhoto(Utilities.imageViewToByte(imageCattle));

            Log.i("server", "creating cattle... " + newCattle.toString());

            CattleDAO.insertCattle(this, newCattle);//Insert a new cattle an returning to ViewCattle
        }
    }

    public void editCattle(View view) {
        FloatingActionButton floatBtEdit = findViewById(R.id.FABEdit);
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
                imageCattle.setImageBitmap(Utilities.getRoundedCornerBitmap(imageBitmap, 100));

            } else {
                //Case SELECT_PICTURE:
                Uri path = data.getData();
                InputStream inputStream;
                try {
                    inputStream = getContentResolver().openInputStream(path);
                    Bitmap bitmap = BitmapFactory.decodeStream(inputStream);
                    imageCattle.setImageBitmap(Utilities.getRoundedCornerBitmap(bitmap, 100));

                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                    Toast.makeText(this, "No se pudo abrir la imagen", Toast.LENGTH_LONG).show();
                }
                imageCattle.setImageURI(path);
            }
        }
    }


}