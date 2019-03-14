package com.app.smartganado.smart_ganado.view;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import com.app.smartganado.smart_ganado.R;
import com.app.smartganado.smart_ganado.model.User;

public class NewUserActivity extends AppCompatActivity {

    EditText editTextCorreo;
    EditText editTextPassword;
    EditText editTextPhone;
    User Usuario = new User();
    CheckBox CheckAdminitrador, CheckEmpleado;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_user);


        editTextCorreo = (EditText)findViewById(R.id.Correo_Registro);
        editTextPassword = (EditText)findViewById(R.id.Contraseña_Registro);
        editTextPhone = (EditText)findViewById(R.id.Telefono_Registro);

        Usuario.setCorreo(editTextCorreo.toString());
        Usuario.setContraseña(editTextPassword.toString());
        Usuario.setTelefono(Integer.parseInt(editTextPhone.toString()));
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        if(CheckAdminitrador.isSelected()){
            Usuario.setId_Rol(1);
        }else{
            Usuario.setId_Rol(2);
        }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;

    }

}
