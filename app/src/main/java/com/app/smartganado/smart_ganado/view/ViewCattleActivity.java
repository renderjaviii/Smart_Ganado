package com.app.smartganado.smart_ganado.view;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import com.app.smartganado.smart_ganado.R;
import com.app.smartganado.smart_ganado.view.adapter.CattleAdapter;

public class ViewCattleActivity extends AppCompatActivity {
    ListView ListaGanados;
    String[][] datos={
            {"1234","edad", "Proposito", "Genero", "Tipo","Raza","Descripcion"},
            {"4321","edad", "Proposito", "Genero", "Tipo","Raza","Descripcion"},
            {"2468","edad", "Proposito", "Genero", "Tipo","Raza","Descripcion"},
            {"4567a","edad", "Proposito", "Genero", "Tipo","Raza","Descripcion"},
    };
    int[] datosImg = {R.drawable.vaca1, R.drawable.vaca2, R.drawable.vaca3, R.drawable.vaca4};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_cattle);

        ListaGanados = (ListView)findViewById(R.id.listaGanado);

        ListaGanados.setAdapter(new CattleAdapter(this,datos,datosImg));

    }
}
