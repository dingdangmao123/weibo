package com.gapcoder.weico.Comment;

import android.util.Log;

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

public class CommentService {

    public static Comm getList(int wid, int id, int f) {

        try {
            OkHttpClient cli = new OkHttpClient();
            String url="http://10.0.2.2/weico/comment.php?wid="+wid+"&flag="+String.valueOf(f)+"&id="+String.valueOf(id);
            Log.i("tag",url);
            Request req = new Request.Builder().url(url).build();
            Response res = cli.newCall(req).execute();
            String json = new String(res.body().bytes(), "utf8");
           /* Type listType = new TypeToken<LinkedList<CommentModel>>() {
            }.getType();*/
            Gson gson = new Gson();
            Comm tl = gson.fromJson(json, Comm.class);
            return tl;
        } catch (Exception e) {
            Log.i("tag", e.toString());
        }

        return null;
    }
}
