package com.app.smartganado.smart_ganado.utilities;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.RectF;
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


    public static Bitmap getRoundedCornerBitmap(Bitmap bitmap, int pixels) {
        Bitmap output = Bitmap.createBitmap(bitmap.getWidth(), bitmap.getHeight(), Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(output);

        final int color = 0xff424242;
        final Paint paint = new Paint();
        final Rect rect = new Rect(0, 0, bitmap.getWidth(), bitmap.getHeight());
        final RectF rectF = new RectF(rect);
        final float roundPx = pixels;

        paint.setAntiAlias(true);
        canvas.drawARGB(0, 0, 0, 0);
        paint.setColor(color);
        canvas.drawRoundRect(rectF, roundPx, roundPx, paint);

        paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_IN));
        canvas.drawBitmap(bitmap, rect, rect, paint);

        return output;
    }
}
