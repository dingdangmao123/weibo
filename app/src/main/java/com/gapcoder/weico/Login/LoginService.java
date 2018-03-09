package com.gapcoder.weico.Login;

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
 * Created by suxiaohui on 2018/3/8.
 */

public class LoginService {

    public static LoginModel login(String key, String psd) {
        try {
            OkHttpClient cli = new OkHttpClient();
            RequestBody rb = new FormBody.Builder().add("key",""+key).
                    add("psd", psd).build();
            Request req = new Request.Builder().url(Config.url+"login.php").post(rb).build();
            Response res = cli.newCall(req).execute();
            String json = new String(res.body().bytes(), "utf8");
            Log.i("tag",json);
            Gson gson = new Gson();
            LoginModel tl = gson.fromJson(json, LoginModel.class);
            return tl;
        } catch (Exception e) {
            Log.i("tag", e.toString());
        }
        return null;
    }
}
