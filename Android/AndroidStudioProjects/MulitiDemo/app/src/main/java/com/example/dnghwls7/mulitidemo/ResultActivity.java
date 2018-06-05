package com.example.dnghwls7.mulitidemo;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by dnghwls7 on 2016-08-24.
 */
public class ResultActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.result_layout);

        Intent intent = this.getIntent();//값 가지고 오는 것
        String name = intent.getStringExtra("name");
        int age = intent.getIntExtra("age",10);//10진수로 받아라
        String phone = intent.getStringExtra("phone");

        String result = String.format("%s님의 나이는 %s이고 전화번호는 %s입니다.",name,age,phone);

        TextView tvResult= (TextView)findViewById(R.id.tvResult);
        tvResult.setText(result);

        //종료 버튼 처리하기

        Button btnEnd = (Button)findViewById(R.id.btnEnd);
        btnEnd.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), "이 창을 닫습니다",
                        Toast.LENGTH_SHORT).show();
                finish(); //창을 닫는다 = 해당 activity 종료
                //cf) 자바처럼 System.exit() 사용 못함
            }
        });

    }
}
