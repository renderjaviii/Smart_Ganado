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


        adapter = new CattleAdapter(getApplicationContext(), CattleDAO.getCattleList());
        cattleListView.setAdapter(adapter);

        if (user == null)
            user = (UserApp) getIntent().getSerializableExtra("user");

        Log.i("server", "llego USER: " + user.toString());
        CattleDAO.getCattles(user.getPhone(), adapter);//Getting cattles's by user_phone

        cattleListView.setOnItemClickListener(cattleListListener);
    }

    private AdapterView.OnItemClickListener cattleListListener = new AdapterView.OnItemClickListener() {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            Cattle cattle = (Cattle) cattleListView.getItemAtPosition(position);
            Intent intent = new Intent(getApplicationContext(), NewCattleActivity.class);
            intent.putExtra("Info", cattle);
            startActivity(intent);
        }
    };


    public void onClickNewCattle(View view) {
        Intent intent = new Intent(getApplicationContext(), NewCattleActivity.class);
        intent.putExtra("user", user);
        startActivity(intent);
    }

}