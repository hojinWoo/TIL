package com.example.student.p553_3;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;

public class MyService extends Service {
    String cmd;
    boolean flag;
    public MyService() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        if(intent == null){
            return Service.START_STICKY;
        }else{
            new Thread(new service()).start();
        }

        return super.onStartCommand(intent, flags, startId);
    }


    class service implements Runnable{
        @Override
        public void run() {
            for (int i = 0; i < 7; i++) {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_SINGLE_TOP | Intent.FLAG_ACTIVITY_CLEAR_TOP);
                intent.putExtra("num", i);
                startActivity(intent);
            }
        }
    }

        private void processCommand(Intent intent){
            cmd = intent.getStringExtra("cmd");
            if(cmd.equals("stop")) {

            }

        }
}
