package com.app.smartganado.smart_ganado.view.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.app.smartganado.smart_ganado.R;
import com.app.smartganado.smart_ganado.model.vo.Cattle;
import com.app.smartganado.smart_ganado.model.vo.Tank;
import com.app.smartganado.smart_ganado.utilities.Utilities;

import java.util.List;

public class TankAdapter extends BaseAdapter {
    private static LayoutInflater inflater;
    private List<Tank> data;
    private Context context;


    public TankAdapter(Context context, List<Tank> data) {
        this.context = context;
        this.data = data;
        inflater = (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
    }

    public TankAdapter() {
    }

    @Override
    public View getView(int i, View convertView, ViewGroup parent) {
        final View view = inflater.inflate(R.layout.tank_adapter, null);
        TextView name = view.findViewById(R.id.TVNombre);
        TextView capacity = view.findViewById(R.id.TVCapacidad);


        name.setText(String.valueOf(data.get(i).getName()));
        capacity.setText(String.valueOf(data.get(i).getCapacity()));

        return view;
    }

    @Override
    public int getCount() {
        return data.size();
    }

    @Override
    public Object getItem(int position) {
        return data.get(position);
    }

    @Override
    public long getItemId(int position) {
        return data.get(position).getId();
    }

}
