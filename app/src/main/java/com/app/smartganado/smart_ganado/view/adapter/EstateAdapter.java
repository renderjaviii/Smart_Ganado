package com.app.smartganado.smart_ganado.view.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.PopupMenu;
import android.widget.TextView;

import com.app.smartganado.smart_ganado.R;
import com.app.smartganado.smart_ganado.model.dao.EstateDAO;
import com.app.smartganado.smart_ganado.model.vo.Estate;
import com.app.smartganado.smart_ganado.utilities.Utilities;
import com.app.smartganado.smart_ganado.view.NewEstateActivity;
import com.app.smartganado.smart_ganado.view.ViewEstateActivity;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

public class EstateAdapter extends ArrayAdapter {


    public List<Estate> items;

    public List<Estate> getItems() {
        return items;
    }

    public EstateAdapter(Context context, int layaout, List<Estate> items) {
        super(context, layaout);
        this.items = items;
    }


    public static class viewHolder {
        TextView textViewName;
        TextView textViewLocation;
        ImageView imageView;
    }

    public int getCount() {
        return items.size();
    }

    public void add(Estate estate) {
        items.add(estate);
        notifyDataSetChanged();
    }

    public void remove(int index) {
        items.remove(index);
        notifyDataSetChanged();
    }


    public void update(ArrayList<Estate> result) {
        items = new ArrayList<>();
        items.addAll(result);
        notifyDataSetChanged();
    }

    public View getView(final int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        View row;
        row = convertView;
        viewHolder viewHolder;
        if (row == null) {
            row = LayoutInflater.from(getContext()).inflate(R.layout.estate_adapter, parent, false);
            viewHolder = new viewHolder();
            viewHolder.imageView = row.findViewById(R.id.imageViewEstate);
            viewHolder.textViewName = row.findViewById(R.id.textViewNameEstate);
            viewHolder.textViewLocation = row.findViewById(R.id.textViewLocation);
            row.setTag(viewHolder);

        } else {
            viewHolder = (viewHolder) row.getTag();
        }
        if (items.get(position).getPhoto() == null) {
            viewHolder.imageView.setImageResource(R.drawable.farmdef);
        } else {
            viewHolder.imageView.setImageBitmap(Utilities.byteToBitmap((items.get(position).getPhoto())));
        }
        viewHolder.textViewName.setText(items.get(position).getName());
        viewHolder.textViewLocation.setText(items.get(position).getLocation());
        row.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                PopupMenu popupMenu = new PopupMenu(getContext(), v);

                popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {

                    @Override
                    public boolean onMenuItemClick(MenuItem item) {

                        Intent newEstateIntent = new Intent(getContext(), NewEstateActivity.class);
                        switch (item.getItemId()) {
                            case R.id.item1:
                                newEstateIntent.putExtra("Estate", new Gson().toJson(items.get(position)));
                                newEstateIntent.putExtra("choose", "1");
                                newEstateIntent.putExtra("position", position);

                                ((ViewEstateActivity) getContext()).startActivityForResult(newEstateIntent, 2);
                                return true;
                            case R.id.item2:
                                newEstateIntent.putExtra("Estate", new Gson().toJson(items.get(position)));
                                newEstateIntent.putExtra("choose", "2");
                                getContext().startActivity(newEstateIntent);
                                return true;

                            case R.id.item3:
                                EstateDAO.deleteEstate(getContext(), items.get(position).getId());

                                items.remove(position);
                                notifyDataSetChanged();

                                return true;
                            default:
                                return false;


                        }
                    }
                });
                popupMenu.inflate(R.menu.popupmenu);
                popupMenu.show();
            }
        });

        return row;

    }

}
