package com.app.smartganado.smart_ganado.view;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ListView;

import com.app.smartganado.smart_ganado.R;
import com.app.smartganado.smart_ganado.model.vo.Cattle;
import com.app.smartganado.smart_ganado.model.vo.UserApp;
import com.app.smartganado.smart_ganado.remote.APIService;
import com.app.smartganado.smart_ganado.remote.APIUtils;
import com.app.smartganado.smart_ganado.view.adapter.CattleAdapter;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ViewCattleActivity extends AppCompatActivity {
    FloatingActionButton FABAgregar_Ganado;
    ListView ListaGanados;
    String[][] datos;

    private List<Cattle> cattleList;
    int[] datosImg = {R.drawable.vaca1, R.drawable.vaca2, R.drawable.vaca3, R.drawable.vaca4};
    private APIService myApiService;

    private CattleAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_view_cattle);
        ListaGanados = (ListView) findViewById(R.id.listaGanado);


        datos = new String[4][4];
        adapter = new CattleAdapter(this, datos, datosImg);
        ListaGanados.setAdapter(adapter);

        FABAgregar_Ganado = findViewById(R.id.Agregar_Ganado);
        FABAgregar_Ganado.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent miIntent = new Intent(ViewCattleActivity.this, NewCattleActivity.class);
                startActivity(miIntent);
            }
        });
        init();
    }

    private void init() {
        UserApp user = new UserApp();
        user.setPhone(1234l);
        Log.i("server", "peticion cattle");

        APIUtils.getAPIService().getCattle("getAll", user.getPhone()).enqueue(new Callback<List<Cattle>>() {
            @Override
            public void onResponse(Call<List<Cattle>> call, Response<List<Cattle>> response) {
                if (response.isSuccessful()) { //Se valida que la respuesta sea correcta
                    int pos = 1;
                    for (Cattle cattle : response.body()) {
                        datos[pos++][0] = cattle.getName();
                        Log.i("server", cattle.toString()
                        );
                    }
                    adapter.notifyDataSetChanged();


                } else Log.i("server", "Error on response");
            }

            @Override
            public void onFailure(Call<List<Cattle>> call, Throwable t) {
                Log.i("server", t.getMessage());
            }
        });


    }

    //Open insertar finca
    public void onNewCattle(View view) {
        Intent intent = new Intent(getApplicationContext(), NewCattleActivity.class);
        startActivity(intent);
    }
}
