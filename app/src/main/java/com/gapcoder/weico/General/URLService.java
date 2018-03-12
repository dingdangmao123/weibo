package com.gapcoder.weico.General;

import android.util.Log;

import com.gapcoder.weico.Config;
import com.google.gson.Gson;

import java.io.File;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import okhttp3.FormBody;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

/**
 * Created by suxiaohui on 2018/3/10.
 */

public class URLService {

    public  static <T extends SysMsg> SysMsg post(String url,HashMap<String,String> map,Class<T> clz) {

        try {
            OkHttpClient cli = new OkHttpClient();
            FormBody.Builder rb = new FormBody.Builder();
            Iterator<String> it=map.keySet().iterator();
            while(it.hasNext()){
                String k=it.next();
                String v=map.get(k);
                rb.add(k,v);
            }
            Request req = new Request.Builder().url(Config.url+url).post(rb.build()).build();
            Response res = cli.newCall(req).execute();
            String json = new String(res.body().bytes(), "utf8");
            Log.i("tag",json);
            Gson gson = new Gson();
            SysMsg m= gson.fromJson(json, clz);
            return m;

        } catch (Exception e) {
            Log.i("tag", e.toString());
            return new SysMsg("error",e.toString());
        }
    }
    public  static <T extends SysMsg> SysMsg get(String url,Class<T> clz) {

        try {

            OkHttpClient cli = new OkHttpClient();
            Request req = new Request.Builder().url(Config.url+url).build();
            Response res = cli.newCall(req).execute();
            String json = new String(res.body().bytes(), "utf8");
            Log.i("tag",json);
            Gson gson = new Gson();
            SysMsg m= gson.fromJson(json, clz);
            return m;

        } catch (Exception e) {

            Log.i("tag", e.toString());
            return new SysMsg("error",e.toString());
        }

    }

    public  static <T extends SysMsg> SysMsg upload(String url, HashMap<String,String> map, List<String> file, Class<T> clz) {

        try {
            OkHttpClient cli = new OkHttpClient();
            MultipartBody.Builder builder = new MultipartBody.Builder()
                    .setType(MultipartBody.FORM);

            Iterator<String> it=map.keySet().iterator();
            while(it.hasNext()){
                String k=it.next();
                String v=map.get(k);
                builder.addFormDataPart(k,v);
            }

            for(int i=0;i<file.size();i++) {
                String[] suffix=file.get(i).split("\\.");
                builder.addFormDataPart("image"+i, file.get(i),
                        RequestBody.create(MediaType.parse("image/"+suffix[suffix.length-1]), new File(file.get(i))));
            }

            Request req = new Request.Builder().url(Config.url+url).post(builder.build()).build();
            Response res = cli.newCall(req).execute();
            String json = new String(res.body().bytes(), "utf8");
            Log.i("tag",json);
            Gson gson = new Gson();
            SysMsg m= gson.fromJson(json, clz);
            return m;

        } catch (Exception e) {
            Log.i("tag", e.toString());
            return new SysMsg("error",e.toString());
        }
    }

}
