package com.example.student.p230;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Date;

public class MainActivity extends AppCompatActivity {
    TextView date;
    SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
    String time = sdf.format(new Date(System.currentTimeMillis()));

    WebView wv;
    LinearLayout ll,ll1,ll2,ll3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        makeUI();
        date = (TextView)findViewById(R.id.date);
        date.setText(time);

    }

    public void makeUI(){
        wv = findViewById(R.id.wv);
        ll = findViewById(R.id.ll);
        ll1 = findViewById(R.id.ll1);
        ll2 = findViewById(R.id.ll2);
        ll3 = findViewById(R.id.ll3);

        wv.setVisibility(View.INVISIBLE);
        ll.setVisibility(View.VISIBLE);
        ll1.setVisibility(View.INVISIBLE);
        ll2.setVisibility(View.INVISIBLE);
        ll3.setVisibility(View.INVISIBLE);
    }
    public void btclick(View v){
        if(v.getId() == R.id.home){
            wv.setVisibility(View.INVISIBLE);
            ll.setVisibility(View.VISIBLE);
            ll1.setVisibility(View.INVISIBLE);
            ll2.setVisibility(View.INVISIBLE);
            ll3.setVisibility(View.INVISIBLE);
        }else if(v.getId() == R.id.signup){
            wv.setVisibility(View.INVISIBLE);
            ll.setVisibility(View.INVISIBLE);
            ll1.setVisibility(View.VISIBLE);
            ll2.setVisibility(View.INVISIBLE);
            ll3.setVisibility(View.INVISIBLE);
        }else if(v.getId() == R.id.login){
            wv.setVisibility(View.INVISIBLE);
            ll.setVisibility(View.INVISIBLE);
            ll1.setVisibility(View.INVISIBLE);
            ll2.setVisibility(View.VISIBLE);
            ll3.setVisibility(View.INVISIBLE);
        }else if(v.getId() == R.id.analyze){
            wv.setVisibility(View.INVISIBLE);
            ll.setVisibility(View.INVISIBLE);
            ll1.setVisibility(View.INVISIBLE);
            ll2.setVisibility(View.INVISIBLE);
            ll3.setVisibility(View.VISIBLE);
        }else if(v.getId() == R.id.naver){
            wv.setVisibility(View.VISIBLE);
            wv.loadUrl("https://m.naver.com");
            ll.setVisibility(View.INVISIBLE);
            ll1.setVisibility(View.INVISIBLE);
            ll2.setVisibility(View.INVISIBLE);
            ll3.setVisibility(View.INVISIBLE);
        }
    }
}
