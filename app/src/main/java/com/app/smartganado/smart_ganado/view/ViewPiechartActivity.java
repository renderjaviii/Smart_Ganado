package com.app.smartganado.smart_ganado.view;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.app.smartganado.smart_ganado.R;
import com.app.smartganado.smart_ganado.model.dao.BreedDAO;
import com.app.smartganado.smart_ganado.model.dao.CattleDAO;
import com.app.smartganado.smart_ganado.model.vo.Estate;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.components.Description;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.highlight.Highlight;
import com.github.mikephil.charting.listener.OnChartValueSelectedListener;
import com.github.mikephil.charting.utils.ColorTemplate;

import org.w3c.dom.Text;

import java.util.ArrayList;

import androidx.annotation.Nullable;

public class ViewPiechartActivity extends AppCompatActivity {
    private String[] estate;
    private PieChart pieChart;
    private Context context;
    private TextView textView;
    private Button bt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_piechart);
        context=this;
        Bundle estateBundle = getIntent().getExtras();

        if (estateBundle != null && estate== null)
            estate= getEstate(estateBundle);


        textView=findViewById(R.id.cattleindtextView);
        bt=findViewById(R.id.buttonMilk);
        String a ="Finca: "+estate[1];
        textView.setText(a);
        pieChart= findViewById(R.id.pieChart);
        createPieChart();
        bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent newEstateIntent = new Intent(getApplicationContext(),ViewMilkProduction.class);
                getApplicationContext().startActivity(newEstateIntent);
            }
        });

    }


    private void createPieChart(){

        /*ArrayList<PieEntry> pieEntries= new ArrayList<>();
        Description description= new Description();
        description.setText("");
        pieChart.setDescription(description);

        pieEntries.add(new PieEntry(((int) (Math.random() * 20 + 1)),"Cebú"));
        pieEntries.add(new PieEntry(((int) (Math.random() * 20 + 1)),"Hoinstein"));
        pieEntries.add(new PieEntry(((int) (Math.random() * 20 + 1)),"Normando"));
        PieDataSet pieDataSet = new PieDataSet(pieEntries,"Razas de ganado");
        pieDataSet.setColors(ColorTemplate.COLORFUL_COLORS);
       PieData pieData= new PieData(pieDataSet);
       pieChart.setData(pieData);

       CattleDAO.getCattlesByEstate(String.valueOf(estate.getId()),pieChart);*/

        BreedDAO.getIndicators(Integer.valueOf(estate[0]),pieChart);

        pieChart.setOnChartValueSelectedListener(new OnChartValueSelectedListener() {
            @Override
            public void onValueSelected(Entry e, Highlight h) {

                Toast.makeText(context, String.valueOf(CattleDAO.getCattleList().size()), Toast.LENGTH_LONG).show();
            }

            @Override
            public void onNothingSelected() {

            }
        });





    }



    public String[] getEstate(Bundle homeBundle) {
        return (String[]) homeBundle.getSerializable("estate");
    }
}
