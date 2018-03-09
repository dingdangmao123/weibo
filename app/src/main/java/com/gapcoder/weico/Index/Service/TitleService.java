package com.gapcoder.weico.Index.Service;

import android.util.Log;

import com.gapcoder.weico.Config;
import com.gapcoder.weico.Index.Model.TitleModel;
import com.gapcoder.weico.Index.Model.WeicoModel;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.LinkedList;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by suxiaohui on 2018/3/3.
 */

public class TitleService {

    public static LinkedList<TitleModel> getTitleList() {

        try {
            OkHttpClient cli = new OkHttpClient();
            Request req = new Request.Builder().url(Config.url+"title.php").build();
            Response res = cli.newCall(req).execute();
            String json = new String(res.body().bytes(), "utf8");
            Type listType = new TypeToken<LinkedList<TitleModel>>() {
            }.getType();
            Gson gson = new Gson();
            LinkedList<TitleModel> tl = gson.fromJson(json, listType);
            return tl;
        } catch (Exception e) {
            Log.i("tag", e.toString());
        }

        return new LinkedList<TitleModel>();
    }
}
