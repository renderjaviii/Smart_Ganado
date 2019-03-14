package com.app.smartganado.smart_ganado.view;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.View;
import android.widget.EditText;

import com.app.smartganado.smart_ganado.R;
import com.app.smartganado.smart_ganado.model.vo.User;

public class NewUserActivity extends AppCompatActivity {

    EditText editTextCorreo;
    EditText editTextPassword;
    EditText editTextPhone;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_user);

        editTextCorreo = (EditText) findViewById(R.id.Correo_Registro);
        editTextPassword = (EditText) findViewById(R.id.Contraseña_Registro);
        editTextPhone = (EditText) findViewById(R.id.Telefono_Registro);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    public void onClick(View view) {
        User user = new User();
    }
}