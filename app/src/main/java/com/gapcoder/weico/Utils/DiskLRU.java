package com.gapcoder.weico.Utils;

import android.content.Context;
import android.os.Environment;
import android.util.Log;

import com.bumptech.glide.disklrucache.DiskLruCache;

import java.io.File;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * Created by suxiaohui on 2018/3/13.
 */

public class DiskLRU {

    private static int max = 1024 * 1024 * 100;
    private static DiskLruCache disklru = null;

    private DiskLRU(Context context) {

    }

    public static DiskLruCache getInstance() {
        return disklru;
    }

    public static File getCacheDir(Context context) {
        String path;
        if (Environment.MEDIA_MOUNTED.equals(Environment
                .getExternalStorageState())
                || !Environment.isExternalStorageRemovable()) {
            path = context.getExternalCacheDir().getPath();
        } else {
            path = context.getCacheDir().getPath();
        }
        return new File(path + File.separator + "WeicoCache");
    }

    public synchronized static void init(Context context) {
        File dir = getCacheDir(context);
        if (!dir.exists()) {
            dir.mkdirs();
        }
        try {
            disklru = DiskLruCache.open(dir, 1, 1, max);
        } catch (Exception e) {
            Log.i("tag", e.toString());
        }
    }


    public static String Hash(String key) {
        String cacheKey;
        try {
            final MessageDigest mDigest = MessageDigest.getInstance("MD5");
            mDigest.update(key.getBytes());
            cacheKey = bytesToHexString(mDigest.digest());
        } catch (NoSuchAlgorithmException e) {
            cacheKey = String.valueOf(key.hashCode());
        }
        return cacheKey;
    }

    private static String bytesToHexString(byte[] bytes) {

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < bytes.length; i++) {
            String hex = Integer.toHexString(0xFF & bytes[i]);
            if (hex.length() == 1) {
                sb.append('0');
            }
            sb.append(hex);
        }
        return sb.toString();
    }


}
