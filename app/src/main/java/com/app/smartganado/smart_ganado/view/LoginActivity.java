package com.app.smartganado.smart_ganado.view;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.EditText;

import com.app.smartganado.smart_ganado.R;
import com.app.smartganado.smart_ganado.model.vo.User;


public class LoginActivity extends AppCompatActivity {

    User usuario;
    EditText telefono, contraseña;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        usuario = new User();
        setContentView(R.layout.activity_login);
        telefono = (EditText) findViewById(R.id.Phone_Login);
        contraseña = (EditText) findViewById(R.id.Password_Login);

        usuario.setTelefono(Integer.parseInt(telefono.toString()));
        usuario.setContraseña(contraseña.toString());//hola
        //Intent
        Intent intent = new Intent(getApplicationContext(), HomeActivity.class);
        intent.putExtra("phone", 320121); //Luego enviamos le objeto usuario
        startActivity(intent);
    }
}
