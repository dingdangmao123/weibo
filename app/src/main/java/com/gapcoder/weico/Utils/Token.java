package com.gapcoder.weico.Utils;

import android.content.Context;
import android.content.SharedPreferences;

import static android.content.Context.MODE_PRIVATE;

/**
 * Created by suxiaohui on 2018/3/8.
 */

public class Token {
    public static String token="4063acc5eeece1cf92deabd6d4b531c38ef8a282";
    public static void exit(Context context){
        SharedPreferences.Editor editor = context.getSharedPreferences("token", MODE_PRIVATE).edit();
        editor.putString("token", "");
        editor.apply();
        token="";
    }
    public static void initToken(Context context){
        SharedPreferences p = context.getSharedPreferences("token", MODE_PRIVATE);
        token= p.getString("name", "");
    }
    public static void initToken(Context context,String token){
        SharedPreferences.Editor editor = context.getSharedPreferences("token", MODE_PRIVATE).edit();
        editor.putString("token",token);
        editor.apply();
        Token.token=token;
    }
}
