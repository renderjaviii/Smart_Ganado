package com.app.smartganado.smart_ganado.view;

import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.app.smartganado.smart_ganado.R;
import com.app.smartganado.smart_ganado.model.vo.Estate;
import com.app.smartganado.smart_ganado.remote.APIService;
import com.app.smartganado.smart_ganado.remote.APIUtils;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class NewEstateActivity extends AppCompatActivity {

    EditText editTNombre, editTArea, editTUbic;
    TextView a;
    public APIService myApiService;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_estate);

        editTNombre = (EditText) findViewById(R.id.Nombre_Finca);
        editTArea = (EditText) findViewById(R.id.Tamaño_Finca);
        editTUbic = (EditText) findViewById(R.id.Direccion_Finca);
        a = (TextView) findViewById(R.id.textView16);

    }

    public void onInsertEstate(View view) {
        Estate estate = new Estate(editTNombre.getText().toString(), Double.parseDouble(editTArea.getText().toString()), "url", editTUbic.getText().toString());//aqui lee de los componentes

        //insert new estate
        Log.i("server", "Peticion");
        if (myApiService == null)
            myApiService = APIUtils.getAPIService();

        myApiService.insertEstate("insert", "estate", 1, estate).enqueue(new Callback<Boolean>() {
            @Override
            public void onResponse(Call<Boolean> call, Response<Boolean> response) {
                Snackbar.make(getCurrentFocus(), response.body() ? "Se insertó" : "No se insertó", Snackbar.LENGTH_LONG).show();
            }

            @Override
            public void onFailure(Call<Boolean> call, Throwable t) {
                Toast.makeText(getApplicationContext(), "Error insertando", Toast.LENGTH_LONG).show();
            }
        });
    }


}
