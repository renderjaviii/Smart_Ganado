package com.app.smartganado.smart_ganado.model.dao;

import android.util.Log;
import android.widget.ArrayAdapter;

import com.app.smartganado.smart_ganado.model.vo.Breed;
import com.app.smartganado.smart_ganado.remote.APIUtils;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class BreedDAO {

    private static List<Breed> breedList;

    static {
        breedList = new ArrayList<>();
    }

    public static List<Breed> getBreedList() {
        return breedList;
    }

    public int getPositionID(int id) {
        Log.i("server", breedList.size() + " " + id);

        for (int i = 0; i < breedList.size(); i++) {

            Log.i("server", "HOLAAAAA " + breedList.get(i).getName());
            if (breedList.get(i).getId() == id)
                return i;
        }
        return -1;
    }

    //GET Breeds from database
    public static void getBreeds(final ArrayAdapter<Breed> arrayAdapter) {
        APIUtils.getAPIService().getBreed().enqueue(new Callback<List<Breed>>() {
            @Override
            public void onResponse(Call<List<Breed>> call, Response<List<Breed>> response) {
                if (response.isSuccessful()) {
                    if (response.body() != null) {
                        Log.i("server", "breedList isEmpty? " + breedList.isEmpty());

                        if (response.body().size() != breedList.size()) {
                            breedList.clear();
                            breedList.addAll(response.body());
                            arrayAdapter.notifyDataSetChanged();
                        }
                    } else call.clone().enqueue(this);

                } else Log.i("server", "reponse no successful");
            }

            @Override
            public void onFailure(Call<List<Breed>> call, Throwable t) {
                Log.i("server", "error: " + t.getMessage());
            }
        });
    }
}
