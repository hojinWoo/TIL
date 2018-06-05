package com.example.student.p540;

import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import java.io.IOException;

public class MainActivity extends AppCompatActivity {
    TextView textView;
    MyHandler myHandler, myHandler2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textView = findViewById(R.id.textView);
        myHandler = new MyHandler();
        myHandler2 = new MyHandler();

    }

    public void clickBt(View v){
        t.start();

    }

    Thread t = new Thread(new Runnable() {
        int i = 0;

        @Override
        public void run() {
            while(i<=10){
                i++;
                try{
                    Thread.sleep(500);
                }catch (InterruptedException e){
                    e.printStackTrace();
                }
                Message message = new Message();
                Bundle bundle = message.getData(); //bundle
                bundle.putInt("data", i);
                myHandler.sendMessage(message);
            }
        }
    });


    class MyHandler extends Handler{
        //기다리다가 5초 후 보낼 때 받음

        //handler는 data가 오는 경우 사용하기에 좋다
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            Message msg2 = new Message();
            Bundle bun2 = msg2.getData();

            msg2.setData(bun2);
            //bun2.putInt("data2",11);

            myHandler2.sendMessage(msg2);
        }

    }
    class MyHandler2 extends Handler{
        //기다리다가 5초 후 보낼 때 받음

        //handler는 data가 오는 경우 사용하기에 좋다
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            Bundle bun3 = msg.getData();
            int res = bun3.getInt("data1");
            int result = bun3.getInt("data2");
            if(result == 11){
                textView.setText("finish");
            }else {
                textView.setText(res+"");
            }

        }
    }
}
