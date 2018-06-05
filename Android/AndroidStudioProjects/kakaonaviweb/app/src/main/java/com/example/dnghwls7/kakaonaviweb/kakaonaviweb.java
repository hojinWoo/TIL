package com.example.dnghwls7.kakaonaviweb;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import java.net.URISyntaxException;

public class kakaonaviweb extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kakaonaviweb);
    }

    public void init(){

    }
    public void shownavi(View v){

        String url="http://70.12.114.136/ws/jsp/kakaonavi.jsp";

        WebView webView = findViewById(R.id.naviweb);
        webView.setWebViewClient(new MyWebViewClient());

        webView.getSettings().setJavaScriptEnabled(true);
        webView.getSettings().setAppCacheEnabled(false);
        webView.getSettings().setDomStorageEnabled(true);

        MyWebViewClient myWebViewClient = new MyWebViewClient();
        webView.clearCache(true);
        myWebViewClient.shouldOverrideUrlLoading(webView, url);

    }

    /*private class MyWebViewClient extends WebViewClient {
        @Override
        public boolean shuldOverrideUrlLoading(WebView view, String url) {
            view.loadUrl(url);
            return false;
        }
    }*/

    private class MyWebViewClient extends WebViewClient {
        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            if (url != null && url.startsWith("intent:")) {
                try {
                    Intent intent = Intent.parseUri(url, Intent.URI_INTENT_SCHEME);
                    Intent existPackage = getPackageManager().getLaunchIntentForPackage(intent.getPackage());
                    if (existPackage != null) {
                        startActivity(intent);
                    } else {
                        Intent marketIntent = new Intent(Intent.ACTION_VIEW);
                        marketIntent.setData(Uri.parse("market://details?id="+intent.getPackage()));
                        startActivity(marketIntent);
                    }
                    return true;
                }catch (Exception e) {
                    e.printStackTrace();
                }
            } else if (url != null && url.startsWith("market://")) {
                try {
                    Intent intent = Intent.parseUri(url, Intent.URI_INTENT_SCHEME);
                    if (intent != null) {
                        startActivity(intent);
                    }
                    return true;
                } catch (URISyntaxException e) {
                    e.printStackTrace();
                }
            }
            view.getSettings().setJavaScriptEnabled(true);
            view.getSettings().setAppCacheEnabled(false);
            view.getSettings().setDomStorageEnabled(true);
            view.loadUrl(url);
            return false;
        }
    }
}
