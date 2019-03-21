package com.app.smartganado.smart_ganado.view;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.app.smartganado.smart_ganado.R;
import com.app.smartganado.smart_ganado.model.Cattle;
import com.app.smartganado.smart_ganado.model.CattleStoryBook;
import com.app.smartganado.smart_ganado.remote.APIService;
import com.app.smartganado.smart_ganado.remote.APIUtils;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class NewCattleActivity extends AppCompatActivity {
    Spinner Finca,Lote, Raza, Proposito, Genero;
    EditText TXTcodigo, TXTedad, TXTPeso;
    Cattle cattle;
    public APIService myApiService;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_cattle);

        //Spinner Finca,Clasificacion, Raza, Proposito, Genero
        Lote = (Spinner) findViewById(R.id.Clasificacion);
        Raza = (Spinner) findViewById(R.id.RazaTexto);
        Proposito = (Spinner) findViewById(R.id.Proposito);
        Genero = (Spinner) findViewById(R.id.Genero);
        Finca = (Spinner) findViewById(R.id.Finca);
        TXTcodigo = (EditText)findViewById(R.id.Codigo);
        TXTedad = (EditText)findViewById(R.id.EdadGanado);
        TXTPeso = (EditText)findViewById(R.id.Peso);
        if(getIntent().getSerializableExtra("Info")==null){
            cattle = new Cattle();
        }else{
            Cattle item = (Cattle) getIntent().getSerializableExtra("Info");
            TXTcodigo.setText(item.getId()+"");
            TXTedad.setText(item.getEdad()+"");
            TXTPeso.setText(item.getPeso()+"");


            if (item.getId_Genero()==1){
                String inicializarItem = "Macho";
                Genero.setSelection(obtenerPosicionItem(Genero, inicializarItem));    Genero.setEnabled(false);
            }else{
                String inicializarItem = "Hembra";
                Genero.setSelection(obtenerPosicionItem(Genero, inicializarItem));
                Genero.setEnabled(false);
            }
            TXTcodigo.setEnabled(false);
            TXTedad.setEnabled(false);
            TXTPeso.setEnabled(false);
        }

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.opciones, android.R.layout.simple_spinner_item);
        Lote.setAdapter(adapter);

        ArrayAdapter<CharSequence> adapter2 = ArrayAdapter.createFromResource(this, R.array.Raza, android.R.layout.simple_spinner_item);
        Raza.setAdapter(adapter2);

        ArrayAdapter<CharSequence> adapter3 = ArrayAdapter.createFromResource(this, R.array.Proposito, android.R.layout.simple_spinner_item);
        Proposito.setAdapter(adapter3);

        ArrayAdapter<CharSequence> adapter4 = ArrayAdapter.createFromResource(this, R.array.Genero, android.R.layout.simple_spinner_item);
        Genero.setAdapter(adapter4);

        ArrayAdapter<CharSequence> adapter5 = ArrayAdapter.createFromResource(this, R.array.Finca, android.R.layout.simple_spinner_item);
        Finca.setAdapter(adapter5);

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

    public Cattle Extraccion (){
        Cattle nuevo = new Cattle();
        nuevo.setEdad(R.id.EdadGanado);
        nuevo.setId_Lote(R.id.Clasificacion);
        nuevo.setId_Raza(R.id.RazaTexto);
        nuevo.setId_Proposito(R.id.Proposito);
        nuevo.setId_Genero(R.id.Genero);
        nuevo.setEdad(R.id.EdadGanado);
        nuevo.setPeso(R.id.Peso);
        System.out.println(nuevo.toString());
        return nuevo;
    }

    public static int obtenerPosicionItem(Spinner spinner, String fruta) {
        //Creamos la variable posicion y lo inicializamos en 0
        int posicion = 0;
        //Recorre el spinner en busca del ítem que coincida con el parametro `String fruta`
        //que lo pasaremos posteriormente
        for (int i = 0; i < spinner.getCount(); i++) {
            //Almacena la posición del ítem que coincida con la búsqueda
            if (spinner.getItemAtPosition(i).toString().equalsIgnoreCase(fruta)) {
                posicion = i;
            }
        }
        //Devuelve un valor entero (si encontro una coincidencia devuelve la
        // posición 0 o N, de lo contrario devuelve 0 = posición inicial)
        return posicion;
    }


}
