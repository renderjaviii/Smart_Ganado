package com.app.smartganado.smart_ganado.view.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.app.smartganado.smart_ganado.R;

public class CattleAdapter extends BaseAdapter {
    private static LayoutInflater inflater = null;
    Context contexto;
    String [][] datos;
    int[] datosImg;

    public CattleAdapter(Context conexto, String[][] datos, int[] imagenes) {
        this.contexto = conexto;
        this.datos = datos;
        this.datosImg = imagenes;
        inflater = (LayoutInflater)conexto.getSystemService(conexto.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public View getView(int i, View convertView, ViewGroup parent) {
        final View vista = inflater.inflate(R.layout.cattle_adapter,null);
            TextView Codigo = (TextView) vista.findViewById(R.id.InfoCodigo);
            TextView Finca = (TextView) vista.findViewById(R.id.InfoFinca);
            TextView Raza = (TextView) vista.findViewById(R.id.InfoRaza);

            ImageView imagen = (ImageView) vista.findViewById(R.id.ivImagen);

            //RatingBar calificacion = (RatingBar) vista.findViewById(R.id.ratingBarPel);

            Codigo.setText(datos[i][0]);
            Finca.setText(datos[i][1]);
            Raza.setText(datos[i][2]);
            imagen.setImageResource(datosImg[i]);

            return vista;

        }

    @Override
    public int getCount() {
        return datosImg.length;
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }


}
