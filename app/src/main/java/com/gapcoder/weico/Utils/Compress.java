package com.gapcoder.weico.Utils;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;

import java.io.InputStream;

/**
 * Created by suxiaohui on 2018/3/13.
 */

public class Compress{

    public static Bitmap getBitmap(InputStream is, int destWidth, int destHeight) {

        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeStream(is, null,options);
        try {
            is.reset();
        }catch(Exception e){
            Log.i("tag",e.toString());
        }

        int width = options.outWidth;
        int height = options.outHeight;
        int sampleSize = 1;

        if (width > destWidth || height > destHeight)
        {
            int widthRadio = Math.round(width * 1.0f / destWidth);
            int heightRadio = Math.round(height * 1.0f / destHeight);
            sampleSize = Math.max(widthRadio, heightRadio);
        }
        Log.i("tag",""+sampleSize);
        options.inJustDecodeBounds = false;
        options.inSampleSize = sampleSize;
        return BitmapFactory.decodeStream(is, null,options);
    }

    public static Bitmap decodeFile(String file, int destWidth, int destHeight) {

        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeFile(file,options);
        int width = options.outWidth;
        int height = options.outHeight;
        int sampleSize = 1;
        if (width > destWidth || height > destHeight)
        {
            int widthRadio = Math.round(width * 1.0f / destWidth);
            int heightRadio = Math.round(height * 1.0f / destHeight);
            sampleSize = Math.max(widthRadio, heightRadio);
        }
        Log.i("tag",""+sampleSize);
        options.inJustDecodeBounds = false;
        options.inSampleSize = sampleSize;
        return BitmapFactory.decodeFile(file,options);
    }
}
