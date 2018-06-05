package com.example.dnghwls7.needthreaddemo;

import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    Button btnStart;
   TextView textView;
    Handler handler = new Handler();//사용법이 다양
    //주스레드와 작업스레드(사용자가 만든 스레드) 간 큐를 만들어서
    //작업 스레드에서 View를 직접 건드리지 못하도록 View를 수정해달라고
    //메인스레드에 요청하는 일을 담당하는 class

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnStart=(Button)findViewById(R.id.button);
        textView=(TextView)findViewById(R.id.textView);

        btnStart.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
//                sigma();

                //Thread 객체 필요
                MyThread thread = new MyThread();
                thread.start();
            }
        });

    }


    void sigma()
    {
        int i, sum=0;
        for (i=1;i<=100;i++) {
            sum += i;
            try {
                Thread.sleep(100);  //옆의 전구가 뜨면 surrond with try/catch 클릭하기
                                    //0.1초간 CPU를 뺏어서 작업을 멈춤, 정해진 시간마다 하는 것은 아님
                                    //단위 1초 = 1000
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            Log.i("sigma result",sum+"");
            textView.setText(sum+"");
        }}

    class MyThread extends Thread{
        int i, sum=0;

        @Override
        public void run() {

            for (i=1;i<=100;i++) {
                sum += i;
                try {
                    Thread.sleep(100);  //옆의 전구가 뜨면 surrond with try/catch 클릭하기
                    //0.1초간 CPU를 뺏어서 작업을 멈춤, 정해진 시간마다 하는 것은 아님
                    //단위 1초 = 1000
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                Log.i("sigma result",sum+"");

                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        textView.setText(sum+"");//이 안에 넣어줘야 하고 sum을 지역변수로 쓰면 안 됨
                    }
                });
//                textView.setText(sum+"");
            }}
        }
    }

