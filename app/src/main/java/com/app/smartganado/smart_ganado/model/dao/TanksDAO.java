package com.app.smartganado.smart_ganado.model.dao;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import com.app.smartganado.smart_ganado.model.vo.Cattle;
import com.app.smartganado.smart_ganado.model.vo.Tank;
import com.app.smartganado.smart_ganado.remote.APIUtils;
import com.app.smartganado.smart_ganado.view.adapter.CattleAdapter;
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
        Log.i("server", "peticion get Tanks");

        tankList.clear();//Clear list

        APIUtils.getAPIService().getTank("getAll", phone).enqueue(new Callback<List<Tank>>() {
            @Override
            public void onResponse(Call<List<Tank>> call, Response<List<Tank>> response) {
                if (response.isSuccessful()) {
                    if (response.body() != null) {
                        for (Tank tank : response.body()) {
                            tankList.add(tank);
                            Log.i("server", tank.toString());
                        }
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

    public static void insertTank(final Context appContext, Tank tank) {

        APIUtils.getAPIService().insertTank("insert", tank).enqueue(new Callback<Boolean>() {
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
