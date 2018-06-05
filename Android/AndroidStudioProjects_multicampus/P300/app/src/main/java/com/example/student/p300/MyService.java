package com.example.student.p300;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;

import static android.content.ContentValues.TAG;

public class MyService extends Service {

    private static final String TAG = "MyService";

    public MyService() {
    }

    @Override
    public void onCreate() {
        super.onCreate();

        Log.d(TAG, "onCreate() 호출");
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.d(TAG, "onStartCommand() 호출");
        if(intent == null){
            return Service.START_STICKY;
        }else {
            processCommand(intent);
        }

        return super.onStartCommand(intent, flags, startId);
    }

    private void processCommand(Intent intent){
        String command = intent.getStringExtra("command");
        String name = intent.getStringExtra("name");

        Log.d(TAG, "Command : "+ command + ", name : "+name );

        final Intent showIntent = new Intent(getApplicationContext(), MainActivity.class);
        // flag를 통해 새롭게 만들지 않고 기존 intent를 이용해서 사용.
        showIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK |
                Intent.FLAG_ACTIVITY_SINGLE_TOP |
                Intent.FLAG_ACTIVITY_CLEAR_TOP);

        Runnable run = new Runnable() {
            @Override
            public void run() {
                for (int i=1;i<=10;i++){
                    Log.d(TAG, "Process: "+i);

                    showIntent.putExtra("command","cmd");
                    showIntent.putExtra("cnt", i);
                    startActivity(showIntent);

                    try{
                        Thread.sleep(1000);
                    }catch (InterruptedException e){
                        e.printStackTrace();
                    }
                }
            }
        };
       new Thread(run).start();
        /*for(int i = 0;i < 10;i++){

            try{
                Thread.sleep(1000);
            }catch (Exception e){

            };
            Log.d(TAG, "Waiting "+ i + " seconds.");
        }*/



    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }
}
