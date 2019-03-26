package com.app.smartganado.smart_ganado.view;

import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.app.smartganado.smart_ganado.R;
import com.app.smartganado.smart_ganado.model.vo.Estate;
import com.app.smartganado.smart_ganado.remote.APIService;
import com.app.smartganado.smart_ganado.remote.APIUtils;
import com.google.gson.Gson;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class NewEstateActivity extends AppCompatActivity {

    EditText editTNombre, editTArea, editTUbic;
    Button estateButton;
    TextView a;
    String jsonEstate;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_estate);

        editTNombre = (EditText) findViewById(R.id.Nombre_Finca);
        editTArea = (EditText) findViewById(R.id.Tamaño_Finca);
        editTUbic = (EditText) findViewById(R.id.Direccion_Finca);

        Bundle estateBundle= getIntent().getExtras();

        if (estateBundle!=null) {
            menu(Integer.valueOf(estateBundle.getString("choose")), estateBundle);

        }


    }

    public void onInsertEstate(View view) {
        Estate estate = new Estate();//aqui lee de los componentes
        estate.setName(editTNombre.getText().toString());
        finish();
    }
    private void menu(Integer choose, Bundle estateBundle){
        jsonEstate = estateBundle.getString("Estate");
        Estate newEstate= new Gson().fromJson(jsonEstate,Estate.class);
        switch (choose){
            case 1:
                editTNombre.setText(newEstate.getName());
                editTArea.setText(String.valueOf(newEstate.getArea()));
                editTUbic.setText(newEstate.getLocation());
                break;
            case 2:
                editTNombre.setText(newEstate.getName());
                editTNombre.setEnabled(false);
                editTArea.setText(String.valueOf(newEstate.getArea()));
                editTArea.setEnabled(false);
                editTUbic.setText(newEstate.getLocation());
                editTUbic.setEnabled(false);
                Button estateButton= (Button)  findViewById(R.id.Save_Finca);
                estateButton.setText("Ver ganado");
                break;
            case 3:

                break;


        }

    }


}
