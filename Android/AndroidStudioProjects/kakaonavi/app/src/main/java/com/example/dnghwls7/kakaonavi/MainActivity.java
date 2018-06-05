package com.example.dnghwls7.kakaonavi;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.dnghwls7.kakaonavi.options.CoordType;
import com.example.dnghwls7.kakaonavi.options.RpOption;
import com.example.dnghwls7.kakaonavi.options.VehicleType;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private int position = 2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        /*Location destination = Location.newBuilder("카카오 판교 오피스", 127.10821222694533, 37.40205604363057).build();

        NaviOptions options = NaviOptions.newBuilder().setCoordType(CoordType.WGS84).setVehicleType(VehicleType.FIRST).setRpOption(RpOption.SHORTEST).build();

        //목적지 공유
        // 경유지를 포함하지 않는 KakaoNaviParams.Builder 객체
        KakaoNaviParams.Builder builder = KakaoNaviParams.newBuilder(destination).setNaviOptions(options);

        // 경유지를 1개 포함하는 KakaoNaviParams.Builder 객체
        List<Location> viaList = new ArrayList<Location>();
        viaList.add(Location.newBuilder("서서울호수공원", 126.8322289016308, 37.528495607451205).build());
        KakaoNaviParams.Builder builder = KakaoNaviParams.newBuilder(destination).setNaviOptions(options).setViaList(viaList);

        KakaoNaviParams params = builder.build();


        // 경유지 설정
        List<Location> viaList = new ArrayList<Location>();
        viaList.add(Location.newBuilder("서서울호수공원", 126.8322289016308, 37.528495607451205).build());

        final KakaoNaviParams.Builder builder = KakaoNaviParams.newBuilder(Location.newBuilder("카카오 판교오피스", 127.10821222694533, 37.40205604363057).build()).setNaviOptions(NaviOptions.newBuilder().setCoordType(CoordType.WGS84).setStartX(126.5).setStartY(35.2).build()).setViaList(viaList);

        KakaoNaviService.getInstance().navigate(MainActivity.this, builder.build());*/
    }

    public void naviclick(View v){
        onNaviButtonClicked();
    }


    private void onNaviButtonClicked() {
        if (position == -1) {
            Toast.makeText(getApplicationContext(), "실행하고 싶은 목적지 공유 / 길 안내를 선택하세요.", Toast.LENGTH_LONG).show();
            return;
        }
        Location kakao = Location.newBuilder("카카오 판교 오피스", 321256, 533732).build();
        KakaoNaviParams params;
        switch (position) {
            case 1:
                params = KakaoNaviParams.newBuilder(kakao).build();
                KakaoNaviService.getInstance().shareDestination(this, params);
                break;
            case 2:
                kakao = Destination.newBuilder("카카오 판교 오피스", 127.10821222694533, 37.40205604363057).build();
                params = KakaoNaviParams.newBuilder(kakao).setNaviOptions(NaviOptions.newBuilder().setCoordType(CoordType.WGS84).build()).build();
                KakaoNaviService.getInstance().shareDestination(this, params);
                break;
            case 4:
                kakao = Location.newBuilder("카카오 판교 오피스", 321256, 533732).build();
                params = KakaoNaviParams.newBuilder(kakao).setNaviOptions(new NaviOptions.Builder().build()).build();
                KakaoNaviService.getInstance().navigate(this, params);
                break;
            case 5:
                kakao = Destination.newBuilder("카카오 판교 오피스", 127.10821222694533, 37.40205604363057).build();
                Location stop = Location.newBuilder("서서울호수공원", 126.8322289016308, 37.528495607451205).build();
                List<Location> stops = new LinkedList<Location>();
                stops.add(stop);
                params = KakaoNaviParams.newBuilder(kakao).setNaviOptions(NaviOptions.newBuilder().setCoordType(CoordType.WGS84).build()).setViaList(stops).build();
                KakaoNaviService.getInstance().navigate(this, params);
                break;
            case 6:
                params = KakaoNaviParams.newBuilder(kakao).setNaviOptions(NaviOptions.newBuilder().setRouteInfo(true).build()).build();
                KakaoNaviService.getInstance().navigate(this, params);
                break;
            default:
                break;
        }
    }
}
