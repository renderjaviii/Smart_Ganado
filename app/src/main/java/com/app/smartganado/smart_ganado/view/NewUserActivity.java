package com.app.smartganado.smart_ganado.view;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import com.app.smartganado.smart_ganado.R;
import com.app.smartganado.smart_ganado.model.dao.UserAppDAO;
import com.app.smartganado.smart_ganado.model.vo.UserApp;

public class NewUserActivity extends AppCompatActivity {
    private EditText editTEmail;
    private EditText editTPassword;
    private EditText editTPhone;
    private EditText editTName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_user);

        editTEmail = findViewById(R.id.editTEmail);
        editTPassword = findViewById(R.id.editTPassword);
        editTPhone = findViewById(R.id.editTPhone);
        editTName = findViewById(R.id.editTName);
    }

    public void onNewUser(View view) {
        Log.i("server", "peticion insert user");

        UserApp user = new UserApp();
        user.setName(editTName.getText().toString());
        user.setPhone(Long.parseLong(editTPhone.getText().toString()));
        user.setEmail(editTEmail.getText().toString());
        user.setPassword(editTPassword.getText().toString());

        UserAppDAO.insertUser(getApplicationContext(), user);
    }

}