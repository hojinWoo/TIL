package com.example.student.forserver;
/*
handler : thread에서 외부의 상태를 알려준다
(multiprocessing, network 통신 작업 시 필요)
*/


import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {
    EditText text_speed;
    EditText text_temp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        init();
    }

    public void init(){
        text_speed = findViewById(R.id.text_speed);
        text_temp = findViewById(R.id.text_temp);

    }
}
