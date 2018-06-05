package com.example.dnghwls7.mulitidemo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;


public class MainActivity extends AppCompatActivity {

    EditText edName;
    EditText edPhone;
    EditText edAge;
    Button btnEnd;
    Button btnCal;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        edName = (EditText) findViewById(R.id.editName);
        edPhone = (EditText) findViewById(R.id.editPhone);
        edAge = (EditText) findViewById(R.id.editAge);
        btnEnd = (Button) findViewById(R.id.buttonStart);
        btnCal = (Button) findViewById(R.id.btnCal);

        //class 정의하면서 객체 생성
        btnEnd.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {

                //이것이 onClick 전에 생성되면 데이터 입력과 상관없이 실행되게 때문에
                //여기서 무조건 선언해야 함!!!!!!!!!!!!!!!!!! 버튼이 눌렸을 때 그제서 받아들여지는 것이기 때문****
                //만일 잘못해서 debugging 하려면
                String name = edName.getText().toString();
                int age = Integer.parseInt(edAge.getText().toString());
                String phone = edPhone.getText().toString();

                Intent intent = new Intent(getApplicationContext(), ResultActivity.class);

                intent.putExtra("name", name);
                intent.putExtra("age", age);
                intent.putExtra("phone", phone);

                startActivity(intent);
            }


        });

        btnCal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), CalculatorActivity.class);
                startActivity(intent);

            } //intent 뒤에 숫자 부여 가능 (숫자가 request code임)

              //onActivityResult 함수 --> 다른 activity를 했다가 결과값을 반환 받는 경우
            //
        });
    }
}
