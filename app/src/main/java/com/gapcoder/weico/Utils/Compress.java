package com.gapcoder.weico.Utils;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import java.io.FileDescriptor;

/**
 * Created by suxiaohui on 2018/3/13.
 */

public class Compress {

    public static Bitmap decodeFile(String file, int w, int h) {

        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeFile(file, options);
        options.inSampleSize = sampleSize(options, w, h);
        options.inJustDecodeBounds = false;
        return BitmapFactory.decodeFile(file, options);
    }

    public static Bitmap decodeFileDes(FileDescriptor fd, int w, int h) {

        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeFileDescriptor(fd, null, options);
        options.inSampleSize = sampleSize(options, w, h);
        options.inJustDecodeBounds = false;
        return BitmapFactory.decodeFileDescriptor(fd, null, options);
    }

    private static int sampleSize(BitmapFactory.Options options, int w, int h) {
        int width = options.outWidth;
        int height = options.outHeight;
        int sampleSize = 1;
        if (width > w || height > h) {
            int widthRadio = Math.round(width * 1.0f / w);
            int heightRadio = Math.round(height * 1.0f / h);
            sampleSize = Math.max(widthRadio, heightRadio);
        }

        return sampleSize;
    }
}
