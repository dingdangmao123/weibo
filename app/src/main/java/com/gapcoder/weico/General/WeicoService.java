package com.gapcoder.weico.General;

import android.util.Log;

import com.gapcoder.weico.Index.Model.*;
import com.gapcoder.weico.Index.Model.WeicoModel;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.LinkedList;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by suxiaohui on 2018/3/6.
 */

public class WeicoService {

    public static WeicoModel getWeico(int id) {

        try {
            OkHttpClient cli = new OkHttpClient();
            Log.i("tag","http://10.0.2.2/weico/get.php?id="+String.valueOf(id));
            Request req = new Request.Builder().url("http://10.0.2.2/weico/get.php?id="+String.valueOf(id)).build();
            Response res = cli.newCall(req).execute();
            String json = new String(res.body().bytes(), "utf8");
            Gson gson = new Gson();
            WeicoModel tl = gson.fromJson(json, WeicoModel.class);
            return tl;
        } catch (Exception e) {
            Log.i("tag", e.toString());
        }
        return null;
    }
}
