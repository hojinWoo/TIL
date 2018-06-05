package com.example.student.p82;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    Button bt1,bt2,bt3,bt4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        makebtn();

    }
    public void makebtn(){
        bt1 = findViewById(R.id.btn1);
        bt2 = findViewById(R.id.btn2);
        bt3 = findViewById(R.id.btn3);
        bt4 = findViewById(R.id.btn4);

        /* 최초 실행 시 버튼이 안 보이게 하는 것. (공백) */
        /*bt2.setVisibility(View.INVISIBLE);
        bt3.setVisibility(View.INVISIBLE);
        bt4.setVisibility(View.INVISIBLE);*/

        bt2.setEnabled(false);
        bt3.setEnabled(false);
        bt4.setEnabled(false);
    }
    public void startbt(View v){
        /*bt2.setVisibility(View.VISIBLE);
        bt3.setVisibility(View.VISIBLE);
        bt4.setVisibility(View.VISIBLE);*/

        bt2.setEnabled(true);
        bt3.setEnabled(true);
        bt4.setEnabled(true);
    }
    public void click1bt(View v){
        Intent myIntent = new Intent(getApplicationContext(), SecondActivity.class);
        startActivity(myIntent);
    }
    public void click2bt(View v){
        Intent myIntent = new Intent(getApplicationContext(), ThirdActivity.class);
        startActivity(myIntent);
    }
    public void click3bt(View v){
        Intent myIntent = new Intent(getApplicationContext(), FourthActivity.class);
        startActivity(myIntent);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Toast.makeText(this,"Destroy", Toast.LENGTH_SHORT).show();
    }
}
