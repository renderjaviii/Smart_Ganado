package com.app.smartganado.smart_ganado.view;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.app.smartganado.smart_ganado.R;
import com.app.smartganado.smart_ganado.model.vo.Breed;
import com.app.smartganado.smart_ganado.model.vo.Lot;
import com.app.smartganado.smart_ganado.model.vo.Purpose;
import com.app.smartganado.smart_ganado.model.vo.UserApp;
import com.app.smartganado.smart_ganado.remote.APIUtils;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class LoginActivity extends AppCompatActivity {

    EditText phone, password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        phone = (EditText) findViewById(R.id.Phone_Login);
        password = (EditText) findViewById(R.id.Password_Login);

        //Test db
        APIUtils.getAPIService().getLot().enqueue(new Callback<List<Lot>>() {
            @Override
            public void onResponse(Call<List<Lot>> call, Response<List<Lot>> response) {
                if (response.isSuccessful()) {
                    if (response.body() != null)
                        for (Lot lot : response.body())
                            Log.i("server", lot.toString());
                } else Log.i("server", "reponse no sucessful");

            }

            @Override
            public void onFailure(Call<List<Lot>> call, Throwable t) {
                Log.i("server", "error: " + t.getMessage());
            }
        });

        //Test db
        APIUtils.getAPIService().getPurpose().enqueue(new Callback<List<Purpose>>() {
            @Override
            public void onResponse(Call<List<Purpose>> call, Response<List<Purpose>> response) {
                if (response.isSuccessful()) {
                    if (response.body() != null)
                        for (Purpose purpose : response.body())
                            Log.i("server", purpose.toString());
                } else Log.i("server", "reponse no sucessful");

            }

            @Override
            public void onFailure(Call<List<Purpose>> call, Throwable t) {
                Log.i("server", "error: " + t.getMessage());
            }
        });


        //Test db
        APIUtils.getAPIService().getBreed().enqueue(new Callback<List<Breed>>() {
            @Override
            public void onResponse(Call<List<Breed>> call, Response<List<Breed>> response) {
                if (response.isSuccessful()) {
                    if (response.body() != null)
                        for (Breed breed : response.body())
                            Log.i("server", breed.toString());
                } else Log.i("server", "reponse no sucessful");


            }

            @Override
            public void onFailure(Call<List<Breed>> call, Throwable t) {
                Log.i("server", "error: " + t.getMessage());
            }
        });
    }

    public void onLoginUser(View view) {
        UserApp user = new UserApp();
        user.setPhone(Long.parseLong(phone.getText().toString()));
        user.setPassword(password.getText().toString());

        APIUtils.getAPIService().getLogin(user).enqueue(new Callback<UserApp>() {
            @Override
            public void onResponse(Call<UserApp> call, Response<UserApp> response) {
                if (response.isSuccessful()) {
                    if (response.body() != null) {
                        Intent intent = new Intent(getApplicationContext(), HomeActivity.class);
                        intent.putExtra("phone", response.body().getPhone()); //Enviamos telefono del usuario
                        Snackbar.make(getCurrentFocus(), "Bienvenido " + response.body().getName() + "!", Snackbar.LENGTH_LONG).show();
                        startActivity(intent);
                    } else
                        Toast.makeText(getApplicationContext(), "Contraseña incorrecta", Toast.LENGTH_SHORT).show();
                } else
                    Toast.makeText(getApplicationContext(), "Error", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(Call<UserApp> call, Throwable t) {
                Log.i("server", "error: " + t.getMessage());
            }
        });

    }

    public void onRegistration(View view) {
        Intent intent = new Intent(getApplicationContext(), NewUserActivity.class);
        startActivity(intent);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }

}
