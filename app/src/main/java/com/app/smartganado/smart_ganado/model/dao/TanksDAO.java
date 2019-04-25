package com.app.smartganado.smart_ganado.model.dao;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import com.app.smartganado.smart_ganado.model.vo.Tank;
import com.app.smartganado.smart_ganado.remote.APIUtils;
import com.app.smartganado.smart_ganado.view.NewTankActivity;
import com.app.smartganado.smart_ganado.view.adapter.TankAdapter;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TanksDAO {


    private static List<Tank> tankList;

    static {
        tankList = new ArrayList<>();
    }

    public static List<Tank> getTankList() {
        return tankList;
    }

    public static void getTanks(Long phone, final TankAdapter arrayAdapter) {
        APIUtils.getAPIService().getTank("getAll", phone).enqueue(new Callback<List<Tank>>() {
            @Override
            public void onResponse(Call<List<Tank>> call, Response<List<Tank>> response) {
                if (response.isSuccessful()) {
                    if (response.body() != null) {
                        tankList.clear();//Clear list
                        tankList.addAll(response.body());

                        arrayAdapter.notifyDataSetChanged();
                    } else
                        call.clone().enqueue(this);
                } else Log.i("server", "response no successful");
            }

            @Override
            public void onFailure(Call<List<Tank>> call, Throwable t) {
                Log.i("server", t.getMessage());
            }
        });


    }

    public static void insertTank(final NewTankActivity app, Tank tank) {

        APIUtils.getAPIService().insertTank("insert", tank).enqueue(new Callback<Boolean>() {
            @Override
            public void onResponse(Call<Boolean> call, Response<Boolean> response) {
                if (response.isSuccessful()) {
                    if (response.body() != null) {
                        Toast.makeText(app.getApplicationContext(), response.body() ? "Se ha creado el nuevo registro" :
                                "Error creando tanque...", Toast.LENGTH_SHORT).show();

                        if (response.body())
                            app.onBackPressed();

                    } else call.clone().enqueue(this);
                } else Log.i("server", "response no successful");
            }

            @Override
            public void onFailure(Call<Boolean> call, Throwable t) {
                Toast.makeText(app.getApplicationContext(), "error: " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    public static void deleteTank(final Context context, int id) {
        APIUtils.getAPIService().deleteTank("delete", String.valueOf(id)).enqueue(new Callback<Boolean>() {
            @Override
            public void onResponse(Call<Boolean> call, Response<Boolean> response) {
                if (response.isSuccessful()) {
                    if (response.body()) {
                        Toast.makeText(context, "Se eliminó el tanque correctamente", Toast.LENGTH_LONG).show();
                    } else
                        Toast.makeText(context, "Ocurrió un problema, intenta mas tarde", Toast.LENGTH_LONG).show();
                } else Log.i("server", "no successful");
            }

            @Override
            public void onFailure(Call<Boolean> call, Throwable t) {
                Log.i("server", "error: " + t.getMessage());
            }
        });
    }
}
