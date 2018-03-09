package com.gapcoder.weico.Account;

import android.util.Log;

import com.gapcoder.weico.Config;
import com.gapcoder.weico.Index.Model.WeicoModel;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.LinkedList;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by suxiaohui on 2018/3/5.
 */

public class AccountService {
    public static LinkedList<WeicoModel> getCareList(int uid,int id, int f) {

        try {
            OkHttpClient cli = new OkHttpClient();
Log.i("tag", Config.url+"myweico.php?uid="+uid+"&flag="+String.valueOf(f)+"&id="+String.valueOf(id));
            Request req = new Request.Builder().url(Config.url+"myweico.php?uid="+uid+"&flag="+String.valueOf(f)+"&id="+String.valueOf(id)).build();
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
