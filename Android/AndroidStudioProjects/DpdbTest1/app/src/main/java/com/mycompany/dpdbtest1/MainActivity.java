package com.mycompany.dpdbtest1;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.support.v7.widget.ButtonBarLayout;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    TextView input1;
    TextView input2;
    TextView result1;
    TextView result2;

    Button btn1;
    Button btn2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        input1=(TextView)findViewById(R.id.editText1);
        input2=(TextView)findViewById(R.id.editText2);

        result1=(TextView)findViewById(R.id.ResultView1);
        result2=(TextView)findViewById(R.id.ResultView2);

        btn1=(Button)findViewById(R.id.button1);
        btn2=(Button)findViewById(R.id.button2);

        //MyClickListener listener = new MyClickListener();
        btn1.setOnClickListener(new Button.OnClickListener(){
            @Override
            public void onClick(View v) {
                int n1 = Integer.parseInt(input1.getText().toString());
                int r1;
                r1 = n1*2;
                result1.setText(Integer.toString(r1));
            }
        });
        btn2.setOnClickListener(new Button.OnClickListener(){
            @Override
            public void onClick(View v) {
                int n2 = Integer.parseInt(input2.getText().toString());
                int r2;
                r2 = n2*n2;
                result2.setText(Integer.toString(r2));
            }
        });

    }

 /*   class MyClickListener implements View.OnClickListener {

        @Override
        public void onClick(View v) {
            int n1 = Integer.parseInt(input1.getText().toString());
            int n2 = Integer.parseInt(input2.getText().toString());
            int r1;
            int r2;

            switch (v.getId()) {
                case R.id.button1:
                    r1 = n1*2;
                    result1.setText(Integer.toString(r1));
                    break;
                case R.id.button2:
                    r2 = n2*n2;
                    result2.setText(Integer.toString(r2));
                    break;
            }

        }
    }*/
}
