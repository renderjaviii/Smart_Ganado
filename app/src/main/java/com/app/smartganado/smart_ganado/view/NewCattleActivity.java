package com.app.smartganado.smart_ganado.view;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.app.smartganado.smart_ganado.R;
import com.app.smartganado.smart_ganado.model.Breed;
import com.app.smartganado.smart_ganado.model.Cattle;
import com.app.smartganado.smart_ganado.model.CattleStoryBook;
import com.app.smartganado.smart_ganado.model.Estate;
import com.app.smartganado.smart_ganado.model.Gender;
import com.app.smartganado.smart_ganado.model.Purpose;
import com.app.smartganado.smart_ganado.remote.APIService;
import com.app.smartganado.smart_ganado.remote.APIUtils;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class NewCattleActivity extends AppCompatActivity {
    ArrayList<Estate> InfoFincas  = new ArrayList<Estate>();
    ArrayList<Gender> InfoGeneros = new ArrayList<Gender>();
    ArrayList<Breed> InfoRazas = new ArrayList<Breed>();
    ArrayList<Purpose> InfoPropositos = new ArrayList<Purpose>();
    Spinner Finca,Lote, Raza, Proposito, Genero;
    EditText TXTcodigo, TXTedad, TXTPeso;
    FloatingActionButton editar;
    Button registrar;
    Cattle cattle;
    public APIService myApiService;
    private int invisible;


    public ArrayList ListaFincas(){
        ArrayList datos = new ArrayList();
        Estate finca;
        for (int i=0; i<=5; i++){
            finca = new Estate();
            finca.setId(i);
            finca.setNombre("Finca: "+i);
            finca.setArea(2000*i);
            finca.setUbicacion("ubicacion "+i);
            datos.add(finca);
        }
        return datos;
    }
    public ArrayList ListaGeneros(){
        ArrayList datos = new ArrayList();
        Gender genero;
        for (int i=0; i<=5; i++){
            genero = new Gender();
            genero.setId(i);
            genero.setNombre("generos: "+i);
            datos.add(genero);
        }
        return datos;
    }
    public ArrayList ListaRazas(){
        ArrayList datos = new ArrayList();
        Breed raza;
        for (int i=0; i<=5; i++){
            raza = new Breed();
            raza.setId(i);
            raza.setNombre("Raza: "+i);
            datos.add(raza);
        }
        return datos;
    }
    public ArrayList ListaPropositos(){
        ArrayList datos = new ArrayList();
        Purpose proposito;
        for (int i=0; i<=5; i++){
            proposito = new Purpose();
            proposito.setId(i);
            proposito.setNombre("proposito: "+i);
            datos.add(proposito);
        }
        return datos;
    }

    public ArrayList NombreFincas(ArrayList<Estate> lista){
        ArrayList<String> listaNombres = new ArrayList<String>();
        for (int i=0; i<lista.size(); i++){
            listaNombres.add(((Estate)(lista.get(i))).getNombre());
        }
        return listaNombres;
    }
    public ArrayList NombreGeneros(ArrayList<Gender> lista){
        ArrayList<String> listaNombres = new ArrayList<String>();
        for (int i=0; i<lista.size(); i++){
            listaNombres.add(((Gender)(lista.get(i))).getNombre());
        }
        return listaNombres;
    }
    public ArrayList NombreRazas(ArrayList<Breed> lista){
        ArrayList<String> listaNombres = new ArrayList<String>();
        for (int i=0; i<lista.size(); i++){
            listaNombres.add(((Breed)(lista.get(i))).getNombre());
        }
        return listaNombres;
    }
    public ArrayList NombrePropositos(ArrayList<Purpose> lista){
        ArrayList<String> listaNombres = new ArrayList<String>();
        for (int i=0; i<lista.size(); i++){
            listaNombres.add(((Purpose)(lista.get(i))).getNombre());
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
        TXTcodigo = (EditText)findViewById(R.id.Codigo);
        TXTedad = (EditText)findViewById(R.id.EdadGanado);
        TXTPeso = (EditText)findViewById(R.id.Peso);
        editar = (FloatingActionButton)findViewById(R.id.FABEditar);
        registrar = (Button)findViewById(R.id.Registrar);

        InfoFincas=ListaFincas();
        InfoGeneros=ListaGeneros();
        InfoPropositos=ListaPropositos();
        InfoRazas=ListaRazas();

        ArrayAdapter<CharSequence> adapter2 = new ArrayAdapter(this, android.R.layout.simple_spinner_item,NombreRazas(InfoRazas));
        Raza.setAdapter(adapter2);

        ArrayAdapter<CharSequence> adapter3 =new ArrayAdapter(this, android.R.layout.simple_spinner_item,NombrePropositos(InfoPropositos));
        Proposito.setAdapter(adapter3);

        ArrayAdapter<CharSequence> adapter4 = new ArrayAdapter(this, android.R.layout.simple_spinner_item,NombreGeneros(InfoGeneros));
        Genero.setAdapter(adapter4);

        ArrayAdapter<CharSequence> adapter5 = new ArrayAdapter(this, android.R.layout.simple_spinner_item,NombreFincas(InfoFincas));
        Finca.setAdapter(adapter5);




        if(getIntent().getSerializableExtra("Info")==null){
            cattle = new Cattle();
            editar.setVisibility(View.GONE);


        }else{
            Cattle item = (Cattle) getIntent().getSerializableExtra("Info");
            TXTcodigo.setText(item.getId()+"");
            TXTedad.setText(item.getEdad()+"");
            TXTPeso.setText(item.getPeso()+"");
            Raza.setSelection(obtenerPosicionItem(Raza, NombreRaza(ListaRazas(),item.getId_Raza())));
            Proposito.setSelection(obtenerPosicionItem(Proposito, NombreProposito(ListaPropositos(),item.getId_Proposito())));
            Genero.setSelection(obtenerPosicionItem(Genero, NombreGenero(ListaGeneros(),item.getId_Genero())));
            Finca.setSelection(obtenerPosicionItem(Finca, NombreFinca(ListaFincas(),item.getId_Lote())));
            TXTcodigo.setEnabled(false);
            TXTedad.setEnabled(false);
            TXTPeso.setEnabled(false);
            Raza.setEnabled(false);
            Proposito.setEnabled(false);
            Genero.setEnabled(false);
            Finca.setEnabled(false);
            registrar.setVisibility(View.GONE);
        }



        //insert a new cattle
        if (myApiService == null)
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
        });

    }

    //Metodo para la validacion de los campos
    public void Registro(View view) {
        if (TXTcodigo.getText().toString().isEmpty() || TXTedad.getText().toString().isEmpty() || TXTPeso.getText().toString().isEmpty()==true) {
            Toast.makeText(this, "Debes ingresar todos los datos", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "Registrado", Toast.LENGTH_SHORT).show();
            Extraccion();
            //Entrar a vista ver Ganado
            Intent i = new Intent(this, ViewCattleActivity.class);
            startActivity(i);
        }
    }

    public void Editar(View view) {
        registrar = (Button)findViewById(R.id.Registrar);
        registrar.setVisibility(View.VISIBLE);
        TXTcodigo.setEnabled(true);
        TXTedad.setEnabled(true);
        TXTPeso.setEnabled(true);
        Raza.setEnabled(true);
        Proposito.setEnabled(true);
        Genero.setEnabled(true);
        Finca.setEnabled(true);
    }


    public Cattle Extraccion (){
        Cattle nuevo = new Cattle();
        nuevo.setEdad(R.id.EdadGanado);
        nuevo.setId_Raza(R.id.RazaTexto);
        nuevo.setId_Proposito(R.id.Proposito);
        nuevo.setId_Genero(R.id.Genero);
        nuevo.setEdad(R.id.EdadGanado);
        nuevo.setPeso(R.id.Peso);
        System.out.println(nuevo.toString());
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

    public String NombreRaza(ArrayList InfoRazas, int id){
        String datos =null;
        for (int i=0; i<InfoRazas.size(); i++){
            if (((Breed)InfoRazas.get(i)).getId()==id){
                datos=((Breed)InfoRazas.get(i)).getNombre();
                return datos;
            }
        }
        return datos;
    }
    public String NombreProposito(ArrayList InfoProposito, int id){
        String datos=null;
        for (int i=0; i<InfoProposito.size(); i++){
            if (((Purpose)InfoProposito.get(i)).getId()==id){
                datos=((Purpose)InfoProposito.get(i)).getNombre();
                return datos;
            }
        }
        return datos;
    }
    public String NombreGenero(ArrayList InfoGenero, int id){
        String datos=null;
        for (int i=0; i<InfoGenero.size(); i++){
            if (((Gender)InfoGenero.get(i)).getId()==id){
                datos=((Gender)InfoGenero.get(i)).getNombre();
                return datos;
            }
        }
        return datos;
    }
    public String NombreFinca(ArrayList InfoFinca, int id){
        String datos=null;
        for (int i=0; i<InfoFinca.size(); i++){
            if (((Estate)InfoFinca.get(i)).getId()==id){
                datos=((Estate)InfoFinca.get(i)).getNombre();
                return datos;
            }
        }
        return datos;
    }

}
