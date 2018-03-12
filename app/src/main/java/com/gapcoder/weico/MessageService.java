package com.gapcoder.weico;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.support.annotation.IntDef;

public class MessageService extends Service {

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
            @Override
            public void run() {

                while(true){
                    try {
                        Thread.sleep(5000);
                    }catch(Exception e){

                    }
                    Intent i=new Intent("com.gapcoder.weico.MESSAGE");
                    i.putExtra("num",2);
                    sendBroadcast(i);
                }
            }
        }).start();

        return super.onStartCommand(intent, flags, startId);
    }
}
