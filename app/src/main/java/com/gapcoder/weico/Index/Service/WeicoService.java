package com.gapcoder.weico.Index.Service;

import android.util.Log;

import com.gapcoder.weico.Config;
import com.gapcoder.weico.Index.Model.WeicoModel;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.LinkedList;

import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

/**
 * Created by suxiaohui on 2018/3/2.
 */

public class WeicoService {


    public static LinkedList<WeicoModel>  getNewList(int id,int f) {

        try {
            OkHttpClient cli = new OkHttpClient();
            Log.i("tag", Config.url+"new.php?flag="+String.valueOf(f)+"&id="+String.valueOf(id));
            Request req = new Request.Builder().url("http://10.0.2.2/weico/new.php?flag="+String.valueOf(f)+"&id="+String.valueOf(id)).build();
            Response res = cli.newCall(req).execute();
            String json = new String(res.body().bytes(), "utf8");
            Type listType = new TypeToken<LinkedList<WeicoModel>>() {
            }.getType();
            Gson gson = new Gson();
            LinkedList<WeicoModel> tl = gson.fromJson(json, listType);
            return tl;
        } catch (Exception e) {
            Log.i("tag", e.toString());
        }

        return new LinkedList<WeicoModel>();
    }

    public static LinkedList<WeicoModel>  getHotList(int id,int f) {

        try {
            OkHttpClient cli = new OkHttpClient();
            Log.i("tag","http://10.0.2.2/weico/hot.php?flag="+String.valueOf(f)+"&id="+String.valueOf(id));
            Request req = new Request.Builder().url("http://10.0.2.2/weico/hot.php?flag="+String.valueOf(f)+"&id="+String.valueOf(id)).build();
            Response res = cli.newCall(req).execute();
            String json = new String(res.body().bytes(), "utf8");
            Type listType = new TypeToken<LinkedList<WeicoModel>>() {
            }.getType();
            Gson gson = new Gson();
            LinkedList<WeicoModel> tl = gson.fromJson(json, listType);
            return tl;
        } catch (Exception e) {
            Log.i("tag", e.toString());
        }

        return new LinkedList<WeicoModel>();
    }

    public static LinkedList<WeicoModel>  getCareList(int id,int f) {

        try {
            OkHttpClient cli = new OkHttpClient();
            Log.i("tag","http://10.0.2.2/weico/care.php?flag="+String.valueOf(f)+"&id="+String.valueOf(id));
            Request req = new Request.Builder().url("http://10.0.2.2/weico/care.php?flag="+String.valueOf(f)+"&id="+String.valueOf(id)).build();
            Response res = cli.newCall(req).execute();
            String json = new String(res.body().bytes(), "utf8");
            Type listType = new TypeToken<LinkedList<WeicoModel>>() {
            }.getType();
            Gson gson = new Gson();
            LinkedList<WeicoModel> tl = gson.fromJson(json, listType);
            return tl;
        } catch (Exception e) {
            Log.i("tag", e.toString());
        }

        return new LinkedList<WeicoModel>();
    }
}
