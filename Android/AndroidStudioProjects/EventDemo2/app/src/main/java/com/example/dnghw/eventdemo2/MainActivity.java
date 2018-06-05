package com.example.dnghw.eventdemo2;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

//interface는 반드시 함수를 overriding해줘야 하므로 처음에 상속 받으면 오류 나옴
public class MainActivity extends AppCompatActivity
    implements View.OnClickListener{//androdiview로 받고 alt+insert로 override하기{


    Button btnRed;
    Button btnBlue;
    TextView mytext;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnRed=(Button)findViewById(R.id.btnRed);
        btnBlue=(Button)findViewById(R.id.btnBlue);
        mytext=(TextView)findViewById(R.id.mytext);

        btnRed.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                mytext.setTextColor(Color.RED);
            }
        });

        btnBlue.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                mytext.setTextColor(Color.BLUE);
            }
        });

        btnRed.setOnClickListener(this);
        btnBlue.setOnClickListener(this);
    };

    @Override
    public void onClick(View v) {
        switch (v.getId())
        {
            case R.id.btnRed:
                mytext.setTextColor(Color.RED);
                break;

            case R.id.btnBlue:
                mytext.setTextColor(Color.BLUE);
                break;
        }
    }
}
