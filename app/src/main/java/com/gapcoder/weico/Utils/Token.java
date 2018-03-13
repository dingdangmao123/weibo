package com.gapcoder.weico.Utils;

import android.content.Context;
import android.content.SharedPreferences;

import static android.content.Context.MODE_PRIVATE;

/**
 * Created by suxiaohui on 2018/3/8.
 */

public class Token {
    public static String token="d0d0e32f98beddbc4d1e144f7c41eafae0e53104";
    public static void exit(Context context){
        SharedPreferences.Editor editor = context.getSharedPreferences("token", MODE_PRIVATE).edit();
        editor.putString("token", "");
        editor.apply();
        token="";
    }
    public static void initToken(Context context){
        SharedPreferences p = context.getSharedPreferences("token", MODE_PRIVATE);
        token= p.getString("token", "");
    }
    public static void initToken(Context context,String token){
        SharedPreferences.Editor editor = context.getSharedPreferences("token", MODE_PRIVATE).edit();
        editor.putString("token",token);
        editor.apply();
        Token.token=token;
    }
}
