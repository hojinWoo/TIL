package com.example.student.p553_2;

import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {
    ImageView imageView, imageView2;

    MyHandler myHandler;

    int[] img = {R.drawable.sana ,R.drawable.img2,R.drawable.img3, R.drawable.bg4,R.drawable.bg5,R.drawable.bg6,R.drawable.bg7};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        imageView = findViewById(R.id.imageView);
        imageView2 = findViewById(R.id.imageView2);

        myHandler = new MyHandler();
    }

    //Service 사용
    public void clickBt(View v){
        Thread t1 = new Thread(r1);
        Thread t2 = new Thread(r2);
        t1.start();
        t2.start();
    }

    //Thread는 한번만 사용되기 때문에 여러번 사용하기 위해서 Runnable 이 필요
    Runnable r1 = new Runnable() {
        @Override
        public void run() {
            for(int i = 0; i<7;i++){
                try{
                    Thread.sleep(500);
                }catch (InterruptedException e){
                    e.printStackTrace();
                }
                final int image = i;
                //Image switch
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        //local variable 로 처리하거나, final variable 사용
                        imageView.setImageResource(img[image]);
                    }
                });
            }
        }
    };

    Runnable r2 = new Runnable() {
        @Override
        public void run() {
            for(int i = 0; i<7;i++){
                try{
                    Thread.sleep(500);
                }catch (InterruptedException e){
                    e.printStackTrace();
                }
                //Image switch
                Message message = new Message();
                Bundle bundle = message.getData();
                bundle.putInt("img", i);
                myHandler.sendMessage(message);
            }
        }
    };

    //자바처럼 객체 생성을 하는 것이 아닌 message를 기다림
    class MyHandler extends Handler {
        //override
        @Override
        public void handleMessage(Message msg) {
            //super.handleMessage(msg);
            Bundle bun1 = msg.getData();
            int img_num = bun1.getInt("img");
            imageView2.setImageResource(img[img_num]);
        }
    }
}
