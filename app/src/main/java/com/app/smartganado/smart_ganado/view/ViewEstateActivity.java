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
import com.app.smartganado.smart_ganado.remote.APIService;
import com.app.smartganado.smart_ganado.remote.APIUtils;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

//lee grace 63 liam fraser 60 darragh leahy 55
public class ViewEstateActivity extends AppCompatActivity {
    ListView finca;
    ArrayAdapter<String> adapter;
    public APIService myApiService;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_estate);
        finca = (ListView) findViewById(R.id.finca);


        ArrayList<String> names = new ArrayList<>();
        adapter = new ArrayAdapter<String>(ViewEstateActivity.this, android.R.layout.simple_list_item_1, names);
        finca.setAdapter(adapter);


        //List of States (esto no tiene que ir en el onCreate())
        Log.i("server", "Peticion");
        if (myApiService == null)
            myApiService = APIUtils.getAPIService();

        myApiService.getEstate("getAll", "estate", 1).enqueue(new Callback<List<Estate>>() {
            @Override
            public void onResponse(Call<List<Estate>> call, Response<List<Estate>> response) {
                if (response.isSuccessful()) {
                    for (Estate estate : response.body())
                        Log.i("server", estate.toString());

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
        MenuItem item = menu.findItem(R.id.finca);
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

    public void onClick(View view) {
        Intent miIntent = new Intent(ViewEstateActivity.this, NewEstateActivity.class);
        startActivity(miIntent);
    }
}
