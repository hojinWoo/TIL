package com.example.student.p260;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    TextView textView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textView = findViewById(R.id.textView);

    }

    public void clickBt(View v){
        Intent intent = new Intent(getApplicationContext(), secondActivity.class);
        //Wraper class (java와 연동된다) key, value
        intent.putExtra("num1",1000);
        //startActivity(intent);

        //여기서 보냄
        startActivityForResult(intent, 100);
    }

    public void clickBt2(View v){
        Intent intent = new Intent(getApplicationContext(), ThirdActivity.class);
        //Wraper class (java와 연동된다) key, value
        intent.putExtra("num2",220);
        //startActivity(intent);

        //여기서 보냄
        startActivityForResult(intent, 101);
    }

    //alt + insert
    //여기서 결과 받음
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        //Toast.makeText(this,""+requestCode+" "+resultCode, Toast.LENGTH_SHORT).show();
        int result=0;
        //100번 requestcode가 정상적으로 응답 시
        if(requestCode == 100 && resultCode ==RESULT_OK){
            result = data.getIntExtra("result",0);

        }else if(requestCode == 101 && resultCode ==RESULT_OK){
            result = data.getIntExtra("result",0);
        }
        textView.setText(result + " ");
    }
}
