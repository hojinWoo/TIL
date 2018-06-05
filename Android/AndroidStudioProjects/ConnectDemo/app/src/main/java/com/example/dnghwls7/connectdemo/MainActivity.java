package com.example.dnghwls7.connectdemo;

import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    TextView tvResult;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tvResult = (TextView)findViewById(R.id.tvResult);

        ConnectivityManager mgr = (ConnectivityManager)getSystemService(CONNECTIVITY_SERVICE);
        /* 시스템 자원들은 모든 앱이 공유를 해야 하기 대문에 객체도 안드로이드가 관리
        getSystemService(서비스명) : 해당 객체를 리턴한다
        */

        NetworkInfo info[] = mgr.getAllNetworkInfo();
        /*함수에 취소선 나오는 경우 = 이전 버전 일때,
        새로운 버전에서는 다른 함수를 써야 한다는 의미지만
        작동은 되므로 그냥 무시하고 써도 되나 가급적 새로운 버전찾기
        */

        String msg="";
        for(int i=0;i<info.length;i++){
            msg = msg +info[i].toString()+"\n\n";
        }
        tvResult.setText(msg);
    }
}
