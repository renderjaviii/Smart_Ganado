package com.app.smartganado.smart_ganado.view;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.app.smartganado.smart_ganado.R;
import com.app.smartganado.smart_ganado.model.dao.EstateDAO;
import com.app.smartganado.smart_ganado.model.dao.TanksDAO;
import com.app.smartganado.smart_ganado.model.vo.Estate;
import com.app.smartganado.smart_ganado.model.vo.ProductionBook;
import com.app.smartganado.smart_ganado.model.vo.Tank;
import com.app.smartganado.smart_ganado.model.vo.UserApp;

import java.time.Instant;
import java.util.ArrayList;
import java.util.Date;

@SuppressWarnings("all")
public class NewTankActivity extends AppCompatActivity {


    private Tank tank;
    private UserApp user;

    private Spinner fincaSpinner;
    private EditText editTNombre, editTCapacidad;
    private Button buttonAdd, buttonDelete;

    private ArrayAdapter<Estate> estateAdapter;

    private ProgressBar capacidadProgressBar;
    private TextView especificacion;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_tank);

        fincaSpinner = findViewById(R.id.estateSpinner);

        editTNombre = findViewById(R.id.ETNameTank);
        editTCapacidad = findViewById(R.id.ETCapacityTank);
        buttonAdd = findViewById(R.id.BRegistrarTank);
        buttonDelete = findViewById(R.id.BEliminarTank);

        capacidadProgressBar = findViewById(R.id.progressBar);
        especificacion = findViewById(R.id.Especificacion);

        estateAdapter = new ArrayAdapter(getApplicationContext(), android.R.layout.simple_spinner_item, EstateDAO.getEstateList());

        fincaSpinner.setAdapter(estateAdapter);

        if (user == null)
            user = (UserApp) getIntent().getSerializableExtra("user");


        Log.i("server", "llegó usuario new tank: " + user.toString());

        if (getIntent().getSerializableExtra("Info") == null) {
            Log.i("server", "Crear");
            buttonDelete.setVisibility(View.GONE);
            capacidadProgressBar.setVisibility(View.INVISIBLE);
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

            capacidadProgressBar.setVisibility(View.VISIBLE);
            CapacidadInicial(InfoProduction(),ListaTanques().get(1));

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
    public void CapacidadInicial(ArrayList<ProductionBook> Libro, Tank tanque) {
        Double inicial = 0.0;
        Double max=tanque.getCapacity();
        for(int i=0;i<=Libro.size();i++){
            inicial=inicial+Libro.get(i).getProduction();
        }
        capacidadProgressBar.setMax(100);
        Double actual = (inicial*100)/max;
        capacidadProgressBar.setProgress(actual.intValue());
        Double libre = max-inicial;
        especificacion.setText("capacidad del tanque: "+max+ " cantidad utilizada: "+inicial+" cantidad libre: "+libre);
    }
    public void Eliminar(View view) {
        TanksDAO.deleteTank(getApplicationContext(), tank.getId());
    }

    public ArrayList<ProductionBook>  InfoProduction() {
        ArrayList<ProductionBook> Libro=null;
        ProductionBook produccion= null;
        String strFecha = "2007-12-25";
        Date fecha = null;
        Instant instant = null;
        for(int i=0;i<=5;i++){
            produccion.setId(i);
            produccion.setIdTank(1);
            produccion.setDate(fecha.from(instant));
            produccion.setProduction(i*4);
            Libro.add(produccion);
        }
        for(int i=0;i<=5;i++){
            produccion.setId(i);
            produccion.setIdTank(2);
            produccion.setDate(fecha.from(instant));
            produccion.setProduction(i*5);
            Libro.add(produccion);
        }
        return Libro;
    }
    public ArrayList<Tank> ListaTanques(){
        ArrayList<Tank> tanques=null;
        Tank tanque1 =null,tanque2=null;
        tanque1.setId(1); tanque2.setId(2);
        tanque1.setCapacity(25.32); tanque2.setCapacity(30.64);
        tanque1.setIdEstate(1); tanque2.setIdEstate(1);
        tanque1.setName("tanque 1"); tanque2.setName("tanque 2");
        tanques.add(tanque1); tanques.add(tanque2);
        return tanques;
    }
}
