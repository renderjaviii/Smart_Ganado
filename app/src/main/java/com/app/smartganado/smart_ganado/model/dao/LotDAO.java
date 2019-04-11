package com.app.smartganado.smart_ganado.model.dao;

import android.util.Log;
import android.widget.ArrayAdapter;

import com.app.smartganado.smart_ganado.model.vo.Lot;
import com.app.smartganado.smart_ganado.remote.APIUtils;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LotDAO {

    private static List<Lot> lotList;

    static {
        lotList = new ArrayList<>();
    }

    public static List<Lot> getLotList() {
        return lotList;
    }

    //GET Lots from database
    public static void getLotList(final ArrayAdapter<Lot> arrayAdapter) {
        APIUtils.getAPIService().getLot().enqueue(new Callback<List<Lot>>() {
            @Override
            public void onResponse(Call<List<Lot>> call, Response<List<Lot>> response) {
                if (response.isSuccessful()) {
                    if (response.body() != null) {
                        Log.i("server", "lotList isEmpty? " + lotList.isEmpty());
                        if (response.body().size() != lotList.size()) {
                            lotList.clear();
                            lotList.addAll(response.body());
                            arrayAdapter.notifyDataSetChanged();
                        }

                    } else call.clone().enqueue(this);
                } else Log.i("server", "response no successful");
            }

            @Override
            public void onFailure(Call<List<Lot>> call, Throwable t) {
                Log.i("server", "error: " + t.getMessage());
            }
        });
    }
}
