package com.app.smartganado.smart_ganado.view;

import android.content.Context;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.app.smartganado.smart_ganado.R;
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

import java.util.ArrayList;

import androidx.annotation.Nullable;

public class ViewPiechartActivity extends AppCompatActivity {
    private Estate estate;
    private PieChart pieChart;
    private Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_piechart);
        context=this;
        Bundle estateBundle = getIntent().getExtras();

        if (estateBundle != null && estate== null)
            estate= getEstate(estateBundle);



        pieChart= findViewById(R.id.pieChart);
        createPieChart();

    }


    private void createPieChart(){

        ArrayList<PieEntry> pieEntries= new ArrayList<>();
        Description description= new Description();
        description.setText("");
        pieChart.setDescription(description);
        pieEntries.add(new PieEntry(11,"asfsd"));
        pieEntries.add(new PieEntry(6,"asfsd"));
        pieEntries.add(new PieEntry(10,"asfsd"));
        PieDataSet pieDataSet = new PieDataSet(pieEntries,"FINCAS");
        pieDataSet.setColors(ColorTemplate.COLORFUL_COLORS);
       PieData pieData= new PieData(pieDataSet);
       pieChart.setData(pieData);

       CattleDAO.getCattlesByEstate(estate.getId(),pieChart);

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

    public Estate getEstate(Bundle homeBundle) {
        return (Estate) homeBundle.getSerializable("estate");
    }
}
