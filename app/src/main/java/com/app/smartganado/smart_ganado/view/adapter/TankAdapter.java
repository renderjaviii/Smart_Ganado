package com.app.smartganado.smart_ganado.view.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.app.smartganado.smart_ganado.R;
import com.app.smartganado.smart_ganado.model.dao.EstateDAO;
import com.app.smartganado.smart_ganado.model.vo.Estate;
import com.app.smartganado.smart_ganado.model.vo.Tank;
import com.app.smartganado.smart_ganado.model.vo.UserApp;

import java.util.List;

public class TankAdapter extends BaseAdapter {
    private static LayoutInflater inflater;
    private List<Tank> data;
    private Context context;
    private UserApp user;


    public TankAdapter(Context context, List<Tank> data, UserApp user) {
        this.context = context;
        this.data = data;
        this.user = user;

        inflater = (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
    }

    public TankAdapter() {
    }

    @Override
    public View getView(int i, View convertView, ViewGroup parent) {
        final View view = inflater.inflate(R.layout.tank_adapter, null);

        TextView name = view.findViewById(R.id.textVName);
        TextView capacity = view.findViewById(R.id.textVCapacity);
        TextView estate = view.findViewById(R.id.textVEstate);

        name.setText(String.valueOf(data.get(i).getName()));
        capacity.setText(String.valueOf(data.get(i).getCapacity()+" Lt"));

        EstateDAO.getEstates(user.getPhone(), null);

        for (Estate e : EstateDAO.getEstateList()) {
            if (e.getId() == data.get(i).getIdEstate())
                estate.setText(e.getName());
        }

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
