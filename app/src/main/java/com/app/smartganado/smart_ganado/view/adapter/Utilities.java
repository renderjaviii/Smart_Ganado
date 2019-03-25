package com.app.smartganado.smart_ganado.view.adapter;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.widget.ImageView;

import java.nio.ByteBuffer;

public class Utilities {
    public byte[] ImageViewtoByte(ImageView imagen) {
        imagen.buildDrawingCache();
        Bitmap bmap = imagen.getDrawingCache();
        int size = 29;

        byte[] byteArray;

        ByteBuffer byteBuffer = ByteBuffer.allocate(size);
        bmap.copyPixelsFromBuffer(byteBuffer);
        byteArray = byteBuffer.array();
        return byteArray;
    }

   public Bitmap Bytetobmap(byte[] byteArray){
        Bitmap bmap = BitmapFactory.decodeByteArray(byteArray,0, byteArray.length);
        return bmap;
    }


}
