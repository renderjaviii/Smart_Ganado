package com.app.smartganado.smart_ganado.view;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.v7.app.AppCompatActivity;

import com.app.smartganado.smart_ganado.R;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;

import java.util.ArrayList;

import androidx.annotation.Nullable;

public class ViewMilkProduction extends AppCompatActivity {
    private LineChart lineChart;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_milk);

        lineChart=findViewById(R.id.lineChart);
        ArrayList<Entry> yEntries= new ArrayList<>();
        yEntries.add(new Entry(0,50f));
        yEntries.add(new Entry(1,55f));
        yEntries.add(new Entry(2,51f));
        yEntries.add(new Entry(3,52f));
        yEntries.add(new Entry(4,45f));
        yEntries.add(new Entry(5,44f));
        yEntries.add(new Entry(6,66f));
        yEntries.add(new Entry(7,61f));
        yEntries.add(new Entry(8,30f));

        LineDataSet lineDataSet= new LineDataSet(yEntries,"Información lechera");

        LineData data= new LineData(lineDataSet);

        lineChart.setData(data);


    }
}
