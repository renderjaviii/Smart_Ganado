package com.app.smartganado.smart_ganado.model.dao;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Toast;

import com.app.smartganado.smart_ganado.model.vo.UserApp;
import com.app.smartganado.smart_ganado.remote.APIUtils;
import com.app.smartganado.smart_ganado.utilities.SHA512Hash;
import com.app.smartganado.smart_ganado.view.HomeActivity;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UserAppDAO {

    private UserApp userApp;

    public UserApp getUser() {
        return userApp;
    }

    public static void insertUser(final Context context, UserApp user) {
        user.setPassword(SHA512Hash.encryptThisString(user.getPassword()));//Encoding password

        APIUtils.getAPIService().insertUser("insert", user).enqueue(new Callback<Boolean>() {
            @Override
            public void onResponse(Call<Boolean> call, Response<Boolean> response) {
                if (response.isSuccessful())
                    Toast.makeText(context, response.body() ? "Registro exitoso" : "Número ya asociado a otra cuenta...", Toast.LENGTH_LONG).show();
            }

            @Override
            public void onFailure(Call<Boolean> call, Throwable t) {
                Log.i("server", "failure: " + t.getMessage());
            }
        });
    }

    public void tryLogin(final Context context, UserApp user) {
        APIUtils.getAPIService().getLogin(user).enqueue(new Callback<UserApp>() {
            @Override
            public void onResponse(Call<UserApp> call, Response<UserApp> response) {
                if (response.isSuccessful()) {
                    if (response.body() != null) {
                        userApp = response.body();
                        Intent intent = new Intent(context, HomeActivity.class);
                        intent.putExtra("user", response.body());

                        AppCompatActivity a = new AppCompatActivity();
                        a.startActivity(intent);

                        Toast.makeText(context, "Sabemos que eres tú, pero intenta nuevamente...", Toast.LENGTH_SHORT).show();
                    } else
                        Toast.makeText(context, "Error", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<UserApp> call, Throwable t) {
                Log.i("server", "error: " + t.getMessage());
            }
        });
    }
}
