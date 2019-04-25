package com.app.smartganado.smart_ganado.model.dao;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import com.app.smartganado.smart_ganado.model.vo.Cattle;
import com.app.smartganado.smart_ganado.remote.APIUtils;
import com.app.smartganado.smart_ganado.view.NewCattleActivity;
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
        APIUtils.getAPIService().getCattle("getAll", phone).enqueue(new Callback<List<Cattle>>() {
            @Override
            public void onResponse(Call<List<Cattle>> call, Response<List<Cattle>> response) {
                if (response.isSuccessful()) {
                    if (response.body() != null) {

                        Log.i("server", "cattleList isEmpty? " + cattleList.isEmpty());

                        cattleList.clear();
                        cattleList.addAll(response.body());
                        arrayAdapter.notifyDataSetChanged();

                    } else
                        call.clone().enqueue(this);//Recalling

                } else Log.i("server", "response no successful");
            }

            @Override
            public void onFailure(Call<List<Cattle>> call, Throwable t) {
                Log.i("server", t.getMessage());
            }
        });


    }

    public static void insertCattle(final NewCattleActivity app, Cattle cattle) {

        APIUtils.getAPIService().insertCattle("insert", cattle).enqueue(new Callback<Boolean>() {
            @Override
            public void onResponse(Call<Boolean> call, Response<Boolean> response) {
                if (response.isSuccessful()) {
                    Toast.makeText(app, response.body() ? "Cabeza de ganado creada correctamente" :
                            "ERROR: Código ya asociado en la finca seleccionada...", Toast.LENGTH_SHORT).show();

                    if (response.body())
                        app.onBackPressed();
                }
            }

            @Override
            public void onFailure(Call<Boolean> call, Throwable t) {
                Log.i("server", "error: " + t.getMessage());
            }
        });
    }

    public static void deleteCattle(final Context context, final CattleAdapter adapter, int id) {
        APIUtils.getAPIService().deleteCattle("delete", String.valueOf(id)).enqueue(new Callback<Boolean>() {
            @Override
            public void onResponse(Call<Boolean> call, Response<Boolean> response) {
                if (response.isSuccessful()) {
                    if (response.body()) {
                        Toast.makeText(context, "Se eliminó el ganado correctamente", Toast.LENGTH_LONG).show();
                        adapter.notifyDataSetChanged();
                    } else
                        Toast.makeText(context, "Ocurrió un problema eliminando, intentalo nuevamente", Toast.LENGTH_LONG).show();

                } else Log.i("server", "no successful");
            }

            @Override
            public void onFailure(Call<Boolean> call, Throwable t) {
                Log.i("server", "error: " + t.getMessage());
            }
        });
    }

    public static void getCattlesByEstate(int idEstate) {
        APIUtils.getAPIService().getCattlesByEstate("getAllByEstate", String.valueOf(idEstate)).enqueue(new Callback<List<Cattle>>() {
            @Override
            public void onResponse(Call<List<Cattle>> call, Response<List<Cattle>> response) {
                if (response.isSuccessful()) {
                    if (response.body() != null) {

                        Log.i("server", "cattleList isEmpty? " + cattleList.isEmpty());

                        cattleList.clear();
                        cattleList.addAll(response.body());
                        //    pieChart.notifyDataSetChanged();

                        Log.i("server", "cattleList isEmpty already? " + cattleList.isEmpty());

                    } else
                        call.clone().enqueue(this);//Recalling

                } else Log.i("server", "response no successful");
            }

            @Override
            public void onFailure(Call<List<Cattle>> call, Throwable t) {
                Log.i("server", t.getMessage());
            }
        });


    }

}
