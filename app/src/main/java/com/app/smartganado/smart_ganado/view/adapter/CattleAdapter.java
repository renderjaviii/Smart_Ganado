package com.app.smartganado.smart_ganado.view.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.app.smartganado.smart_ganado.R;
import com.app.smartganado.smart_ganado.model.vo.Cattle;
import com.app.smartganado.smart_ganado.utilities.Utilities;

import java.util.List;

public class CattleAdapter extends BaseAdapter {
    private static LayoutInflater inflater;
    private List<Cattle> data;
    private Context context;


    public CattleAdapter(Context context, List<Cattle> data) {
        this.context = context;
        this.data = data;
        inflater = (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
    }

    public CattleAdapter() {
    }

    @Override
    public View getView(int i, View convertView, ViewGroup parent) {
        final View view = inflater.inflate(R.layout.cattle_adapter, null);
        TextView code = view.findViewById(R.id.codeInfo);
        TextView gender = view.findViewById(R.id.genderInfo);
        TextView weight = view.findViewById(R.id.weightInfo);
        ImageView image = view.findViewById(R.id.imageInfo);

        try {
            code.setText("#" + String.valueOf(data.get(i).getCode()));
            gender.setText(((data.get(i)).getIdGender() == 1 ? "Hembra" : "Macho"));
            weight.setText(String.valueOf(data.get(i).getWeight()) + " Kg");
            image.setImageBitmap(Utilities.getRoundedCornerBitmap(Utilities.byteToBitmap(data.get(i).getPhoto()), 100));
        } catch (Exception e) {
            Log.i("server", "exception in CattleAdaper" + e.getMessage());
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
