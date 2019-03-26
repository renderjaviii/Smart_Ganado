package com.app.smartganado.smart_ganado.view;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ListView;
import android.widget.SearchView;

import com.app.smartganado.smart_ganado.R;
import com.app.smartganado.smart_ganado.model.vo.Estate;
import com.app.smartganado.smart_ganado.model.vo.UserApp;
import com.app.smartganado.smart_ganado.remote.APIUtils;
import com.app.smartganado.smart_ganado.view.adapter.EstateAdapter;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

//lee grace 63 liam fraser 60 darragh leahy 55
public class ViewEstateActivity extends AppCompatActivity {

    ArrayList<Estate> a;
    ListView finca;
    private int userPhone;
    private List<String> names = new ArrayList<>();
    private EstateAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_estate);
        finca = (ListView) findViewById(R.id.finca);

        a = new ArrayList<>();
        Estate estate = new Estate();
        estate.setName("Fincota");
        estate.setPhoneUser(1234l);
        a.add(estate);

        adapter = new EstateAdapter(ViewEstateActivity.this, R.layout.estate_adapter, a);

        init();
    }


  /*  @Override
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
                ArrayList<Estate> result = new ArrayList<>();
                for (Estate x : a) {
                    if (x.getName().toLowerCase().contains(s.toLowerCase())) {
                        result.add(x);
                    }
                }
                ((EstateAdapter) finca.getAdapter()).update(result);
                return false;
            }
        });
        return super.onCreateOptionsMenu(menu);
    }*/

    public void onClick(View view) {
        Intent miIntent = new Intent(ViewEstateActivity.this, NewEstateActivity.class);
        startActivity(miIntent);
    }


    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);

    }


    private void init() {

        UserApp user = new UserApp();
        user.setPhone(1234l);
        Log.i("server" , "peticion");


        APIUtils.getAPIService().getEstate("getAll", user).enqueue(new Callback<List<Estate>>() {
            @Override
            public void onResponse(Call<List<Estate>> call, Response<List<Estate>> response) {
                if (response.isSuccessful()) {
                    for (Estate estate : response.body()) {
                        adapter.add(estate.getName());
                        Log.i("server", estate.toString());
                    }
                    adapter.notifyDataSetChanged();

                } else Log.i("server", "error on response");
            }

            @Override
            public void onFailure(Call<List<Estate>> call, Throwable t) {
                Log.i("server", t.getMessage());
            }
        });
    }
}