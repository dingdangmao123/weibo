package com.gapcoder.weico.General;

import android.util.Log;

import com.gapcoder.weico.Config;
import com.google.gson.Gson;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by suxiaohui on 2018/3/5.
 */

public class UserService {

    public static UserModel getUser(int id) {

        try {
            OkHttpClient cli = new OkHttpClient();
            Request req = new Request.Builder().url(Config.url+"user.php?id="+String.valueOf(id)).build();
            Response res = cli.newCall(req).execute();
            String json = new String(res.body().bytes(), "utf8");
            Gson gson = new Gson();
            UserModel u = gson.fromJson(json,UserModel.class);
            return u;
        } catch (Exception e) {
            Log.i("tag", e.toString());
        }

        return null;
    }
}
