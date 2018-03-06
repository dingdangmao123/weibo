package com.gapcoder.weico.Utils;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.util.Log;
import android.util.LruCache;
import android.view.View;
import android.widget.ImageView;

import com.gapcoder.weico.R;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by suxiaohui on 2018/3/3.
 */

public class Image{
    private static LruCache<String,Bitmap> ins;
    private static ExecutorService pool= Executors.newFixedThreadPool(1);
    private synchronized static LruCache<String,Bitmap> getInstance(){
        if(ins==null) {
            int max=(int)(Runtime.getRuntime().maxMemory()/1024)/8;
            ins=new LruCache<String,Bitmap>(max){
                @Override
                protected int sizeOf(String k,Bitmap bit){
                    return bit.getRowBytes()*bit.getHeight()/1024;
                }
            };
        }
        return ins;
    }
    public static Bitmap get(String k){
        return getInstance().get(k);
    }
    public synchronized static void put(String k,Bitmap v){ getInstance().put(k,v);}
    public static void down(Activity context, ImageView v, String url){
        Bitmap bit=get(url);
        if(bit!=null){
            Log.i("Unit","getFromCache "+url);
            try {
                v.setImageBitmap(bit);
            }catch(Exception e){
                Log.i("Unit",e.toString());
            }
        }else{
            getFromUrl(context,v,url);
        }
    }
    public static void getFromUrl(final Activity context, final ImageView im, final String url){
        Log.i("Unit","getFromUrl "+url);
        pool.execute(new Runnable() {
            @Override
            public void run() {
                final Bitmap bit=Curl.getImage(url);
                if(bit==null){
                    Log.i("Unit","null bitmap");
                }else if(bit!=null) {
                    put(url, bit);
                  context.runOnUiThread(new Runnable() {
                      @Override
                      public void run() {
                          if (url.equals(im.getTag().toString())) {
                              try {
                                  im.setImageBitmap(bit);
                              } catch (Exception e) {
                                  Log.i("Unit", e.toString());
                              }
                          }
                      }
                  });
                }
            }
        });

    }
}

