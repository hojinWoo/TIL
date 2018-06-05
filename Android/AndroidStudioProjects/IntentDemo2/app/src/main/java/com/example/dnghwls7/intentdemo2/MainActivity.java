package com.example.dnghwls7.intentdemo2;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

    public class MainActivity extends AppCompatActivity {

    Button btnFirst;
    Button btnSecond;
    Button btnThird;
    TextView tvResult;

    final int First = 1; //final : 수정 안 되는 것
    final int Second = 2;
    final int Third = 3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnFirst=(Button)findViewById(R.id.btnFirst);
        btnSecond=(Button)findViewById(R.id.btnSecond);
        btnThird=(Button)findViewById(R.id.btnThird);
        tvResult=(TextView)findViewById(R.id.result);

        EventListener listener = new EventListener();
        btnFirst.setOnClickListener(listener);
        btnSecond.setOnClickListener(listener);
        btnThird.setOnClickListener(listener);

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        if (resultCode==RESULT_OK){
            String name = data.getStringExtra("name");
            String address = data.getStringExtra("address");
            String email = data.getStringExtra("email");
            String resul="";
            if(name==null) name="";
            if(address==null) name="";
            if(email==null) name="";
            resul = name + "" +email + "" + address;
            tvResult.setText(resul);
        }
        switch (requestCode)
        {
            case First:
                Log.i("first", "first 결과받음 @@@@@@@@@@@@@@@@@@@@@");
                break;
            case Second:
                Log.i("second", "second 결과받음 ######################");
                break;
            case Third:
                Log.i("third", "third 결과받음 $$$$$$$$$$$$$$$$$$$$$");
                break;
        }
    }

    class EventListener implements View.OnClickListener{

        @Override
        public void onClick(View view) {

            //String s = ((Button).view).getText().toString
            //switch 안 쓰고 button으로 할 때 방법

            Intent intent;
            switch (view.getId())
            {
                case R.id.btnFirst:
                    intent= new Intent(getApplicationContext(),FirstActivity.class);
                    startActivityForResult(intent,First);
                    break;
                case R.id.btnSecond:
                    intent= new Intent(getApplicationContext(),SecondActivity.class);
                    startActivityForResult(intent,Second);
                    break;
                case R.id.btnThird:
                    intent= new Intent(getApplicationContext(),ThirdActivity.class);
                    startActivityForResult(intent,Third);
                    break;
            }

        }
    }
}
