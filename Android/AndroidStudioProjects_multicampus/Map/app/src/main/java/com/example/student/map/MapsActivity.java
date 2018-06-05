package com.example.student.map;

import android.Manifest;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.ArrayList;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    ImageView imageView;
    WebView webView;
    FrameLayout map;
    LinearLayout linearLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
        map = findViewById(R.id.map);
        imageView = findViewById(R.id.imageView);
        webView = findViewById(R.id.webView);
        linearLayout = findViewById(R.id.ll1);

        map.setVisibility(View.VISIBLE);
        imageView.setVisibility(View.INVISIBLE);
        webView.setVisibility(View.INVISIBLE);
        linearLayout.setVisibility(View.VISIBLE);
    }
    public void clickMap(View v){
        map.setVisibility(View.VISIBLE);
        imageView.setVisibility(View.INVISIBLE);
        webView.setVisibility(View.INVISIBLE);
        linearLayout.setVisibility(View.VISIBLE);
    }
    public void clickImage(View v){
        map.setVisibility(View.INVISIBLE);
        imageView.setVisibility(View.VISIBLE);
        webView.setVisibility(View.INVISIBLE);
        linearLayout.setVisibility(View.INVISIBLE);
    }
    public void clickChart(View v){
        map.setVisibility(View.INVISIBLE);
        imageView.setVisibility(View.INVISIBLE);
        webView.setVisibility(View.VISIBLE);
        linearLayout.setVisibility(View.INVISIBLE);
    }


    public void clickBread(View v){
        mMap.clear();
        ArrayList<LatLng> b = new ArrayList<>();
        b.add(new LatLng(37.4990997, 127.03738229999999));
        b.add(new LatLng(37.4993774, 127.03429929999993));
        b.add(new LatLng(37.503155, 127.03738229999999));
        b.add(new LatLng(37.4999269, 127.03655260000005));
        b.add(new LatLng(37.5020148, 127.03381939999997));
        b.add(new LatLng(37.4919131, 127.0342311131011));

        for(LatLng location : b){
            mMap.addMarker(new MarkerOptions().position(location));
        }
    }
    public void clickDrink(View v){
        mMap.clear();
        ArrayList<LatLng> d = new ArrayList<>();
        d.add(new LatLng(37.502455, 127.0338921));
        d.add(new LatLng(37.502055, 127.0364821));
        d.add(new LatLng(37.501355, 127.03456821));
        d.add(new LatLng(37.501955, 127.0356321));
        d.add(new LatLng(37.4999777, 127.0343131));
        d.add(new LatLng(37.499014, 127.03263132));

        for(LatLng location : d){
            mMap.addMarker(new MarkerOptions().position(location));
        }
    }
    public void clickCoffee(View v){
        mMap.clear();
        ArrayList<LatLng> c = new ArrayList<>();
        c.add(new LatLng(37.499465, 127.039694));
        c.add(new LatLng(37.502545, 127.039583));
        c.add(new LatLng(37.502083, 127.034781));
        c.add(new LatLng(37.499057, 127.034828));
        c.add(new LatLng(37.499312, 127.035879));
        c.add(new LatLng(37.498989, 127.03712));
        for(LatLng location : c){
            mMap.addMarker(new MarkerOptions().position(location));
        }
    }


    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        // Add a marker in Sydney and move the camera
        LatLng m1 = new LatLng(37.500744, 127.036864);
        //mMap.addMarker(new MarkerOptions().position(m1));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(m1));
        mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(m1, 15));
    }
}
