package com.example.student.workshop;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;

public class MyService1 extends Service {

    public MyService1() {
    }

    @Override
    public void onCreate() {
        super.onCreate();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        if(intent == null) {
            return Service.START_STICKY;
        }else{
            final Intent showIntent = new Intent(getApplicationContext(), MainActivity.class);
            showIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK |
                    Intent.FLAG_ACTIVITY_SINGLE_TOP |
                    Intent.FLAG_ACTIVITY_CLEAR_TOP);

            showIntent.putExtra("command","MyService1");
            Runnable run = new Runnable() {
                @Override
                public void run() {
                    for (int i = 0; i < 5; i++) {
                        Log.d("MyService1", "Process: " + (i + 1));
                        try {
                            Thread.sleep(1000);
                            showIntent.putExtra("time",(i+1));
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }

                    startActivity(showIntent);
                }
            };
            new Thread(run).start();
        }

        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }
}
