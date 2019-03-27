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
import com.app.smartganado.smart_ganado.view.adapter.EstateAdapter;
import com.google.gson.Gson;

import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

//lee grace 63 liam fraser 60 darragh leahy 55
public class ViewEstateActivity extends AppCompatActivity {

    ArrayList<Estate> estateArrayList;
    ListView estateListView;
    private Long userPhone;
    EstateAdapter estateAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Bundle homeBundle= getIntent().getExtras();
        if (homeBundle!=null) {
        userPhone=getUserPhone(homeBundle);
        }

        setContentView(R.layout.activity_view_estate);
        estateListView = (ListView) findViewById(R.id.estateListView);
        estateArrayList= new ArrayList<>();
        estateArrayList.add(new Estate(1.0,"El sur","La milagrosa",null,userPhone));
        estateArrayList.add(new Estate(12.0,"El sur","El peñon",null,userPhone));
        estateAdapter=new EstateAdapter(ViewEstateActivity.this,R.layout.estate_adapter,estateArrayList);


        estateListView.setAdapter(estateAdapter);



    }

        //Buscador de finca
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.search_menu, menu);
        MenuItem item = menu.findItem(R.id.estateListView );
        SearchView searchView = (SearchView) item.getActionView();
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                ArrayList<Estate> result = new ArrayList<>();
                for (Estate x : estateArrayList) {
                    if (x.getName().toLowerCase().contains(s.toLowerCase())) {
                        result.add(x);
                    }
                }
                ((EstateAdapter) estateListView.getAdapter()).update(result);
                return false;
            }
        });
        return super.onCreateOptionsMenu(menu);
    }

    public void onClick(View view) {
        Intent newEstateIntent = new Intent(ViewEstateActivity.this, NewEstateActivity.class);
        newEstateIntent.putExtra("choose", "3");
        newEstateIntent.putExtra("userPhone", userPhone);
        startActivity(newEstateIntent);
    }



    public boolean onOptionsItemSelected(MenuItem item){
        int id=item.getItemId();
        if(id==R.id.action_settings){
            return true;
        }
        return super.onOptionsItemSelected(item);

    }

    public Long getUserPhone(Bundle homeBundle){
        return homeBundle.getLong("userPhone");
    }


    private List<Estate> getEstates(){
 /*
        Llamar al dao desde acá para obtener todas las fincas !

        */
 return null;
    }
}
