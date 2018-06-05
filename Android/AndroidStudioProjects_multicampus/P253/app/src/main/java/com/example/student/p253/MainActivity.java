package com.example.student.p253;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    LinearLayout container;
    //container에 다른 것을 붙일 때 필요.
    LayoutInflater inflater;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initUI();
    }
    public void initUI(){
        container = findViewById(R.id.container);

        // layoutInflater
        inflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }
    public void clickBT(View v){
        //container layout에 R.layout.sub를 붙임
        //return View
        inflater.inflate(R.layout.sub, container,true);

        TextView s_tv = findViewById(R.id.s_tv);
        s_tv.setText("Button click");

        Button s_bt1 = findViewById(R.id.s_bt1);
        Button s_bt2 = findViewById(R.id.s_bt2);

        s_bt1.setText("sub button1 >");
        s_bt2.setText("sub button2 >");

        /*s_bt1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //button 클릭 시 main 화면을 sub2로 변경
                inflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                inflater.inflate(R.layout.sub, container,false);
                inflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                inflater.inflate(R.layout.sub2, container,true);
            }
        });

        s_bt2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //button 클릭 시 main 화면을 sub3로 변경
                view = inflater.inflate(R.layout.sub3, container,false);
            }
        });*/
    }

}
