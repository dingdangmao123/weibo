package com.gapcoder.weico.Message.Service;

import android.util.Log;

import com.gapcoder.weico.Comment.Comm;
import com.gapcoder.weico.Config;
import com.gapcoder.weico.Message.Model.AtModel;
import com.gapcoder.weico.Utils.Token;
import com.google.gson.Gson;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by suxiaohui on 2018/3/8.
 */

public class AtService {
    public static AtModel getList(int id, int f) {

        try {
            OkHttpClient cli = new OkHttpClient();
            String url= Config.url+"at.php?token="+ Token.token+"&flag="+String.valueOf(f)+"&id="+String.valueOf(id);
            Log.i("tag",url);
            Request req = new Request.Builder().url(url).build();
            Response res = cli.newCall(req).execute();
            String json = new String(res.body().bytes(), "utf8");
           /* Type listType = new TypeToken<LinkedList<CommentModel>>() {
            }.getType();*/
            Gson gson = new Gson();
            AtModel t = gson.fromJson(json, AtModel.class);
            return t;
        } catch (Exception e) {
            Log.i("tag", e.toString());
        }

        return null;
    }
}
