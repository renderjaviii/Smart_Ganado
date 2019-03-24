package com.app.smartganado.smart_ganado.view.adapter;

import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.AlertDialogLayout;
import android.support.v7.widget.RecyclerView;
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

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

public class EstateAdapter extends ArrayAdapter  {

    private ArrayList<Estate> items;

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
        viewHolder.imageView.setImageResource(R.drawable.farmdef);
        viewHolder.textViewName.setText(items.get(position).getName());
        viewHolder.textViewLocation.setText(items.get(position).getLocation());

        row.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PopupMenu popupMenu= new PopupMenu(getContext(), v);
                popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem item) {
                        switch (item.getItemId()){
                            case R.id.item1:
                               /* Intent miIntent = new Intent(getContext(), EditEstateActivity.class);
                                getContext().startActivity(miIntent); */
                                return  true;
                            case R.id.item2:
                             /*  miIntent = new Intent(getContext(), ViewEstateInfo.class);
                                getContext().startActivity(miIntent);*/
                                return  true;
                            case R.id.item3:
                                // Petición al server de eliminar
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
