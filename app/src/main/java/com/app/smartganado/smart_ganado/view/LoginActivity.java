package com.app.smartganado.smart_ganado.view;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.app.smartganado.smart_ganado.R;


public class LoginActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);


        //Intent
        Intent intent = new Intent(getApplicationContext() , HomeActivity.class);
        intent.putExtra("phone", 320121); //Luego enviamos le objeto usuario
        startActivity(intent);
    }
}
