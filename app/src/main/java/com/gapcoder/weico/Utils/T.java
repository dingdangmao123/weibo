package com.gapcoder.weico.Utils;

import android.content.Context;
import android.graphics.Color;
import android.view.Gravity;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by suxiaohui on 2018/3/4.
 */

public class T {
    public static void show(Context context, Object obj){
        Toast t=new Toast(context);
        t.setDuration(Toast.LENGTH_SHORT);
        t.setGravity(Gravity.CENTER,0,0);
        TextView tv=new TextView(context);
        tv.setText(obj.toString());
        t.setView(tv);
        t.show();
    }
}