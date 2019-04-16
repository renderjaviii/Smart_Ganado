package com.app.smartganado.smart_ganado.model.dao;

import android.app.Application;
import android.content.Intent;
import android.support.design.widget.Snackbar;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.app.smartganado.smart_ganado.model.vo.UserApp;
import com.app.smartganado.smart_ganado.remote.APIUtils;
import com.app.smartganado.smart_ganado.view.HomeActivity;
import com.app.smartganado.smart_ganado.view.LoginActivity;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UserAppDAO {

    private static UserApp userApp;

    public static UserApp getUser() {
        return userApp;
    }

    public static void insertUser(final Application appComp, UserApp user) {
        APIUtils.getAPIService().insertUser("insert", user).enqueue(new Callback<Boolean>() {
            @Override
            public void onResponse(Call<Boolean> call, Response<Boolean> response) {
                if (response.isSuccessful()) {
                    Toast.makeText(appComp.getApplicationContext(), response.body() ? "Registro exitoso" : "Número ya asociado a otra cuenta...", Toast.LENGTH_LONG).show();

                    if (response.body()) //If the insertions is successful going to login
                        appComp.startActivity(new Intent(appComp.getApplicationContext(), LoginActivity.class));
                }
            }

            @Override
            public void onFailure(Call<Boolean> call, Throwable t) {
                Log.i("server", "failure: " + t.getMessage());
            }
        });
    }

    public static void tryLogin(final View view, final Application appComp, UserApp user) {
        APIUtils.getAPIService().getLogin(user).enqueue(new Callback<UserApp>() {
            @Override
            public void onResponse(Call<UserApp> call, Response<UserApp> response) {
                if (response.isSuccessful()) {
                    if (response.body() != null) {
                        userApp = response.body();

                        Intent intent = new Intent(appComp.getApplicationContext(), HomeActivity.class);
                        intent.putExtra("user", userApp);//Sending user info to home activity
                        appComp.startActivity(intent);
                    } else
                        Snackbar.make(view, "No dudamos que seas tú, pero intenta nuevamente...", Snackbar.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<UserApp> call, Throwable t) {
                Log.i("server", "error: " + t.getMessage());
            }
        });
    }
}
