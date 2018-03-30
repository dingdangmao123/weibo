package com.gapcoder.weico.MessageService;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;

import com.gapcoder.weico.Utils.Token;
import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.Socket;

public class MessageService extends Service {

    Socket s=null;
    Gson gson=new Gson();
    public MessageService() {

    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw null;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {

        new Thread(new Runnable() {
            public void run() {
                try {
                    s = new Socket("10.0.2.2", 8888);
                    OutputStream os = s.getOutputStream();
                    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(os));
                    while(true){
                        Log.i("tag","{\"token\":\""+Token.token+"\",\"num\":0}\n");
                        bw.write("{\"token\":\""+Token.token+"\",\"num\":0}\n");
                        bw.flush();
                        Thread.sleep(5000);
                    }
                }catch (Exception e){
                    Log.w("tag",e.toString());
                }
            }
        }).start();


        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(5000);
                    InputStream is = s.getInputStream();
                    BufferedReader br=new BufferedReader(new InputStreamReader(is));
                    while(true) {
                        String s=br.readLine();
                        Message ins=gson.fromJson(s,Message.class);
                        if(ins.getNum()>0) {
                            Intent i = new Intent("com.gapcoder.weico.MESSAGE");
                            i.putExtra("num", ins.getNum());
                            sendBroadcast(i);
                        }
                    }
                }catch(Exception e){
                    Log.w("tag",e.toString());
                }
            }
        }).start();
        return START_STICKY;
    }
}
