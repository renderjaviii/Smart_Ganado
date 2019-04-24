package com.app.smartganado.smart_ganado.view;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.widget.ListView;
import android.widget.Toast;

import com.app.smartganado.smart_ganado.R;
import com.app.smartganado.smart_ganado.model.dao.EstateDAO;
import com.app.smartganado.smart_ganado.model.vo.Estate;
import com.app.smartganado.smart_ganado.model.vo.UserApp;
import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.components.Description;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.formatter.IValueFormatter;
import com.github.mikephil.charting.highlight.Highlight;
import com.github.mikephil.charting.listener.OnChartValueSelectedListener;
import com.github.mikephil.charting.utils.ColorTemplate;
import com.github.mikephil.charting.utils.ViewPortHandler;

import java.util.ArrayList;
import java.util.List;

public class ViewIndicatorsActivity extends AppCompatActivity {
   private PieChart pieChart;
   private BarChart barChart;
   private Context context;
   private UserApp user;
   private List<Estate> estateList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_indicators);

        Bundle estateBundle = getIntent().getExtras();

        if (estateBundle != null && user == null)
            user = getUser(estateBundle);







        context=this;
        pieChart= findViewById(R.id.pieChart);
        barChart= findViewById(R.id.barChart);
        createPieChart();
        createBarChart();
        pieChart.setOnChartValueSelectedListener(new OnChartValueSelectedListener() {
            @Override
            public void onValueSelected(Entry e, Highlight h) {

                Toast.makeText(context, String.valueOf(((Estate) e.getData()).getId()), Toast.LENGTH_LONG).show();
            }

            @Override
            public void onNothingSelected() {

            }
        });
    }

    public UserApp getUser(Bundle homeBundle) {
        return (UserApp) homeBundle.getSerializable("user");
    }

    private void createPieChart(){
        estateList= EstateDAO.getEstateList();
        Description description= new Description();
        description.setText("");
        pieChart.setDescription(description);
        PieDataSet pieDataSet = new PieDataSet(EstateDAO.getPieEntries(),"FINCAS");
        pieDataSet.setColors(ColorTemplate.COLORFUL_COLORS);
        PieData pieData= new PieData(pieDataSet);
        pieChart.setData(pieData);
        EstateDAO.getEstatesWA(Long.valueOf(1234),pieChart,context);

    }
    private  void createBarChart(){
        ArrayList<BarEntry> barEntries= new ArrayList<>();


        barEntries.add(new BarEntry(1,12,"label"));
        barEntries.add(new BarEntry(2,12,"label"));

        BarDataSet barDataSet= new BarDataSet(barEntries,"alv");
        barDataSet.setColors(ColorTemplate.COLORFUL_COLORS);
        BarData barData= new BarData(barDataSet);
        barChart.setData(barData);
        barChart.getBarData().setValueFormatter(new IValueFormatter() {
            @Override
            public String getFormattedValue(float value, Entry entry, int dataSetIndex, ViewPortHandler viewPortHandler) {
                return entry.getData().toString();

            }
        });
    }
}
