package com.app.smartganado.smart_ganado.model.dao;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import com.app.smartganado.smart_ganado.model.vo.Cattle;
import com.app.smartganado.smart_ganado.remote.APIUtils;
import com.app.smartganado.smart_ganado.view.adapter.CattleAdapter;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CattleDAO {


    private static List<Cattle> cattleList;

    static {
        cattleList = new ArrayList<>();
    }

    public static List<Cattle> getCattleList() {
        return cattleList;
    }


    public static void getCattles(Long phone, final CattleAdapter arrayAdapter) {
        Log.i("server", "peticion get cattles");

        cattleList.clear();//Clear list

        APIUtils.getAPIService().getCattle("getAll", phone).enqueue(new Callback<List<Cattle>>() {
            @Override
            public void onResponse(Call<List<Cattle>> call, Response<List<Cattle>> response) {
                if (response.isSuccessful()) {
                    if (response.body() != null) {
                        for (Cattle cattle : response.body()) {
                            cattleList.add(cattle);
                            Log.i("server", cattle.toString());
                        }
                        arrayAdapter.notifyDataSetChanged();
                    } else
                        call.clone().enqueue(this);

                } else Log.i("server", "response no successful");
            }

            @Override
            public void onFailure(Call<List<Cattle>> call, Throwable t) {
                Log.i("server", t.getMessage());
            }
        });


    }

    public static void insertCattle(final Context appContext, Cattle cattle) {

        APIUtils.getAPIService().insertCattle("insert", cattle).enqueue(new Callback<Boolean>() {
            @Override
            public void onResponse(Call<Boolean> call, Response<Boolean> response) {
                if (response.isSuccessful())
                    Toast.makeText(appContext, response.body() ? "Se ha creado el nuevo registro" : "Error creando...", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(Call<Boolean> call, Throwable t) {
                Toast.makeText(appContext, "error: " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

}
