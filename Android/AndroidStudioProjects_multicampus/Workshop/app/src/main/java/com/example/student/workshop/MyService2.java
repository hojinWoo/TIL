package com.example.student.workshop;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;

public class MyService2 extends Service {
    public MyService2() {
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
            //int cnt = intent.getIntExtra("cnt", 0);
            final Intent showIntent = new Intent(getApplicationContext(), MainActivity.class);
            showIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK |
                    Intent.FLAG_ACTIVITY_SINGLE_TOP |
                    Intent.FLAG_ACTIVITY_CLEAR_TOP);
            showIntent.putExtra("command","MyService2");
            Runnable run = new Runnable() {
                @Override
                public void run() {
                    for (int i = 1; i <= 10; i++) {
                        try{
                            Thread.sleep(1000);
                            showIntent.putExtra("cnt2", i * 10);
                            startActivity(showIntent);
                        }catch (Exception e){
                            e.printStackTrace();
                        }

                    }
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
