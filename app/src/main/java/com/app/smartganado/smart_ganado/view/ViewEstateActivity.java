package com.app.smartganado.smart_ganado.view;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.SearchView;

import com.app.smartganado.smart_ganado.R;
import com.app.smartganado.smart_ganado.model.vo.Estate;
import com.app.smartganado.smart_ganado.remote.APIUtils;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

//lee grace 63 liam fraser 60 darragh leahy 55
public class ViewEstateActivity extends AppCompatActivity {

    private ArrayList<String> estateNames;
    private ArrayAdapter<String> adapter;
    public ListView estateList;


    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_estate);
        estateList = findViewById(R.id.cattleList);

        initActivity();
    }

    private void initActivity() {
        estateNames = new ArrayList<>();

        //Lis estates
        Log.i("server", "Peticion");
        APIUtils.getAPIService().getEstate("getAll", "estate", 1).enqueue(new Callback<List<Estate>>() {
            @Override
            public void onResponse(Call<List<Estate>> call, Response<List<Estate>> response) {
                if (response.isSuccessful()) {
                    for (Estate estate : response.body()) {
                        estateNames.add(estate.getNombre());//Cargamos el arreglo nombre de fincas
                        Log.i("server", "finca: " + estate.getNombre());
                    }

                    adapter = new ArrayAdapter(getApplicationContext(), android.R.layout.simple_list_item_1, estateNames);
                    estateList.setAdapter(adapter);//Enviamos los nombres al listView

                    finish();
                    overridePendingTransition(0, 0);
                    startActivity(getIntent());
                    overridePendingTransition(0, 0);
                } else Log.i("server", "error on response");
            }

            @Override
            public void onFailure(Call<List<Estate>> call, Throwable t) {
                Log.i("server", "error: " + t.getMessage());
            }
        });

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.search_menu, menu);
        MenuItem item = menu.findItem(R.id.cattleList);
        SearchView searchView = (SearchView) item.getActionView();
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                adapter.getFilter().filter(s);
                return false;
            }
        });
        return super.onCreateOptionsMenu(menu);
    }


    //Open nueva finca
    public void onNewEstate(View view) {
        Intent intent = new Intent(getApplicationContext(), NewEstateActivity.class);
        startActivity(intent);
    }
}
