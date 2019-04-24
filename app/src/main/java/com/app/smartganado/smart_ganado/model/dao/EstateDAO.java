package com.app.smartganado.smart_ganado.model.dao;

import android.content.Context;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.Toast;

import com.app.smartganado.smart_ganado.model.vo.Estate;
import com.app.smartganado.smart_ganado.remote.APIUtils;
import com.app.smartganado.smart_ganado.view.adapter.EstateAdapter;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class EstateDAO {

    private static List<Estate> estateList;
    private static List<PieEntry> pieEntries;

    static {
        estateList = new ArrayList<>();
        pieEntries= new ArrayList<>();
    }

    public static List<Estate> getEstateList() {
        return estateList;
    }
    public static List<PieEntry> getPieEntries(){return pieEntries; }

    //GET estates from database
    public static void getEstates(Long phone, final ArrayAdapter<Estate> arrayAdapter) {
        APIUtils.getAPIService().getEstate("getAll", phone).enqueue(new Callback<List<Estate>>() {
            @Override
            public void onResponse(Call<List<Estate>> call, Response<List<Estate>> response) {
                if (response.isSuccessful())
                    if (response.body() != null) {
                        Log.i("server", "estateList isEmpty? " + estateList.isEmpty());

                        estateList.clear();
                        estateList.addAll(response.body());
                        arrayAdapter.notifyDataSetChanged();

                    } else
                        call.clone().enqueue(this);//Recalling
            }

            @Override
            public void onFailure(Call<List<Estate>> call, Throwable t) {
                Log.i("server", "error failure: " + t.getMessage());
            }
        });
    }

    public static void getEstatesWA(Long phone, final PieChart pieChart,final Context context) {
        APIUtils.getAPIService().getEstate("getAll", phone).enqueue(new Callback<List<Estate>>() {
            @Override
            public void onResponse(Call<List<Estate>> call, Response<List<Estate>> response) {
                if (response.isSuccessful())
                    if (response.body() != null) {
                        Log.i("server", "estateList isEmpty? " + estateList.isEmpty());
                        estateList.clear();
                        estateList.addAll(response.body());
                        for (int i=0;i<estateList.size();i++){
                            pieEntries.add(new PieEntry(i,estateList.get(i).getName(),estateList.get(i)));
                        }

                        pieChart.notifyDataSetChanged();

                    } else
                        call.clone().enqueue(this);//Recalling
            }

            @Override
            public void onFailure(Call<List<Estate>> call, Throwable t) {
                Log.i("server", "error failure: " + t.getMessage());
            }
        });
    }

    public static void insertEstate(final Context context, Estate estate) {
        APIUtils.getAPIService().insertEstate("insert", estate).enqueue(new Callback<Boolean>() {
            @Override
            public void onResponse(Call<Boolean> call, Response<Boolean> response) {
                Toast.makeText(context, response.body() ? "Finca creada correctamente" : "Ya tienes una finca con el mismo nombre...", Toast.LENGTH_LONG).show();
            }

            @Override
            public void onFailure(Call<Boolean> call, Throwable t) {
                Log.i("server", "error failure: " + t.getMessage());
            }
        });
    }


    public static void deleteEstate(final Context context, int id) {
        APIUtils.getAPIService().deleteEstate("delete", String.valueOf(id)).enqueue(new Callback<Boolean>() {
            @Override
            public void onResponse(Call<Boolean> call, Response<Boolean> response) {
                if (response.isSuccessful()) {
                    if (response.body()) {
                        Toast.makeText(context, "Se eliminó la finca correctamente", Toast.LENGTH_LONG).show();
                    } else
                        Toast.makeText(context, "Ocurrió un problema eliminando finca, intentalo nuevamente", Toast.LENGTH_LONG).show();
                } else Log.i("server", "no successful");
            }

            @Override
            public void onFailure(Call<Boolean> call, Throwable t) {
                Log.i("server", "error: " + t.getMessage());
            }
        });
    }
}





/*    public static void getEstates(final ArrayAdapter<Estate> estateAdapter) {
        if (estateList.isEmpty()) {
            Log.i("server", "peticion get estates");

            APIUtils.getAPIService()
                    .getEstate("getAll", 1234l)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(new Subscriber<List<Estate>>() {

                        @Override
                        public void onCompleted() {
                            estateAdapter.notifyDataSetChanged();//Updated adapter
                        }

                        @Override
                        public void onError(Throwable e) {
                            Log.i("Server", "error: " + e.getMessage());
                        }

                        @Override
                        public void onNext(List<Estate> estates) {
                            if (estates != null)
                                for (Estate estate : estates) {
                                    Log.i("server: ", estate.print());
                                    estateList.add(estate);
                                }
                            else Log.i("server", "isEmpy()");
                        }
                    });
        }
*/

