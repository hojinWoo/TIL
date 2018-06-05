package com.example.student.p246;

import android.content.res.Resources;
import android.graphics.drawable.BitmapDrawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {

    Button btn_up, btn_down;
    ImageView upimg, downimg;
    Resources res;
    BitmapDrawable bitmap = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initUI();
    }
    public void initUI(){
        res = getResources();
        upimg = findViewById(R.id.iv1);
        downimg = findViewById(R.id.iv2);
        bitmap = (BitmapDrawable) res.getDrawable(R.drawable.sana);;
    }
    public void upclick(View v){
        upimg.setImageDrawable(bitmap);
        downimg.setImageDrawable(null);
    }
    public void downclick(View v){
        upimg.setImageDrawable(null);
        downimg.setImageDrawable(bitmap);
    }

}
