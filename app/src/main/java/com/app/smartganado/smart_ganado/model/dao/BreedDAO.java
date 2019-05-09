package com.app.smartganado.smart_ganado.model.dao;

import android.util.Log;
import android.widget.ArrayAdapter;

import com.app.smartganado.smart_ganado.model.vo.Breed;
import com.app.smartganado.smart_ganado.remote.APIUtils;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.components.Description;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class BreedDAO {

    private static List<Breed> breedList;
    private static List<String> breedIndicatorsList;
    private static List<PieEntry> pieEntries;

    static {
        breedList = new ArrayList<>();
        breedIndicatorsList= new ArrayList<>();
        pieEntries= new ArrayList<>();
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

    public static void getIndicators (Integer idEstate, final PieChart pieChart){
        APIUtils.getAPIService().getEstateBreedSize("getEstateBreedSize", String.valueOf(idEstate)).enqueue(new Callback<List<String>>() {
            @Override
            public void onResponse(Call<List<String>> call, Response<List<String>> response) {
                breedIndicatorsList.clear();
                breedIndicatorsList.addAll(response.body());

                for (int i = 0; i < breedIndicatorsList.size(); i++) {
                    String[] data= breedIndicatorsList.get(i).split(",");
                   pieEntries.add(new PieEntry(Integer.valueOf(data[0]),data[1]));
                }
                Description description= new Description();
                description.setText("");
                pieChart.setDescription(description);
                PieDataSet pieDataSet= new PieDataSet(pieEntries,"Razas de ganado");
                pieDataSet.setColors(ColorTemplate.COLORFUL_COLORS);
                PieData pieData = new PieData(pieDataSet);
                pieChart.setData(pieData);
                pieChart.notifyDataSetChanged();
            }

            @Override
            public void onFailure(Call<List<String>> call, Throwable t) {

            }
        });
    }
}
