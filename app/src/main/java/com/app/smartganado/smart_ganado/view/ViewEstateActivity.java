package com.app.smartganado.smart_ganado.view;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ListView;
import android.widget.SearchView;

import com.app.smartganado.smart_ganado.R;
import com.app.smartganado.smart_ganado.model.vo.Estate;
import com.app.smartganado.smart_ganado.view.adapter.EstateAdapter;

import java.util.ArrayList;
import java.util.List;

//lee grace 63 liam fraser 60 darragh leahy 55
public class ViewEstateActivity extends AppCompatActivity {

    ArrayList<Estate> estateArrayList = new ArrayList<>();
    ListView estateListView;
    private Long userPhone;
    EstateAdapter estateAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle estateBundle = getIntent().getExtras();
        if (estateBundle != null) {
            userPhone = getUserPhone(estateBundle);
        }


        setContentView(R.layout.activity_view_estate);
        estateListView = (ListView) findViewById(R.id.estateListView);
        estateAdapter = new EstateAdapter(ViewEstateActivity.this, R.layout.estate_adapter, estateArrayList);
        estateListView.setAdapter(estateAdapter);


    }

    //Buscador de finca
  /*  @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.search_menu, menu);
        MenuItem item = menu.findItem(R.id.estateListView);
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
*/
    public void onClick(View view) {
        Intent newEstateIntent = new Intent(ViewEstateActivity.this, NewEstateActivity.class);
        newEstateIntent.putExtra("choose", "3");
        newEstateIntent.putExtra("userPhone", userPhone);

        startActivityForResult(newEstateIntent, 1);
    }


    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);

    }

    public Long getUserPhone(Bundle homeBundle) {
        return homeBundle.getLong("userPhone");
    }


    private List<Estate> getEstates() {
 /*
        Llamar al dao desde acá para obtener todas las fincas !

        */
        return null;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1) {
            if (resultCode == RESULT_OK) {
                Bundle estateBundle = data.getExtras();
                estateAdapter.add((Estate) estateBundle.getSerializable("estate"));
            }
        } else {
            if (resultCode == RESULT_OK) {

                Bundle estateBundle = data.getExtras();
                //    Toast.makeText(this, ((Estate)estateBundle.getSerializable("oldEstate")).getName(), Toast.LENGTH_LONG).show();
                estateAdapter.remove(estateBundle.getInt("position"));
                estateAdapter.add(((Estate) estateBundle.getSerializable("estate")));

            }
        }

    }


}
