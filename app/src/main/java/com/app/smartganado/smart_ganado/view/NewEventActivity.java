package com.app.smartganado.smart_ganado.view;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.app.smartganado.smart_ganado.R;
import com.app.smartganado.smart_ganado.model.vo.Event;
import com.app.smartganado.smart_ganado.model.vo.UserApp;

import java.util.Date;

public class NewEventActivity extends AppCompatActivity {

    private UserApp user;
    private Event event;
    private Long time;
    private Date date;
    private EditText managerEditText, detailsEditText, nameEditText;
    private Spinner estateSpinner;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_event);

        //Obtiene lo que le manda la anterior actividad
        Bundle eventBundle = getIntent().getExtras();

        managerEditText= (EditText) findViewById(R.id.managerEventEditText);
        nameEditText= (EditText) findViewById(R.id.nameEventEditText);
        detailsEditText=(EditText) findViewById(R.id.detailsEventEditText);
        estateSpinner=(Spinner) findViewById(R.id.estateSpinner);

        if (eventBundle != null && user == null) {

            //Obtiene el usuario y el tiempo en long
            user = getUser(eventBundle);
            time= eventBundle.getLong("time");
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.save:
                if (!(managerEditText.getText().toString().isEmpty() && nameEditText.getText().toString().isEmpty() && detailsEditText.getText().toString().isEmpty())) {
                    event = new Event();
                    event.setDate(new Date(time));
                    event.setDetails(detailsEditText.getText().toString());
                    event.setManager(managerEditText.getText().toString());
                    event.setName(nameEditText.getText().toString());
                    Intent newEventIntent = new Intent();
                    Bundle eventAdd = new Bundle();
                    eventAdd.putSerializable("event", event);
                    eventAdd.putLong("time", time);
                    newEventIntent.putExtras(eventAdd);
                    setResult(RESULT_OK, newEventIntent);
                    this.finish();
                }
            else{
                    Toast.makeText(this, "Por favor llene todos los campos", Toast.LENGTH_LONG).show();
            }

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