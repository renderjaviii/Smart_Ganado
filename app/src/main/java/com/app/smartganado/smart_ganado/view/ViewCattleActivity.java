package com.app.smartganado.smart_ganado.view;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.app.smartganado.smart_ganado.R;
import com.app.smartganado.smart_ganado.model.dao.CattleDAO;
import com.app.smartganado.smart_ganado.model.dao.UserAppDAO;
import com.app.smartganado.smart_ganado.model.vo.Cattle;
import com.app.smartganado.smart_ganado.model.vo.UserApp;
import com.app.smartganado.smart_ganado.view.adapter.CattleAdapter;

public class ViewCattleActivity extends AppCompatActivity {

    private CattleAdapter adapter;
    private ListView cattleListView;
    private UserApp user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_cattle);
        cattleListView = findViewById(R.id.listaGanado);

        user = UserAppDAO.getUser();

        Log.i("server", "user in view cattle: " + user.getName());

        adapter = new CattleAdapter(getApplicationContext(), CattleDAO.getCattleList());//Initializing cattleAdapter
        cattleListView.setAdapter(adapter);

        cattleListView.setOnItemClickListener(cattleListListener);
        Log.i("server", "\nOnCreate in: " + this.getLocalClassName());

        String idCattleToDelete = getIntent().getExtras().getString("id_cattle");
        if (idCattleToDelete != null) {
            Log.i("server", "deleting cattle with ID = " + idCattleToDelete);
            CattleDAO.deleteCattle(getApplicationContext(), adapter, Integer.parseInt(idCattleToDelete));
        }
    }

    private AdapterView.OnItemClickListener cattleListListener = new AdapterView.OnItemClickListener() {

        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            Cattle cattle = (Cattle) cattleListView.getItemAtPosition(position);
            Intent intent = new Intent(getApplicationContext(), NewCattleActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);
            intent.putExtra("cattle", cattle);
            startActivity(intent);
        }
    };


    public void onClickNewCattle(View view) {
        startActivity(new Intent(getApplicationContext(), NewCattleActivity.class));
    }

    @Override
    protected void onResume() {
        CattleDAO.getCattles(user.getPhone(), adapter);//Getting and showing cattles's

        Log.i("server", "---> OnResume: " + this.getLocalClassName());
        super.onResume();
    }

    @Override
    protected void onPause() {
        Log.i("server", "---> OnPause: " + this.getLocalClassName());
        super.onPause();
    }
}