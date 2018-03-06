package com.gapcoder.weico.Utils;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by suxiaohui on 2018/3/3.
 */

public class Curl {
    public static String getText(String link){

        StringBuilder sb = new StringBuilder();
        HttpURLConnection con=null;
        try {
            URL url=new URL(link);
            con = (HttpURLConnection) url.openConnection();
            con.setRequestMethod("GET");
            con.setRequestProperty("User-Agent",
                    "Mozilla/5.0 (Windows NT 6.3; WOW64; rv:27.0) Gecko/20100101 Firefox/49.0");
            InputStream in = con.getInputStream();
            BufferedReader br = new BufferedReader(new InputStreamReader(in));
            String str;
            while ((str = br.readLine()) != null) {
                // Log.i("url",str);
                sb.append(str);
            }
            //Log.i("utl",sb.toString());

        } catch (Exception e) {
            Log.i("error",e.toString());
        }finally {
            if(con!=null)
                con.disconnect();
        }
        return sb.toString();

    }
    public static Bitmap getImage(String link){
        HttpURLConnection urlConn=null;
        Bitmap bitmap=null;
        try {
            URL imgUrl = new URL(link);
            // 使用HttpURLConnection打开连接
            urlConn= (HttpURLConnection)imgUrl
                    .openConnection();
            urlConn.setRequestProperty("User-Agent", "Mozilla/5.0 (Windows NT 6.3; WOW64; rv:27.0) Gecko/20100101 Firefox/27.0");
            urlConn.setDoInput(true);
            urlConn.connect();

            InputStream is = urlConn.getInputStream();
            // 将InputStream转换成Bitmap
            bitmap = BitmapFactory.decodeStream(new BufferedInputStream(is));
            is.close();
        } catch (Exception e) {
            Log.i("fetchPic", e.toString());

        }finally {
            if(urlConn!=null)
                urlConn.disconnect();
        }
        return bitmap;

    }
}
