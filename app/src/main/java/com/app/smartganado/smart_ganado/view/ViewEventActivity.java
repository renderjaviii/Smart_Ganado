package com.app.smartganado.smart_ganado.view;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.app.smartganado.smart_ganado.R;
import com.app.smartganado.smart_ganado.model.vo.UserApp;
import com.github.sundeepk.compactcalendarview.CompactCalendarView;
import com.github.sundeepk.compactcalendarview.domain.Event;

import java.util.Date;
import java.util.List;

public class ViewEventActivity extends AppCompatActivity {

    private CompactCalendarView calendarView;
    private Context context;
    private UserApp user;
    private Event calendarEvent;
    private com.app.smartganado.smart_ganado.model.vo.Event event;
    private Integer reference;
    private ListView listView;
    private TextView tittle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_event);

        calendarView = (CompactCalendarView) findViewById(R.id.compactcalendar_view);
        tittle = (TextView) findViewById(R.id.eventTittle);
        context = this;
        reference = (android.R.layout.simple_list_item_1);
        listView = (ListView) findViewById(R.id.eventListView);
        Bundle eventBundle = getIntent().getExtras();

        if (eventBundle != null && user == null)
            user = getUser(eventBundle);


        calendarView.setListener(new CompactCalendarView.CompactCalendarViewListener() {

            @Override
            public void onDayClick(final Date dateClicked) {
                AlertDialog.Builder builder = new AlertDialog.Builder(context);
                CharSequence[] items = new CharSequence[2];
                items[0] = "Añadir evento";
                items[1] = "Ver eventos";
                builder.setTitle("Seleccione").setItems(items, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        if (which == 0) {
                            Intent newEventIntent = new Intent(ViewEventActivity.this, NewEventActivity.class);
                            newEventIntent.putExtra("user", user);
                            newEventIntent.putExtra("time", dateClicked.getTime());
                            startActivityForResult(newEventIntent, 1);
                        } else {
                            List<Event> estateList = calendarView.getEvents(dateClicked);
                            if (!estateList.isEmpty()) {
                                listView.setVisibility(View.VISIBLE);
                                tittle.setVisibility(View.VISIBLE);
                                String[] values = new String[estateList.size()];

                                for (int i = 0; i < estateList.size(); i++) {
                                    event = (com.app.smartganado.smart_ganado.model.vo.Event) estateList.get(i).getData();
                                    values[i] = event.getName();
                                }

                                ArrayAdapter<String> adapter = new ArrayAdapter<>(context, reference, values);
                                listView.setAdapter(adapter);

                            } else {
                                listView.setVisibility(View.INVISIBLE);
                                Toast.makeText(context, "No existen eventos para este día", Toast.LENGTH_LONG).show();
                            }
                        }
                    }
                });
                AlertDialog dialog = builder.create();
                dialog.show();

                //Event event= new Event(Color.RED, dateClicked.getTime(), "alv");

                //calendarView.addEvent(event);

            }

            @Override
            public void onMonthScroll(Date firstDayOfNewMonth) {
                final String[] month = new String[12];
                month[0] = "Enero";
                month[1] = "Febrero";
                month[2] = "Marzo";
                month[3] = "Abril";
                month[4] = "Mayo";
                month[5] = "Junio";
                month[6] = "Julio";
                month[7] = "Agosto";
                month[8] = "Septiembre";
                month[9] = "Octubre";
                month[10] = "Noviembre";
                month[11] = "Diciembre";
                Toast.makeText(context, String.valueOf(month[firstDayOfNewMonth.getMonth()]) + " " + String.valueOf(firstDayOfNewMonth.getYear() + 1900), Toast.LENGTH_LONG).show();
                ;
            }
        });


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
                event = (com.app.smartganado.smart_ganado.model.vo.Event) estateBundle.getSerializable("event");
                calendarEvent = new Event(Color.RED, estateBundle.getLong("time"), event);
                calendarView.addEvent(calendarEvent);
            }
        }

    }


}




