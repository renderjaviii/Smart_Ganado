package com.app.smartganado.smart_ganado.view;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import com.app.smartganado.smart_ganado.R;
import com.app.smartganado.smart_ganado.model.dao.EstateDAO;
import com.app.smartganado.smart_ganado.model.vo.Estate;
import com.app.smartganado.smart_ganado.model.vo.UserApp;
import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.highlight.Highlight;
import com.github.mikephil.charting.listener.OnChartValueSelectedListener;

import java.util.List;

public class ViewIndicatorsActivity extends AppCompatActivity {
    //  private PieChart pieChart;
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


        context = this;
        //  pieChart= findViewById(R.id.pieChart);
        barChart = findViewById(R.id.barChart);
        createPieChart();
        createBarChart();
        /*pieChart.setOnChartValueSelectedListener(new OnChartValueSelectedListener() {
            @Override
            public void onValueSelected(Entry e, Highlight h) {

                Toast.makeText(context, String.valueOf(((Estate) e.getData()).getId()), Toast.LENGTH_LONG).show();
            }

            @Override
            public void onNothingSelected() {

            }
        });*/
    }

    public UserApp getUser(Bundle homeBundle) {
        return (UserApp) homeBundle.getSerializable("user");
    }

    private void createPieChart() {
        /*estateList= EstateDAO.getEstateList();
        Description description= new Description();
        description.setText("");
        pieChart.setDescription(description);
        PieDataSet pieDataSet = new PieDataSet(EstateDAO.getPieEntries(),"FINCAS");
        pieDataSet.setColors(ColorTemplate.COLORFUL_COLORS);
        PieData pieData= new PieData(pieDataSet);
        pieChart.setData(pieData);
        EstateDAO.getEstatesWA(Long.valueOf(1234),pieChart,context);*/

    }

    private void createBarChart() {
        EstateDAO.getEstatesWA(user.getPhone(), barChart, context);

        Toast.makeText(context, String.valueOf(EstateDAO.getEstateList().size()), Toast.LENGTH_LONG).show();
        barChart.setOnChartValueSelectedListener(new OnChartValueSelectedListener() {
            @Override
            public void onValueSelected(Entry e, Highlight h) {

                Intent inicatorsIntent = new Intent(context, ViewPiechartActivity.class);
                Estate estate = (Estate) e.getData();
                inicatorsIntent.putExtra("estate", estate);
                startActivity(inicatorsIntent);
                Toast.makeText(context, String.valueOf(EstateDAO.getEstateList().size()), Toast.LENGTH_LONG).show();


            }

            @Override
            public void onNothingSelected() {

            }
        });

    }
}
