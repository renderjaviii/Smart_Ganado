package com.app.smartganado.smart_ganado.view;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.app.smartganado.smart_ganado.R;
import com.app.smartganado.smart_ganado.model.Estate;
import com.app.smartganado.smart_ganado.remote.APIService;
import com.app.smartganado.smart_ganado.remote.APIUtils;

import java.net.Socket;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class NewEstateActivity extends AppCompatActivity {

    EditText Nombre_Finca;
    EditText Tamaño_Finca;
    EditText Direccion_Finca;
    TextView a;
    public APIService myApiService;


    private static Socket s;
    String msg = "";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_estate);

        Nombre_Finca = (EditText) findViewById(R.id.Nombre_Finca);
        Tamaño_Finca = (EditText) findViewById(R.id.Tamaño_Finca);
        Direccion_Finca = (EditText) findViewById(R.id.Direccion_Finca);
        a = (TextView) findViewById(R.id.textView16);

    }

    public void onClick(View view) {
        msg = Nombre_Finca.getText().toString();
        a.setText(msg);

        Estate estate = new Estate(100,"Finquio",12,"url","");//aqui lee de los componentes

        //insert new estate
        Log.i("server", "Peticion");
        if (myApiService == null)
            myApiService = APIUtils.getAPIService();

        myApiService.insertEstate("insert", "estate", getIntent().getIntExtra("phone", 0), estate).enqueue(new Callback<Boolean>() {
            @Override
            public void onResponse(Call<Boolean> call, Response<Boolean> response) {
                Toast.makeText(getApplicationContext(), response.body() ? "Se insertó" : "No se insertó", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(Call<Boolean> call, Throwable t) {
                Toast.makeText(getApplicationContext(), "Error insertando", Toast.LENGTH_LONG).show();

            }
        });
    }


}
