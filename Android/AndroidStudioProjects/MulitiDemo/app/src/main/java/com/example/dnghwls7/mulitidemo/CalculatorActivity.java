package com.example.dnghwls7.mulitidemo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.ButtonBarLayout;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;



/**
 * Created by dnghwls7 on 2016-08-24.
 */
public class CalculatorActivity extends AppCompatActivity {
    TextView num1;
    TextView num2;
    TextView ResultView;
    Button btnadd;
    Button btnsub;
    Button btnmul;
    Button btndiv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.calculator);

        num1 = (TextView)findViewById(R.id.editText);
        num2 = (TextView)findViewById(R.id.editText2);
        ResultView = (TextView)findViewById(R.id.ResultView);

        btnadd = (Button)findViewById(R.id.button1);
        btnsub = (Button)findViewById(R.id.button2);
        btnmul = (Button)findViewById(R.id.button3);
        btndiv = (Button)findViewById(R.id.button4);


        MyClickListener listener = new MyClickListener();
        btnadd.setOnClickListener(listener);
        btnsub.setOnClickListener(listener);
        btnmul.setOnClickListener(listener);
        btndiv.setOnClickListener(listener);

    }

    class MyClickListener implements View.OnClickListener {

        @Override
        public void onClick(View v) {
            int n1 = Integer.parseInt(num1.getText().toString());
            int n2 = Integer.parseInt(num2.getText().toString());
            int r;
            switch (v.getId()) {
                case R.id.button1:
                    r = n1 + n2;
                    ResultView.setText(Integer.toString(r));
                    break;
                case R.id.button2:
                    r = n1 - n2;
                    ResultView.setText(Integer.toString(r));
                    break;
                case R.id.button3:
                    r = n1 * n2;
                    ResultView.setText(Integer.toString(r));
                    break;
                case R.id.button4:
                    r = n1 / n2;
                    ResultView.setText(Integer.toString(r));
                    break;
            }
        }
    }
}
