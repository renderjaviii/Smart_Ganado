package com.app.smartganado.smart_ganado.view;

import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.app.smartganado.smart_ganado.R;
import com.app.smartganado.smart_ganado.model.dao.BreedDAO;
import com.app.smartganado.smart_ganado.model.dao.CattleDAO;
import com.app.smartganado.smart_ganado.model.dao.EstateDAO;
import com.app.smartganado.smart_ganado.model.dao.GenderDAO;
import com.app.smartganado.smart_ganado.model.dao.LotDAO;
import com.app.smartganado.smart_ganado.model.dao.PurposeDAO;
import com.app.smartganado.smart_ganado.model.dao.TanksDAO;
import com.app.smartganado.smart_ganado.model.vo.Breed;
import com.app.smartganado.smart_ganado.model.vo.Cattle;
import com.app.smartganado.smart_ganado.model.vo.Estate;
import com.app.smartganado.smart_ganado.model.vo.Gender;
import com.app.smartganado.smart_ganado.model.vo.Lot;
import com.app.smartganado.smart_ganado.model.vo.Purpose;
import com.app.smartganado.smart_ganado.model.vo.Tank;
import com.app.smartganado.smart_ganado.model.vo.UserApp;
import com.app.smartganado.smart_ganado.utilities.Utilities;

public class NewTankActivity extends AppCompatActivity {


    private Tank tank;
    private UserApp user;

    private Spinner fincaSpinner;
    private EditText editTNombre, editTCapacidad;
    private FloatingActionButton floatButtonEdit;
    private Button buttonAdd, buttonDelete;

    private ArrayAdapter<Estate> estateAdapter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_cattle);

        fincaSpinner = findViewById(R.id.SFincasTank);

        editTNombre = findViewById(R.id.ETNameTank);
        editTCapacidad = findViewById(R.id.ETCapacityTank);
        floatButtonEdit = findViewById(R.id.FABEditarTank);
        buttonAdd = findViewById(R.id.BRegistrarTank);
        buttonDelete = findViewById(R.id.BEliminarTank);

        initUI();

        if (user == null)
            user = (UserApp) getIntent().getSerializableExtra("user");


        Log.i("server", "llegó usuario new tank: " + user.toString());

        if (getIntent().getSerializableExtra("Info") == null) {
            Log.i("server", "Crear");
            floatButtonEdit.setVisibility(View.GONE);
            buttonDelete.setVisibility(View.GONE);
        } else {

            Log.i("server", "Editando");
            tank = (Tank) getIntent().getSerializableExtra("Info");

            editTNombre.setText(String.valueOf(tank.getName()));
            editTCapacidad.setText(String.valueOf(tank.getCapacity()));
            fincaSpinner.setSelection(tank.getIdEstate());


            editTNombre.setEnabled(false);
            editTCapacidad.setEnabled(false);
            fincaSpinner.setEnabled(false);

            buttonAdd.setVisibility(View.GONE);
        }
    }

    private void initUI() {

        PurposeDAO purposeDAO = new PurposeDAO();

        EstateDAO estateDAO = new EstateDAO();
        estateAdapter = new ArrayAdapter<>(getApplicationContext(), android.R.layout.simple_spinner_item, estateDAO.getEstateList());
        fincaSpinner.setAdapter(estateAdapter);
        estateDAO.getEstates(user.getPhone(), estateAdapter);
    }

    public void createCattleAction(View view) {
        if (editTNombre.getText().toString().isEmpty() || editTCapacidad.getText().toString().isEmpty()) {
            Toast.makeText(this, "Debes ingresar todos los datos", Toast.LENGTH_SHORT).show();
        } else {

            Tank newTank = new Tank();
            newTank.setName(editTNombre.getText().toString());
            newTank.setCapacity(Double.parseDouble(editTCapacidad.getText().toString()));

            newTank.setIdEstate(((Estate) fincaSpinner.getSelectedItem()).getId());



            Log.i("server: ", "NEW cattle -> " + newTank.toString());

            TanksDAO.insertTank(getApplicationContext(), newTank);//Insert a new cattle

            //Go to View Cattle
            Intent intent = new Intent(getApplicationContext(), ViewTankActivity.class);
            intent.putExtra("user", user);
            startActivity(intent);

            this.finish();//Delete activity form queue

        }
    }


    public void Editar(View view) {
        buttonAdd = findViewById(R.id.Registrar);
        FloatingActionButton floatBtEdit = findViewById(R.id.FABEditar);
        floatBtEdit.setVisibility(View.GONE);
        buttonAdd.setVisibility(View.VISIBLE);
        editTNombre.setEnabled(true);
        editTCapacidad.setEnabled(true);
        fincaSpinner.setEnabled(true);
        buttonDelete.setVisibility(View.INVISIBLE);
    }

    public void Eliminar(View view) {
        Toast.makeText(this, "Eliminar", Toast.LENGTH_LONG).show();
    }
}
