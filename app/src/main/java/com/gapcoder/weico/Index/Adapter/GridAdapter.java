package com.gapcoder.weico.Index.Adapter;

import android.app.Activity;
import android.content.Context;
import android.util.Log;
import android.widget.ImageView;

import com.gapcoder.weico.Config;
import com.gapcoder.weico.Utils.Image;
import com.jaeger.ninegridimageview.NineGridImageViewAdapter;

/**
 * Created by suxiaohui on 2018/3/12.
 */

public class GridAdapter extends NineGridImageViewAdapter<String> {

    private Context context;

    public GridAdapter(Context context){
        this.context=context;
    }

    @Override
    protected void onDisplayImage(Context context, ImageView iv, String photo) {

        iv.setTag(Config.photo+photo);
        Log.i("tag", Config.photo+photo);
        Image.down((Activity) context, iv, Config.photo+photo);

    }

    @Override
    protected ImageView generateImageView(Context context) {
        return super.generateImageView(context);
    }


}

