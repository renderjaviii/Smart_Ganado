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
import com.app.smartganado.smart_ganado.model.vo.UserApp;
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
