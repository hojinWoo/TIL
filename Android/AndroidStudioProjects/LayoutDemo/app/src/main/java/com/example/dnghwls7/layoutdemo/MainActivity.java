package com.example.dnghwls7.layoutdemo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

public class MainActivity extends AppCompatActivity {

    LinearLayout layout1;
    LinearLayout layout2;
    Button btnRed;
    Button btnBlue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_test6);

        layout1=(LinearLayout)findViewById(R.id.first_layout);
        layout2=(LinearLayout)findViewById(R.id.second_layout);

        btnRed=(Button)findViewById(R.id.btnRed);
        btnBlue=(Button)findViewById(R.id.btnBlue);

        // 지역 객체 선언하면 참조 받아서 안 지워짐
        MyClickListener listener = new MyClickListener();
        btnRed.setOnClickListener(listener);
        btnBlue.setOnClickListener(listener);
    }

    class MyClickListener implements View.OnClickListener{
        @Override
        public void onClick(View v) {
            switch (v.getId())
            {
                case R.id.btnRed:
                    layout1.setVisibility(View.VISIBLE);
                    layout2.setVisibility(View.INVISIBLE);
                    break;

                case R.id.btnBlue:
                    layout1.setVisibility(View.INVISIBLE);
                    layout2.setVisibility(View.VISIBLE);
                    break;
            }
        }
    }
}
