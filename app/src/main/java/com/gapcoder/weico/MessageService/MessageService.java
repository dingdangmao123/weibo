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
    int delay=5000;
    long last=0;
    public MessageService() {

    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw null;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        init();
        task();
        return START_STICKY;
    }

    private void task(){
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    while(true) {
                        Thread.sleep(3 * delay);
                        if(System.currentTimeMillis()-last<3*delay){
                            continue;
                        }
                        if(s!=null){
                            if(!s.isClosed())
                                s.close();
                            Thread.sleep(delay);
                            init();
                        }
                    }
                }catch(Exception e){
                    Log.i("tag",e.toString());
                }
            }
        }).start();

    }
    private void init(){
        new Thread(new Runnable() {
            public void run() {
                try {
                    Log.i("tag","r1 start");
                    s = new Socket("10.0.2.2", 8888);
                    OutputStream os = s.getOutputStream();
                    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(os));
                    while(true){
                        bw.write("{\"token\":\""+Token.token+"\",\"num\":0}\n");
                        bw.flush();
                        Thread.sleep(delay);
                    }
                }catch (Exception e){
                    Log.w("tag",e.toString());
                }

                Log.i("tag","r1 exit");
            }
        }).start();


        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Log.i("tag","r2 start");
                    Thread.sleep(delay);
                    InputStream is = s.getInputStream();
                    BufferedReader br=new BufferedReader(new InputStreamReader(is));
                    while(true) {
                        String s=br.readLine();
                        Log.i("tag",s);
                        Message ins=gson.fromJson(s,Message.class);
                        if(ins.getNum()>0) {
                            Intent i = new Intent("com.gapcoder.weico.MESSAGE");
                            i.putExtra("num", ins.getNum());
                            sendBroadcast(i);
                        }
                        last=System.currentTimeMillis();
                    }
                }catch(Exception e){
                    Log.w("tag",e.toString());
                }
                Log.i("tag","r2 exit");
            }
        }).start();
    }
}
