package com.app.smartganado.smart_ganado.view;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.app.smartganado.smart_ganado.R;
import com.app.smartganado.smart_ganado.model.dao.EstateDAO;
import com.app.smartganado.smart_ganado.model.dao.TanksDAO;
import com.app.smartganado.smart_ganado.model.vo.Estate;
import com.app.smartganado.smart_ganado.model.vo.Tank;
import com.app.smartganado.smart_ganado.model.vo.UserApp;

@SuppressWarnings("all")
public class NewTankActivity extends AppCompatActivity {


    private Tank tank;
    private UserApp user;

    private Spinner fincaSpinner;
    private EditText editTNombre, editTCapacidad;
    private Button buttonAdd, buttonDelete;

    private ArrayAdapter<Estate> estateAdapter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_tank);

        fincaSpinner = findViewById(R.id.estateSpinner);

        editTNombre = findViewById(R.id.ETNameTank);
        editTCapacidad = findViewById(R.id.ETCapacityTank);
        buttonAdd = findViewById(R.id.BRegistrarTank);
        buttonDelete = findViewById(R.id.BEliminarTank);

        estateAdapter = new ArrayAdapter(getApplicationContext(), android.R.layout.simple_spinner_item, EstateDAO.getEstateList());

        fincaSpinner.setAdapter(estateAdapter);

        if (user == null)
            user = (UserApp) getIntent().getSerializableExtra("user");


        Log.i("server", "llegó usuario new tank: " + user.toString());

        if (getIntent().getSerializableExtra("Info") == null) {
            Log.i("server", "Crear");
            buttonDelete.setVisibility(View.GONE);
        } else {

            Log.i("server", "Editando");
            tank = (Tank) getIntent().getSerializableExtra("Info");

            editTNombre.setText(String.valueOf(tank.getName()));
            editTCapacidad.setText(String.valueOf(tank.getCapacity()));
            getEstatePosition(estateAdapter, tank.getIdEstate());

            editTNombre.setEnabled(false);
            editTCapacidad.setEnabled(false);
            fincaSpinner.setEnabled(false);

            buttonAdd.setVisibility(View.GONE);
        }
    }

    private int getEstatePosition(ArrayAdapter<Estate> adapter, int id) {
        for (int i = 0; i < adapter.getCount(); i++) {
            if (adapter.getItem(i).getId() == id)
                fincaSpinner.setSelection(i);
        }
        return -1;
    }

    @Override
    protected void onResume() {
        EstateDAO.getEstates(user.getPhone(), estateAdapter);
        super.onResume();
    }

    public void createTank(View view) {
        if (editTNombre.getText().toString().isEmpty() || editTCapacidad.getText().toString().isEmpty()) {
            Toast.makeText(this, "Debes ingresar todos los datos", Toast.LENGTH_SHORT).show();
        } else {

            Tank newTank = new Tank();
            newTank.setName(editTNombre.getText().toString());
            newTank.setCapacity(Double.parseDouble(editTCapacidad.getText().toString()));
            newTank.setIdEstate(((Estate) fincaSpinner.getSelectedItem()).getId());

            Log.i("server: ", "NEW cattle -> " + newTank.toString());

            TanksDAO.insertTank(this, newTank);//Insert a new cattle
        }
    }

    public void Editar(View view) {
        buttonAdd = findViewById(R.id.buttonAdd);
        FloatingActionButton floatBtEdit = findViewById(R.id.FABEdit);
        floatBtEdit.setVisibility(View.GONE);
        buttonAdd.setVisibility(View.VISIBLE);
        editTNombre.setEnabled(true);
        editTCapacidad.setEnabled(true);
        fincaSpinner.setEnabled(true);
        buttonDelete.setVisibility(View.INVISIBLE);
    }

    public void Eliminar(View view) {
        TanksDAO.deleteTank(getApplicationContext(), tank.getId());
    }
}
