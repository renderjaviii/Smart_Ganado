package com.app.smartganado.smart_ganado.view;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.ListView;

import com.app.smartganado.smart_ganado.R;
import com.app.smartganado.smart_ganado.model.dao.EstateDAO;
import com.app.smartganado.smart_ganado.model.vo.Estate;
import com.app.smartganado.smart_ganado.model.vo.UserApp;
import com.app.smartganado.smart_ganado.view.adapter.EstateAdapter;

//lee grace 63 liam fraser 60 darragh leahy 55
public class ViewEstateActivity extends AppCompatActivity {

    private ListView estateListView;
    private EstateAdapter estateAdapter;
    private UserApp user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle estateBundle = getIntent().getExtras();

        if (estateBundle != null && user == null)
            user = getUser(estateBundle);

        setContentView(R.layout.activity_view_estate);
        estateListView = findViewById(R.id.estateListView);

        estateAdapter = new EstateAdapter(ViewEstateActivity.this, R.layout.estate_adapter, EstateDAO.getEstateList());
        estateListView.setAdapter(estateAdapter);
        EstateDAO.getEstates(user.getPhone(), estateAdapter);
    }

   /* //Buscador de finca
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.search_menu, menu);
        MenuItem item = (MenuItem) menu.findItem(R.id.estateListView);

        Log.i("server", item.toString());


        SearchView searchView = (SearchView) item.getActionView();
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                ArrayList<Estate> result = new ArrayList<>();

                for (int i = 0; i < estateListView.getCount(); i++) {
                    Estate x = (Estate) estateListView.getItemAtPosition(i);
                    Log.i("Server", x.getName());
                    if (x.getName().toLowerCase().contains(s.toLowerCase())) {
                        result.add(x);
                    }
                }

                ((EstateAdapter) estateListView.getAdapter()).update(result);
                return false;
            }
        });
        return super.

                onCreateOptionsMenu(menu);
    }*/

    public void onClick(View view) {
        Intent newEstateIntent = new Intent(ViewEstateActivity.this, NewEstateActivity.class);
        newEstateIntent.putExtra("choose", "3");
        newEstateIntent.putExtra("user", user);

        startActivityForResult(newEstateIntent, 1);
    }


    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);

    }

    public UserApp getUser(Bundle homeBundle) {
        return (UserApp) homeBundle.getSerializable("user");
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1) {
            if (resultCode == RESULT_OK) {
                Bundle estateBundle = data.getExtras();
                Estate estate = (Estate) estateBundle.getSerializable("estate");

                Boolean var = true;
                for (Estate e : estateAdapter.getItems())
                    if (e.getName().equals(estate.getName()))
                        var = false;

                if (var)
                    estateAdapter.add(estate);

            }
        } else {
            if (resultCode == RESULT_OK) {
                Bundle estateBundle = data.getExtras();
                //estateAdapter.add(((Estate) estateBundle.getSerializable("estate")));
                EstateDAO.getEstates(user.getPhone(), estateAdapter);
            }
        }

    }


}
