package com.example.student.p425;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class MainActivity extends AppCompatActivity {
    WebView webView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        webView = findViewById(R.id.webView);
        //app에 있는 browser default engine이용하겠다는 것 (ex. naver, 크롬, safari, firefox)
        webView.setWebViewClient(new WebViewClient());

        //webView의 properties 설정
        WebSettings webSettings = webView.getSettings();

        /*//ex. html5에서 zoom in, zoom out 설정 on/off
        webSettings.setBuiltInZoomControls();
        //font 설정
        webSettings.setDefaultFontSize(int);
        //JS 설정 on/off (on을 안해주면 JS가 동작 X)
        webSettings.setJavaScriptEnabled(true);
        */
        webSettings.setJavaScriptEnabled(true);

    }
    public void clickBt(View v){
        //permission 필요
        if(v.getId() == R.id.button){
            webView.loadUrl("http://m.naver.com");
        }else if(v.getId() == R.id.button2){
            webView.loadUrl("http://m.daum.net");
        }
    }

}
