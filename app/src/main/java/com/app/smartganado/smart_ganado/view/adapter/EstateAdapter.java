package com.app.smartganado.smart_ganado.view.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

public class EstateAdapter extends BaseAdapter {
    private static LayoutInflater inflater = null;
    private Context context;
    private String[][] data;
    private Integer[] imgData;

    public EstateAdapter(Context context, String[][] data, Integer[] imgData) {
        this.context = context;
        this.data = data;
        this.imgData = imgData;
        inflater = (LayoutInflater)context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return 0;
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        return null;
    }
}
