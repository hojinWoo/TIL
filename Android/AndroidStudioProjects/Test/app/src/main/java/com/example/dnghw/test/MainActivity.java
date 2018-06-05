package com.example.dnghw.test;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    // alt+enter를 해야만 import가 됨
    Button btnRed;
    Button btnBlue;
    TextView myText; //textview 가져오기

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_main); //무조건 setContentView 이후에 작업할 것
            //XML 파일 작업해주는 것-객체 만들어줌

            btnRed=(Button)findViewById(R.id.btnRed);
            btnBlue=(Button)findViewById(R.id.btnBlue);
            myText=(TextView)findViewById(R.id.mytext);

            //ClickListener 객체 붙여준다
            //alt+insert 하면 override가 가능해짐
            btnRed.setOnClickListener(new View.OnClickListener(){
            @Override
                public void onClick(View v) {
                    myText.setTextColor(Color.RED);
                }//객체가 한 번 만들어지고 버림.
            });//익명의 eventlistener 사용한 것임

            btnBlue.setOnClickListener(new View.OnClickListener(){

                @Override
                public void onClick(View v) {
                    myText.setTextColor(Color.BLUE);
                }
            });
    }
}
//개인 activity가