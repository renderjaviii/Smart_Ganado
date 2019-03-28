package com.app.smartganado.smart_ganado.view;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import com.app.smartganado.smart_ganado.R;
import com.app.smartganado.smart_ganado.model.dao.UserAppDAO;
import com.app.smartganado.smart_ganado.model.vo.UserApp;


public class LoginActivity extends AppCompatActivity {

   private EditText phone, password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        phone = (EditText) findViewById(R.id.Phone_Login);
        password = (EditText) findViewById(R.id.Password_Login);

    }

    public void onLoginUser(View view) {
        UserApp user = new UserApp();
        user.setPhone(Long.parseLong(phone.getText().toString()));
        user.setPassword(password.getText().toString());

        UserAppDAO userAppDAO = new UserAppDAO();
        userAppDAO.tryLogin(getApplicationContext(), user);
    }

    public void onRegistration(View view) {
        Intent intent = new Intent(getApplicationContext(), NewUserActivity.class);
        startActivity(intent);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }

}
