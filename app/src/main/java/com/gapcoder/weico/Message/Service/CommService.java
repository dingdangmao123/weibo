package com.gapcoder.weico.Message.Service;

import android.util.Log;

import com.gapcoder.weico.Config;
import com.gapcoder.weico.Index.Model.WeicoModel;
import com.gapcoder.weico.Message.Model.AtModel;
import com.gapcoder.weico.Message.Model.CommModel;
import com.gapcoder.weico.Utils.Token;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.LinkedList;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by suxiaohui on 2018/3/8.
 */

public class CommService {

    public static  LinkedList<CommModel> getList(int id, int f) {

        try {
            OkHttpClient cli = new OkHttpClient();
            String url= Config.url+"comm.php?token="+ Token.token+"&flag="+String.valueOf(f)+"&id="+String.valueOf(id);
            Log.i("tag",url);
            Request req = new Request.Builder().url(url).build();
            Response res = cli.newCall(req).execute();
            String json = new String(res.body().bytes(), "utf8");
            Type listType = new TypeToken<LinkedList<CommModel>>() {
            }.getType();
            Gson gson = new Gson();
            LinkedList<CommModel> tl = gson.fromJson(json, listType);
            return tl;
        } catch (Exception e) {
            Log.i("tag", e.toString());
        }

        return new LinkedList<CommModel>();
    }

}
