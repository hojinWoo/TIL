package com.example.student.p260;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class ThirdActivity extends AppCompatActivity {
    TextView textView3;
    int num2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_third);
        textView3 = findViewById(R.id.textView3);

        Intent intent = getIntent();
        num2 = intent.getIntExtra("num2",0);
        //int만은 setText에 안 나옴. (string이 필요)
        int result = num2*2000;
        Intent intent2 = new Intent();
        intent2.putExtra("result",result);
        setResult(RESULT_OK,intent2);
        //textView3.setText(num2+" ");

        finish();
    }
    public void clickBt(View v){

        finish();
    }
}
