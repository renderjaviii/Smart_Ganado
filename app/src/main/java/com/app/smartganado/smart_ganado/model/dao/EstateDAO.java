package com.app.smartganado.smart_ganado.model.dao;

import android.content.Context;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import com.app.smartganado.smart_ganado.model.vo.Estate;
import com.app.smartganado.smart_ganado.remote.APIUtils;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class EstateDAO {

    private List<Estate> estateList;

    public EstateDAO() {
        estateList = new ArrayList<>();
    }

    public List<Estate> getEstateList() {
        return estateList;
    }

    public static int getPositionID(Spinner spinner, int id) {
        for (int i = 0; i < spinner.getCount(); i++) {
            Log.i("server", ((Estate) spinner.getItemAtPosition(i)).getId() + " == " + id);
            if (((Estate) spinner.getItemAtPosition(i)).getId() == id)
                return i;
        }
        return -1;
    }

    //GET estates from database
    public void getEstates(Long phone, final ArrayAdapter<Estate> arrayAdapter) {
        estateList.clear();

        APIUtils.getAPIService().getEstate("getAll", phone).enqueue(new Callback<List<Estate>>() {
            @Override
            public void onResponse(Call<List<Estate>> call, Response<List<Estate>> response) {
                if (response.isSuccessful())
                    if (response.body() != null) {
                        for (Estate estate : response.body()) {
                            Log.i("server", estate.print());
                            estateList.add(estate);
                        }
                        arrayAdapter.notifyDataSetChanged();

                    } else
                        call.clone().enqueue(this);
            }

            @Override
            public void onFailure(Call<List<Estate>> call, Throwable t) {
                Log.i("server", "error failure: " + t.getMessage());
            }
        });
    }

    public void insertEstate(final Context context, Estate estate) {
        APIUtils.getAPIService().insertEstate("insert", estate).enqueue(new Callback<Boolean>() {
            @Override
            public void onResponse(Call<Boolean> call, Response<Boolean> response) {
                Toast.makeText(context, response.body() ? "Finca creada correctamente" : "Ya tienes una finca con el mismo nombre...", Toast.LENGTH_LONG).show();
            }

            @Override
            public void onFailure(Call<Boolean> call, Throwable t) {
                Toast.makeText(context, "Se produjo un error creando finca...", Toast.LENGTH_LONG).show();
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

