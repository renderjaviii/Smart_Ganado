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
import com.app.smartganado.smart_ganado.model.vo.User;
import com.app.smartganado.smart_ganado.remote.APIUtils;

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
    }

    public void onLoginUser(View view) {
        User user = new User();
        user.setTelefono(Integer.parseInt(phone.getText().toString()));
        user.setContraseña(password.getText().toString());

        APIUtils.getAPIService().getSession("login", "user", user).enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                if (response.isSuccessful()) {
                    Intent intent = new Intent(getApplicationContext(), HomeActivity.class);
                    intent.putExtra("phone", response.body().getTelefono()); //Enviamos telefono del usuario
                    startActivity(intent);
                    Snackbar.make(getCurrentFocus(), "Bienvenido " + response.body().getNombre() + "!", Snackbar.LENGTH_LONG).show();
                } else
                    Toast.makeText(getApplicationContext(), "Contraseña incorrecta", Toast.LENGTH_SHORT).show();

            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {
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
