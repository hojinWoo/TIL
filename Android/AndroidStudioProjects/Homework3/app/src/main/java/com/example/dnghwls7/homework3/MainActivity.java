package com.example.dnghwls7.homework3;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    TextView edResult;
    Button btnEnter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        btnEnter = (Button) findViewById(R.id.btnEnter);
        edResult = (TextView) findViewById(R.id.editResult);


        btnEnter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), SigmaActivity.class);
                startActivityForResult(intent, 1);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        //1.startActivityForResult의 두번째 인자값
        //2.각 activity의 처리결과가 RESULT_OK면 성공적이라는 의미
        //3.Activity가 별도의 결과를 보내줄 경우 이 파라미터로 받아온다.
        switch (requestCode) {
            case 1:
                if (resultCode == RESULT_OK) {
                    String result = data.getStringExtra("result");
                    edResult.setText(result);
//                    Toast.makeText(getApplicationContext(), result, Toast.LENGTH_LONG).show();
                } else if (resultCode == RESULT_CANCELED) {
                    Toast.makeText(getApplicationContext(), "뿅", Toast.LENGTH_LONG).show();
                }
        }

    }
}
