package com.example.student.p553;

import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {
    ImageView imageView, imageView2;

    MyHandler myHandler;

    //int image;
    //image array
    int[] img = {R.drawable.sana, R.drawable.twice1,R.drawable.img2,R.drawable.img3, R.drawable.bg4,R.drawable.bg5,R.drawable.bg6,R.drawable.bg7};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        imageView = findViewById(R.id.imageView);
        imageView2 = findViewById(R.id.imageView2);


        //arguement로 value를 주는 것도 가능
        myHandler = new MyHandler();
    }

    //동시에 Thread와 handler 실행시키기기

    public void clickBt(View v){
       t.start();
       t2.start();
       //button 두 번 클릭 못 하게 막아야 함


    }

    //받기만 하는 thread (response) //used by thread
    Thread t = new Thread(new Runnable() {
        @Override
        public void run() {
            for(int i = 0; i<8;i++){
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
            while (t.isInterrupted()){
                t.interrupt();
            }
        }
    });

    //보내기만 하는 thread (request), used by handler (handler class가 필요)
    Thread t2 = new Thread(new Runnable() {
        @Override
        public void run() {
            for(int i = 0; i<8;i++){
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
            while (t2.isInterrupted()){
                t2.interrupt();
            }
        }

    });

    //자바처럼 객체 생성을 하는 것이 아닌 message를 기다림
    class MyHandler extends Handler{
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
