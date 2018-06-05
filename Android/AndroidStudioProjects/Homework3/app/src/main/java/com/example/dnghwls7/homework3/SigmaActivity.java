package com.example.dnghwls7.homework3;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

/**
 * Created by dnghwls7 on 2016-08-24.
 */
public class SigmaActivity extends AppCompatActivity {

    EditText edNum;
    EditText edHide;
    Button btnConfirm;

    void init() {
        edNum = (EditText) findViewById(R.id.editNum);
        btnConfirm = (Button) findViewById(R.id.btnConfirm);

        EventListener listener = new EventListener();
        btnConfirm.setOnClickListener(listener);


    }

    int Sum(int a) {
        if (a == 1) return 1;
        else return a+Sum(a - 1);
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sigma);
        init();

    }

    class EventListener implements View.OnClickListener {
        @Override
        public void onClick(View view) {
            Intent intent = new Intent();
            int x = Integer.parseInt(edNum.getText().toString());
            int result = 0;


            result = Sum(x);

            String s = String.format("Input your num=%d\nSigma Sum = %d",x, result);
            intent.putExtra("result", s);

            switch (view.getId()) {
                case R.id.btnConfirm:
                    setResult(RESULT_OK, intent);
                    finish();
                    break;
            }

        }
    }
}
