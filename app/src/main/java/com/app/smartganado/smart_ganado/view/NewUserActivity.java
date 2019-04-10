package com.app.smartganado.smart_ganado.view;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.app.smartganado.smart_ganado.R;
import com.app.smartganado.smart_ganado.model.vo.UserApp;
import com.app.smartganado.smart_ganado.remote.APIUtils;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class NewUserActivity extends AppCompatActivity {


    EditText editTextCorreo;
    EditText editTextPassword;
    EditText editTextPhone;
    EditText editTNombre;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_user);

        editTextCorreo = findViewById(R.id.managerEventEditText);
        editTextPassword = findViewById(R.id.Contraseña_Registro);
        editTextPhone = findViewById(R.id.detailsEventEditText);
    //    editTNombre = findViewById(R.id.editTName);


    }

    public void onNewUser(View view) {
        UserApp user = new UserApp();

        Log.i("server" , "peticion");
       user.setName(editTNombre.getText().toString());
        user.setPhone(Long.parseLong(editTextPhone.getText().toString()));
        user.setEmail(editTextCorreo.getText().toString());
        user.setPassword(editTextPassword.getText().toString());

        APIUtils.getAPIService().insertUser("insert", user).enqueue(new Callback<Boolean>() {
            @Override
            public void onResponse(Call<Boolean> call, Response<Boolean> response) {
                if (response.isSuccessful())
                    Toast.makeText(getApplicationContext(), response.body() ? "Se insertó correctamente" : "No se pudo insertar", Toast.LENGTH_LONG).show();
            }

            @Override
            public void onFailure(Call<Boolean> call, Throwable t) {
                Log.i("server", "failure: " + t.getMessage());
            }
        });
    }

}