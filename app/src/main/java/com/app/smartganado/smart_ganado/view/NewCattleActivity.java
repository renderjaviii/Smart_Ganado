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
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import com.app.smartganado.smart_ganado.R;
import com.app.smartganado.smart_ganado.model.vo.Breed;
import com.app.smartganado.smart_ganado.model.vo.Cattle;
import com.app.smartganado.smart_ganado.model.vo.Estate;
import com.app.smartganado.smart_ganado.model.vo.Gender;
import com.app.smartganado.smart_ganado.model.vo.Purpose;
import com.app.smartganado.smart_ganado.remote.APIService;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;


public class NewCattleActivity extends AppCompatActivity {

    ImageView img;
    ArrayList<Estate> InfoFincas = new ArrayList<Estate>();
    ArrayList<Gender> InfoGeneros = new ArrayList<Gender>();
    ArrayList<Breed> InfoRazas = new ArrayList<Breed>();
    ArrayList<Purpose> InfoPropositos = new ArrayList<Purpose>();
    Spinner Finca, Lote, Raza, Proposito, Genero;
    EditText TXTcodigo, TXTedad, TXTPeso;
    FloatingActionButton editar;
    Button registrar;
    Cattle cattle;
    ImageButton button;

    public APIService myApiService;
    private int invisible;

    public ArrayList ListaFincas() {
        ArrayList datos = new ArrayList();
        Estate finca;
        for (int i = 0; i <= 4; i++) {
            finca = new Estate();
            finca.setId(i);
            finca.setName("Finca: " + i);
            finca.setArea(2000 * i);
            finca.setLocation("ubicacion " + i);
            datos.add(finca);
        }
        return datos;
    }

    public ArrayList ListaGeneros() {
        ArrayList datos = new ArrayList();
        Gender genero;
        for (int i = 0; i <= 4; i++) {
            genero = new Gender();
            genero.setId(i);
            genero.setName("generos: " + i);
            datos.add(genero);
        }
        return datos;
    }

    public ArrayList ListaRazas() {
        ArrayList datos = new ArrayList();
        Breed raza;
        for (int i = 0; i <= 4; i++) {
            raza = new Breed();
            raza.setId(i);
            raza.setName("Raza: " + i);
            datos.add(raza);
        }
        return datos;
    }

    public ArrayList ListaPropositos() {
        ArrayList datos = new ArrayList();
        Purpose proposito;
        for (int i = 0; i <= 4; i++) {
            proposito = new Purpose();
            proposito.setId(i);
            proposito.setName("proposito: " + i);
            datos.add(proposito);
        }
        return datos;
    }

    /*public ArrayList ListaImagenes(){
        ArrayList listaImagenes = new ArrayList();
        ImageView imagen=null;
        for (int i=0; i<=4; i++){
            String nombre="vaca"+i;
            imagen.setImageResource(R.drawable.vaca1);
            listaImagenes.add(imagen);
        }
        return listaImagenes;
    }*/

    public ArrayList NombreFincas(ArrayList<Estate> lista) {
        ArrayList<String> listaNombres = new ArrayList<String>();
        for (int i = 0; i < lista.size(); i++) {
            listaNombres.add(((Estate) (lista.get(i))).getName());
        }
        return listaNombres;
    }

    public ArrayList NombreGeneros(ArrayList<Gender> lista) {
        ArrayList<String> listaNombres = new ArrayList<String>();
        for (int i = 0; i < lista.size(); i++) {
            listaNombres.add(((Gender) (lista.get(i))).getName());
        }
        return listaNombres;
    }

    public ArrayList NombreRazas(ArrayList<Breed> lista) {
        ArrayList<String> listaNombres = new ArrayList<String>();
        for (int i = 0; i < lista.size(); i++) {
            listaNombres.add(((Breed) (lista.get(i))).getName());
        }
        return listaNombres;
    }

    public ArrayList NombrePropositos(ArrayList<Purpose> lista) {
        ArrayList<String> listaNombres = new ArrayList<String>();
        for (int i = 0; i < lista.size(); i++) {
            listaNombres.add(((Purpose) (lista.get(i))).getName());
        }
        return listaNombres;
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_cattle);

        //Spinner Finca,Clasificacion, Raza, Proposito, Genero
        Raza = (Spinner) findViewById(R.id.RazaTexto);
        Proposito = (Spinner) findViewById(R.id.Proposito);
        Genero = (Spinner) findViewById(R.id.Genero);
        Finca = (Spinner) findViewById(R.id.Finca);
        TXTcodigo = (EditText) findViewById(R.id.Codigo);
        TXTedad = (EditText) findViewById(R.id.EdadGanado);
        TXTPeso = (EditText) findViewById(R.id.Peso);
        //editar = (FloatingActionButton) findViewById(R.id.FABEditar); /No encuentra el componente
        registrar = (Button) findViewById(R.id.Registrar);
        img = (ImageView) findViewById(R.id.imageView);
        //button = (ImageButton) findViewById(R.id.imageButton); //No encuentra el componente
        InfoFincas = ListaFincas();
        InfoGeneros = ListaGeneros();
        InfoPropositos = ListaPropositos();
        InfoRazas = ListaRazas();

        ArrayAdapter<CharSequence> adapter2 = new ArrayAdapter(this, android.R.layout.simple_spinner_item, NombreRazas(InfoRazas));
        Raza.setAdapter(adapter2);

        ArrayAdapter<CharSequence> adapter3 = new ArrayAdapter(this, android.R.layout.simple_spinner_item, NombrePropositos(InfoPropositos));
        Proposito.setAdapter(adapter3);

        ArrayAdapter<CharSequence> adapter4 = new ArrayAdapter(this, android.R.layout.simple_spinner_item, NombreGeneros(InfoGeneros));
        Genero.setAdapter(adapter4);

        ArrayAdapter<CharSequence> adapter5 = new ArrayAdapter(this, android.R.layout.simple_spinner_item, NombreFincas(InfoFincas));
        Finca.setAdapter(adapter5);

        if (ContextCompat.checkSelfPermission(NewCattleActivity.this, Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(NewCattleActivity.this, Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(NewCattleActivity.this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.CAMERA}, 1000);
        }

        if (getIntent().getSerializableExtra("Info") == null) {
            cattle = new Cattle();
            //editar.setVisibility(View.GONE); revise, muestra error
        } else {
            Cattle item = (Cattle) getIntent().getSerializableExtra("Info");
            TXTcodigo.setText(item.getId() + "");
            TXTedad.setText(item.getAge() + "");
            TXTPeso.setText(item.getWeight() + "");
            Raza.setSelection(obtenerPosicionItem(Raza, NombreRaza(ListaRazas(), item.getIdBreed())));
            Proposito.setSelection(obtenerPosicionItem(Proposito, NombreProposito(ListaPropositos(), item.getIdPurpose())));
            Genero.setSelection(obtenerPosicionItem(Genero, NombreGenero(ListaGeneros(), item.getIdGender())));
            Finca.setSelection(obtenerPosicionItem(Finca, NombreFinca(ListaFincas(), item.getIdLot())));
            TXTcodigo.setEnabled(false);
            TXTedad.setEnabled(false);
            TXTPeso.setEnabled(false);
            Raza.setEnabled(false);
            Proposito.setEnabled(false);
            Genero.setEnabled(false);
            Finca.setEnabled(false);
            button.setEnabled(false);
            registrar.setVisibility(View.GONE);
        }


        //insert a new cattle
      /*  if (myApiService == null)
            myApiService = APIUtils.getAPIService();

        myApiService.insertCattle("insert", "cattle" , getIntent().getIntExtra("phone", 0), cattle).enqueue(new Callback<Boolean>() {
            @Override
            public void onResponse(Call<Boolean> call, Response<Boolean> response) {
                if (response.isSuccessful())
                    Toast.makeText(getApplicationContext(), response.body() ? "Se insertó" : "No se insertó", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(Call<Boolean> call, Throwable t) {
                Toast.makeText(getApplicationContext(), "Error insertando", Toast.LENGTH_LONG).show();
            }
        });*/

    }

    private final int SELECT_PICTURE = 10;

    public void menu(View view) {
        final CharSequence[] options = {"Tomar foto", "Elegir de galeria", "Cancelar"};
        final AlertDialog.Builder builder = new AlertDialog.Builder(NewCattleActivity.this);
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

    //Metodo para la validacion de los campos
    public void Registro(View view) {
        if (TXTcodigo.getText().toString().isEmpty() || TXTedad.getText().toString().isEmpty() || TXTPeso.getText().toString().isEmpty() == true) {
            Toast.makeText(this, "Debes ingresar todos los datos", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "Registrado", Toast.LENGTH_SHORT).show();
            Extraccion();
            //Entrar a vista ver Ganado
            Intent i = new Intent(this, ViewCattleActivity.class);
            startActivity(i);
        }
    }

    static final int REQUEST_IMAGE_CAPTURE = 1;

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == RESULT_OK) {
            switch (requestCode) {
                case REQUEST_TAKE_PHOTO:
                    Bundle extras = data.getExtras();
                    Bitmap imageBitmap = (Bitmap) extras.get("data");
                    img.setImageBitmap(imageBitmap);
                    break;
                case SELECT_PICTURE:
                    Uri path = data.getData();
                    InputStream inputStream;
                    try {
                        inputStream = getContentResolver().openInputStream(path);

                        Bitmap bitmap = BitmapFactory.decodeStream(inputStream);
                        img.setImageBitmap(bitmap);
                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                        Toast.makeText(this, "Unale to open image", Toast.LENGTH_LONG).show();
                    }
                    img.setImageURI(path);
                    break;
            }
        }
    }

    public void Editar(View view) {
        registrar = (Button) findViewById(R.id.Registrar);
       // FloatingActionButton editar = (FloatingActionButton) findViewById(R.id.FABEditar); //Revisar
        //editar.setVisibility(View.GONE);
        registrar.setVisibility(View.VISIBLE);
        TXTcodigo.setEnabled(true);
        TXTedad.setEnabled(true);
        TXTPeso.setEnabled(true);
        Raza.setEnabled(true);
        Proposito.setEnabled(true);
        Genero.setEnabled(true);
        Finca.setEnabled(true);
        button.setEnabled(true);
    }


    public Cattle Extraccion() {
        Cattle nuevo = new Cattle();
        nuevo.setAge(R.id.EdadGanado);
        nuevo.setIdBreed(R.id.RazaTexto);
        nuevo.setIdPurpose(R.id.Proposito);
        nuevo.setIdGender(R.id.Genero);
        nuevo.setWeight(Double.valueOf(R.id.Peso));
        ImageView imagen = (ImageView) findViewById(R.id.ivImagen);
        //Bitmap hola= Utilities.ImageViewtoByte(imagen);
        //nuevo.setFoto(Utilities.ImageViewtoByte(imagen));
        return nuevo;
    }

    public static int obtenerPosicionItem(Spinner spinner, String validacion) {
        //Creamos la variable posicion y lo inicializamos en 0
        int posicion = 0;
        //Recorre el spinner en busca del ítem que coincida con el parametro `String fruta`
        //que lo pasaremos posteriormente
        for (int i = 0; i < spinner.getCount(); i++) {
            //Almacena la posición del ítem que coincida con la búsqueda
            if (spinner.getItemAtPosition(i).toString().equalsIgnoreCase(validacion)) {
                posicion = i;
            }
        }
        //Devuelve un valor entero (si encontro una coincidencia devuelve la
        // posición 0 o N, de lo contrario devuelve 0 = posición inicial)
        return posicion;
    }

    public String NombreRaza(ArrayList InfoRazas, int id) {
        String datos = null;
        for (int i = 0; i < InfoRazas.size(); i++) {
            if (((Breed) InfoRazas.get(i)).getId() == id) {
                datos = ((Breed) InfoRazas.get(i)).getName();
                return datos;
            }
        }
        return datos;
    }

    public String NombreProposito(ArrayList InfoProposito, int id) {
        String datos = null;
        for (int i = 0; i < InfoProposito.size(); i++) {
            if (((Purpose) InfoProposito.get(i)).getId() == id) {
                datos = ((Purpose) InfoProposito.get(i)).getName();
                return datos;
            }
        }
        return datos;
    }

    public String NombreGenero(ArrayList InfoGenero, int id) {
        String datos = null;
        for (int i = 0; i < InfoGenero.size(); i++) {
            if (((Gender) InfoGenero.get(i)).getId() == id) {
                datos = ((Gender) InfoGenero.get(i)).getName();
                return datos;
            }
        }
        return datos;
    }

    public String NombreFinca(ArrayList InfoFinca, int id) {
        String datos = null;
        for (int i = 0; i < InfoFinca.size(); i++) {
            if (((Estate) InfoFinca.get(i)).getId() == id) {
                datos = ((Estate) InfoFinca.get(i)).getName();
                return datos;
            }
        }
        return datos;
    }
}
