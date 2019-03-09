package com.app.smartganado.smart_ganado.view;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.app.smartganado.smart_ganado.R;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class NewEstateActivity extends AppCompatActivity {

    EditText Nombre_Finca;
    EditText Tamaño_Finca;
    EditText Direccion_Finca;
    TextView a;


    private static Socket s;

    private static InputStreamReader isr;
    private static BufferedReader br;
    private static PrintWriter printWriter;
    String msg = "";

    private static String ip = "172.25.7.130";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_estate);

        Nombre_Finca = (EditText) findViewById(R.id.Nombre_Finca);
        Tamaño_Finca = (EditText) findViewById(R.id.Tamaño_Finca);
        Direccion_Finca = (EditText) findViewById(R.id.Direccion_Finca);
        a = (TextView) findViewById(R.id.textView16);

    }

    public void onClick(View view) {
        msg = Nombre_Finca.getText().toString();
        a.setText(msg);
        myTask mt = new myTask();
        mt.execute();

        Toast.makeText(getApplicationContext(), "Data sent", Toast.LENGTH_LONG).show();
    }


    class myTask extends AsyncTask<Void, Void, Void> {

        @Override
        protected Void doInBackground(Void... voids) {
            try {
                s = new Socket(ip, 5000);

                printWriter = new PrintWriter(s.getOutputStream());
                printWriter.write(msg);
                printWriter.flush();
                printWriter.close();
                s.close();


            } catch (IOException e) {
                e.printStackTrace();
            }


            return null;
        }
    }
}
