package com.app.smartganado.smart_ganado.view;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import com.app.smartganado.smart_ganado.R;
import com.app.smartganado.smart_ganado.model.vo.Event;
import com.app.smartganado.smart_ganado.model.vo.UserApp;

import java.util.Date;

public class NewEventActivity extends AppCompatActivity {

    private UserApp user;
    private Event event;
    private Long time;
    private Date date;
    private


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_event);
        Bundle eventBundle = getIntent().getExtras();

        if (eventBundle != null && user == null) {
            user = getUser(eventBundle);
            time= eventBundle.getLong("time");
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.save:

        }
        return super.onOptionsItemSelected(item);
    }

    public UserApp getUser(Bundle homeBundle) {
        return (UserApp) homeBundle.getSerializable("user");
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater= getMenuInflater();
        inflater.inflate(R.menu.menu_event, menu);

        return super.onCreateOptionsMenu(menu);
    }


}