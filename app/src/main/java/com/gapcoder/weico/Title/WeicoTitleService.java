package com.gapcoder.weico.Title;

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
 * Created by suxiaohui on 2018/3/4.
 */

public class WeicoTitleService {

    public static LinkedList<WeicoModel> getList(int id, int f,int tid,String title) {

        try {
            OkHttpClient cli = new OkHttpClient();
            String url="";
            if(tid!=0)
                url="http://10.0.2.2/weico/weicotitle.php?tid="+String.valueOf(tid)+"&flag="+String.valueOf(f)+"&id="+String.valueOf(id);
            else
                url="http://10.0.2.2/weico/weicotitle.php?tid="+String.valueOf(tid)+"&title="+title+"&flag="+String.valueOf(f)+"&id="+String.valueOf(id);
            Request req = new Request.Builder().url(url).build();
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
