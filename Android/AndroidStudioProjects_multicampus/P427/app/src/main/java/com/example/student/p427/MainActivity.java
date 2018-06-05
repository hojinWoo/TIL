package com.example.student.p427;

import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    TextView textView;
    WebView webView;
    Handler handler;
    //hybrid
    //communicate android <-> web
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textView = findViewById(R.id.textView);
        webView = findViewById(R.id.webView);
        webView.setWebViewClient(new WebViewClient());
        //JS와 연동 (interface를 js와 하기, html5에서 js를 호출하면 android 내부 js를 호출)
        webView.addJavascriptInterface(new JS(), "js");

        WebSettings webSettings = webView.getSettings();
        webSettings.setJavaScriptEnabled(true);

        handler = new Handler();
    }

    public void clickBt(View v){
        webView.loadUrl("http://70.12.114.133/ad/sample.html");
    }

    //app에서 html에 있는 js 호출
    public void clickBt2(View v){
        //thread에서 동작하도록 설정
        //webView는 새로운 thread로 하면 안되기 때문에 handler 사용
        handler.post(new Runnable() {
            @Override
            public void run() {
                webView.loadUrl("javascript:changeImg()");
            }
        });
    }

    final class JS{
        JS(){}
        //web control android
        @android.webkit.JavascriptInterface
        public void clickJS(String str){
            textView.setText(str);
            Log.d("[JS click]","Event process~~");
            Toast.makeText(getApplicationContext(),"Hello",Toast.LENGTH_SHORT).show();

        }
    }


}
