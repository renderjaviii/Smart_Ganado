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
import com.app.smartganado.smart_ganado.model.vo.Cattle;
import com.app.smartganado.smart_ganado.remote.APIService;
import com.app.smartganado.smart_ganado.remote.APIUtils;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class NewCattleActivity extends AppCompatActivity {
    Spinner opciones, Raza, Proposito, Genero;
    EditText TXTcodigo, TXTedad, TXTPeso;

    public APIService myApiService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_cattle);

        //Spinner Clasificacion, Raza, Proposito, Genero
        opciones = (Spinner) findViewById(R.id.Clasificacion);
        Raza = (Spinner) findViewById(R.id.RazaTexto);
        Proposito = (Spinner) findViewById(R.id.Proposito);
        Genero = (Spinner) findViewById(R.id.Genero);

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.opciones, android.R.layout.simple_spinner_item);
        opciones.setAdapter(adapter);

        ArrayAdapter<CharSequence> adapter2 = ArrayAdapter.createFromResource(this, R.array.Raza, android.R.layout.simple_spinner_item);
        Raza.setAdapter(adapter2);

        ArrayAdapter<CharSequence> adapter3 = ArrayAdapter.createFromResource(this, R.array.Proposito, android.R.layout.simple_spinner_item);
        Proposito.setAdapter(adapter3);

        ArrayAdapter<CharSequence> adapter4 = ArrayAdapter.createFromResource(this, R.array.Genero, android.R.layout.simple_spinner_item);
        Genero.setAdapter(adapter4);

        ArrayAdapter<CharSequence> adapter5 = ArrayAdapter.createFromResource(this, R.array.Finca, android.R.layout.simple_spinner_item);
        Genero.setAdapter(adapter5);


        //TXT Codigo, edad, Peso
        TXTcodigo = (EditText) findViewById(R.id.Codigo);
        TXTedad = (EditText) findViewById(R.id.EdadGanado);
        TXTPeso = (EditText) findViewById(R.id.Peso);


        Cattle cattle = new Cattle(1, "Vaca leal", 1, 1, 1, 1, 1, 1, "url", "");
        //insert a new cattle
        if (myApiService == null)
            myApiService = APIUtils.getAPIService();

        myApiService.insertCattle("insert", "cattle" , 1, cattle).enqueue(new Callback<Boolean>() {
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

    //Metodo para el mensaje del ButtomImage
    public void MensajePrueba(View view) {
        Toast.makeText(this, "Cambiar imagen", Toast.LENGTH_SHORT).show();
    }

    //Metodo para la validacion de los campos
    public void Registro(View view) {
        if (TXTcodigo.getText().toString().isEmpty() || TXTedad.getText().toString().isEmpty() || TXTPeso.getText().toString().isEmpty()) {
            Toast.makeText(this, "Debes ingresar todos los datos", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "Registrado", Toast.LENGTH_SHORT).show();
        }


        //Entrar a vista ver Ganado
        Intent i = new Intent(this, ViewCattleActivity.class);
        startActivity(i);
    }

}
