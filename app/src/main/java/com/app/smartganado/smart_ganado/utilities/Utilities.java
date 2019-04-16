package com.app.smartganado.smart_ganado.utilities;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.widget.ImageView;

import java.io.ByteArrayOutputStream;

public class Utilities {

    public static Byte[] imageViewToByte(ImageView image) {
        Bitmap bitmap = ((BitmapDrawable) image.getDrawable()).getBitmap();
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG, 20, baos);
        return getBytes(baos.toByteArray());
    }

    public static Bitmap byteToBitmap(Byte[] byteArr) {
        return BitmapFactory.decodeByteArray(getBytes(byteArr), 0, byteArr.length);
    }


    public static byte[] bitmapToByte(Bitmap bitmap) {
        ByteArrayOutputStream opstream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 20, opstream);
        byte[] bytArray = opstream.toByteArray();
        return bytArray;
    }

    public static Byte[] getBytes(byte[] byteArr) {
        Byte[] arrTemp = new Byte[byteArr.length];
        for (int i = 0; i < byteArr.length; i++)
            arrTemp[i] = byteArr[i];
        return arrTemp;
    }

    public static byte[] getBytes(Byte[] byteArr) {
        byte[] arrTemp = new byte[byteArr.length];
        for (int i = 0; i < byteArr.length; i++)
            arrTemp[i] = byteArr[i];
        return arrTemp;
    }
}
