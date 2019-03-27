package com.app.smartganado.smart_ganado.view.adapter;

import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.AlertDialogLayout;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.Toast;

import com.app.smartganado.smart_ganado.R;
import com.app.smartganado.smart_ganado.model.vo.Estate;
import com.app.smartganado.smart_ganado.view.NewEstateActivity;
import com.app.smartganado.smart_ganado.view.ViewEstateActivity;
import com.google.gson.Gson;

import org.w3c.dom.Text;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class EstateAdapter extends ArrayAdapter implements Serializable {



   public static ArrayList<Estate> items;

    public ArrayList<Estate> getItems() {
        return items;
    }

    public EstateAdapter(Context context, int layaout, ArrayList<Estate> items) {
    super(context,layaout);
    this.items=items;
    }



    public static class viewHolder{
        TextView textViewName;
        TextView textViewLocation;
        ImageView imageView;
    }
    public  int getCount(){
        return items.size();
    }

    public  void add (Estate estate){
        items.add(estate);
        update(items);
    }
    public void replace (Estate estate){
        for (int i=0; i<items.size(); i++){
            if (items.get(i).getName().equals(estate.getName())){
                items.remove(items.get(i));
                items.add(estate);
            //    update(items);
            }
        }
            }
     public void update(ArrayList<Estate> result){
        items= new ArrayList<>();
        items.addAll(result);
        notifyDataSetChanged();
      }

    public View getView(final int position, @Nullable View  convertView, @NonNull ViewGroup parent){
        View row;
        row=convertView;
        viewHolder viewHolder;
        if (row==null){
            row=LayoutInflater.from(getContext()).inflate(R.layout.estate_adapter,parent,false);
            viewHolder= new viewHolder();
            viewHolder.imageView=row.findViewById(R.id.imageViewEstate);
            viewHolder.textViewName=row.findViewById(R.id.textViewNameEstate);
            viewHolder.textViewLocation=row.findViewById(R.id.textViewLocation);
            row.setTag(viewHolder);

        }else{
            viewHolder=(viewHolder) row.getTag();
        }
        Utilities utilities = new Utilities();
        if (items.get(position).getPhoto()==null) {
        viewHolder.imageView.setImageResource(R.drawable.farmdef);
        }else{
        viewHolder.imageView.setImageBitmap(utilities.Bytetobmap(items.get(position).getPhoto()));}
        viewHolder.textViewName.setText(items.get(position).getName());
        viewHolder.textViewLocation.setText(items.get(position).getLocation());
        row.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PopupMenu popupMenu= new PopupMenu(getContext(), v);
                popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem item) {
                        Intent newEstateIntent =new Intent(getContext(), NewEstateActivity.class);

                        switch (item.getItemId()){

                            case R.id.item1:
                               newEstateIntent.putExtra("Estate",new Gson().toJson(items.get(position)));
                                newEstateIntent.putExtra("choose", "1");
                                getContext().startActivity(newEstateIntent);
                                return  true;
                            case R.id.item2:
                                newEstateIntent.putExtra("Estate",new Gson().toJson(items.get(position)));
                                newEstateIntent.putExtra("choose", "2");
                                getContext().startActivity(newEstateIntent);
                                return  true;
                            case R.id.item3:
                                // Petición al server de eliminar
                                //If es favorable
                               if (items.remove(items.get(position))) {
                                   update(items);
                                   Toast.makeText(getContext(), "Se elimino correctamente", Toast.LENGTH_LONG).show();
                               }
                                else {
                                   Toast.makeText(getContext(), "Hubo un problema eliminando, intentelo más tarde", Toast.LENGTH_LONG).show();
                               }
                                return  true;
                                default: return false;


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
