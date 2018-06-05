package com.example.student.p536;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    TextView textView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textView = findViewById(R.id.textView);

    }

    public void clickBt(View v){
        MyThread3.start();
    }

    /*
    * Network는 무조건 Thread로 할 것
    * Activity 내용 변경 하려면 무조건 run function 필요요
   * */

    //create Thread
    /*class MyThread1 extends Thread{
        Thread thread = new Thread();

        @Override
        public void run() {
            super.run();
        }
    }*/



   /* class MyThread2 implements Runnable{
        @Override
        public void run() {

        }
    }*/

    Thread MyThread3 = new Thread(new Runnable() {
        int i = 1;
        @Override
        public void run() {
            while(i<=10){
                try{
                    Thread.sleep(500);
                }catch (InterruptedException e){
                    e.printStackTrace();
                }
                i++;
                //thread 동작은 main thread + sub thread(created by main)
                //sub thread는 main thread의 UI 변경 X
                //textView.setText(i+" ");

                //새로 만들어야 가능해짐 (가장 쉬운 방법)
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        textView.setText(i+" ");
                    }
                });
            }
        }
    });
}
