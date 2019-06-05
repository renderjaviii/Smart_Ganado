package com.app.smartganado.smart_ganado.view;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
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
import java.util.Calendar;
import java.util.Date;

@SuppressWarnings("all")
public class NewTankActivity extends AppCompatActivity implements View.OnClickListener {


    private Tank tank;
    private UserApp user;

    private Spinner fincaSpinner;
    private EditText editTNombre, editTCapacidad;
    private Button buttonAdd, buttonDelete;

    private ArrayAdapter<Estate> estateAdapter;

    private ProgressBar capacidadProgressBar;
    private TextView especificacion;

    private Button buttonProduccion;
    private Button buttonCalendario,AñadirButtom;
    private EditText CantidadTXT;

    private int dia,mes,año;
    Date dato=new Date();

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_tank);

        fincaSpinner = findViewById(R.id.estateSpinner);

        editTNombre = findViewById(R.id.ETNameTank);
        editTCapacidad = findViewById(R.id.ETCapacityTank);
        buttonAdd = findViewById(R.id.BRegistrarTank);
        buttonDelete = findViewById(R.id.BEliminarTank);

        capacidadProgressBar = findViewById(R.id.progressBar2);
        especificacion = findViewById(R.id.Especificacion);
        buttonProduccion = findViewById(R.id.ProduccionButtom);
        buttonCalendario = findViewById(R.id.FechaButtom);
        CantidadTXT = findViewById(R.id.CantidadTXT);
        AñadirButtom = findViewById(R.id.AñadirButtom);

        buttonCalendario.setOnClickListener(this);


        estateAdapter = new ArrayAdapter(getApplicationContext(), android.R.layout.simple_spinner_item, EstateDAO.getEstateList());

        fincaSpinner.setAdapter(estateAdapter);

        if (user == null)
            user = (UserApp) getIntent().getSerializableExtra("user");


        Log.i("server", "llegó usuario new tank: " + user.toString());

        if (getIntent().getSerializableExtra("Info") == null) {
            Log.i("server", "Crear");
            buttonDelete.setVisibility(View.GONE);
            capacidadProgressBar.setVisibility(View.GONE);
            especificacion.setText("");
            buttonProduccion.setVisibility(View.GONE);
            buttonCalendario.setVisibility(View.GONE);
            CantidadTXT.setVisibility(View.GONE);
            AñadirButtom.setVisibility(View.GONE);
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
            capacidadProgressBar.setMax(tank.getCapacity().intValue());
            capacidadProgressBar.setProgress(500);
            CapacidadInicial(tank);
            buttonCalendario.setVisibility(View.GONE);
            CantidadTXT.setVisibility(View.GONE);
            AñadirButtom.setVisibility(View.GONE);
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
    public void CapacidadInicial(Tank tanque) {
        Double inicial = 0.0;
        Double max=tanque.getCapacity();
        Double libre = max-inicial;
        especificacion.setText("Cantidad utilizada: "+inicial+" litros \nCantidad libre: "+libre+" litros");
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

    public void Producción(View view){
        buttonCalendario.setVisibility(View.VISIBLE);
        CantidadTXT.setVisibility(View.VISIBLE);
        AñadirButtom.setVisibility(View.VISIBLE);
        buttonDelete.setVisibility(View.GONE);
    }

    public void Añadir(View view){
        buttonCalendario.setVisibility(View.GONE);
        CantidadTXT.setVisibility(View.GONE);
        AñadirButtom.setVisibility(View.GONE);
        buttonDelete.setVisibility(View.VISIBLE);
    }

    @Override
    public void onClick(View v) {
            final Calendar c = Calendar.getInstance();

            dia=c.get(Calendar.DAY_OF_MONTH);
            año=c.get(Calendar.YEAR);
            mes=c.get(Calendar.MONTH);
        Toast.makeText(this, "dia: "+dia+" año: "+año+" mes: "+mes, Toast.LENGTH_SHORT).show();
        c.set(año,mes,dia);


        DatePickerDialog datePickerDialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
                @Override
                public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                    dato= new Date(dia,mes,año);
                    dia=dayOfMonth;
                    mes=month+1;
                    año=year;

                }
            }
            ,dia,mes,año);
            datePickerDialog.show();
    }
}
