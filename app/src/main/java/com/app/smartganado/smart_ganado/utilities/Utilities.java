package com.app.smartganado.smart_ganado.utilities;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.widget.ImageView;

import java.io.ByteArrayOutputStream;

public class Utilities {

    public static byte[] imageViewToByte(ImageView image) {
        Bitmap bitmap = ((BitmapDrawable) image.getDrawable()).getBitmap();
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG, 5, baos);

        return baos.toByteArray();
    }

    public static Bitmap byteToBitmap(byte[] byteArr) {
        return BitmapFactory.decodeByteArray(byteArr, 0, byteArr.length);
    }

    public static byte[] bitmapToByte(Bitmap bitmap) {
        ByteArrayOutputStream opstream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 5, opstream);
        byte[] bytArray = opstream.toByteArray();

        return bytArray;
    }

}
