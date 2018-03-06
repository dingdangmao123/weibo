package com.gapcoder.weico.Utils;

import android.util.Log;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by suxiaohui on 2018/3/3.
 */

public class Pool {
    private static ExecutorService pool= Executors.newFixedThreadPool(1);

    public static  void run(Runnable r){
        try {
            pool.execute(r);
        }catch(Exception e){
            Log.i("tag",e.toString());
        }
    }
}
