package com.app.smartganado.smart_ganado.view;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.app.smartganado.smart_ganado.R;
import com.app.smartganado.smart_ganado.model.dao.CattleDAO;
import com.app.smartganado.smart_ganado.model.dao.UserAppDAO;
import com.app.smartganado.smart_ganado.model.vo.Cattle;
import com.app.smartganado.smart_ganado.model.vo.UserApp;
import com.app.smartganado.smart_ganado.utilities.SHA512;


public class LoginActivity extends AppCompatActivity {

    private EditText editTPhone, editTPassword;
    private Button buttonLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        buttonLogin = findViewById(R.id.buttonLogin);
        editTPhone = findViewById(R.id.Phone_Login);
        editTPassword = findViewById(R.id.Password_Login);

        //Camera and storage permissions
        if (ContextCompat.checkSelfPermission(getApplicationContext(), Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(getApplicationContext(), Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.CAMERA}, 1000);
        }

        editTPhone.setText("1234");//Testing
        editTPassword.setText("root");

        //Enabling and disabling button
        editTPhone.addTextChangedListener(onTextChangedListener);
        editTPassword.addTextChangedListener(onTextChangedListener);
    }

    public void onLoginUser(View view) {
        UserApp user = new UserApp();
        user.setPhone(Long.parseLong(editTPhone.getText().toString()));
        user.setPassword(SHA512.encrypt(editTPassword.getText().toString()));//Encoding the users's password

        UserAppDAO.tryLogin(getCurrentFocus(), getApplication(), user);

        editTPassword.setText("");//Cleaning form's space
    }


    public void onRegistration(View view) {
        startActivity(new Intent(getApplicationContext(), NewUserActivity.class));
    }

    private TextWatcher onTextChangedListener = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {

        }

        @Override
        public void afterTextChanged(Editable s) {
            buttonLogin.setEnabled(!editTPhone.getText().toString().isEmpty() && !editTPassword.getText().toString().isEmpty());//Button enabled if both editTexts aren't empty
        }
    };

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }

}
