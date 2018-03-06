package com.gapcoder.weico.UserList;

import android.util.Log;

import com.gapcoder.weico.Index.Model.TitleModel;
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

public class UserListService {

    public static LinkedList<UserListModel> getFans(int flag,int id,int uid) {

        try {
            OkHttpClient cli = new OkHttpClient();
            String url="http://10.0.2.2/weico/myfans.php?uid="+String.valueOf(uid)+"&flag="+String.valueOf(flag)+"&id="+String.valueOf(id);
            Log.i("tag",url);
            Request req = new Request.Builder().url(url).build();
            Response res = cli.newCall(req).execute();
            String json = new String(res.body().bytes(), "utf8");
            Type listType = new TypeToken<LinkedList<UserListModel>>() {
            }.getType();
            Gson gson = new Gson();
            LinkedList<UserListModel> tl = gson.fromJson(json, listType);
            return tl;
        } catch (Exception e) {
            Log.i("tag", e.toString());
        }

        return new LinkedList<UserListModel>();
    }
    public static LinkedList<UserListModel> getCare(int flag,int id,int uid) {

        try {
            OkHttpClient cli = new OkHttpClient();
            String url="http://10.0.2.2/weico/mycare.php?uid="+String.valueOf(uid)+"&flag="+String.valueOf(flag)+"&id="+String.valueOf(id);
            Log.i("tag",url);
            Request req = new Request.Builder().url(url).build();
            Response res = cli.newCall(req).execute();
            String json = new String(res.body().bytes(), "utf8");
            Type listType = new TypeToken<LinkedList<UserListModel>>() {
            }.getType();
            Gson gson = new Gson();
            LinkedList<UserListModel> tl = gson.fromJson(json, listType);
            return tl;
        } catch (Exception e) {
            Log.i("tag", e.toString());
        }

        return new LinkedList<UserListModel>();
    }
}
