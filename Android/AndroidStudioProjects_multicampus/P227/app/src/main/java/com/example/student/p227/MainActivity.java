package com.example.student.p227;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;
import android.widget.LinearLayout;

public class MainActivity extends AppCompatActivity {

    WebView wv;
    LinearLayout ll;
    ImageView iv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        makeUI();
    }
    public void makeUI(){
        wv = findViewById(R.id.wv);
        ll = findViewById(R.id.ll);
        iv = findViewById(R.id.iv);

        //android 안에 browser (기본 브라우저는 JS가 동작 안하기 때문에)
        //JS가 동작하도록 설정
        wv.setWebViewClient(new WebViewClient());
        wv.getSettings().setJavaScriptEnabled(true);

        wv.setVisibility(View.INVISIBLE);
        ll.setVisibility(View.INVISIBLE);
        iv.setVisibility(View.INVISIBLE);
    }
    public void clickBt(View v){
        if(v.getId() == R.id.bt1){
            wv.setVisibility(View.VISIBLE);
            //androidManifests.xml >>add <uses-permission android:name="android.permission.INTERNET/">
            wv.loadUrl("https://m.naver.com");

            ll.setVisibility(View.INVISIBLE);
            iv.setVisibility(View.INVISIBLE);

        }else if(v.getId() == R.id.bt2){
            wv.setVisibility(View.INVISIBLE);
            ll.setVisibility(View.VISIBLE);
            iv.setVisibility(View.INVISIBLE);
        }else if(v.getId() == R.id.bt3){
            wv.setVisibility(View.INVISIBLE);
            ll.setVisibility(View.INVISIBLE);
            iv.setVisibility(View.VISIBLE);
        }

    }
}
