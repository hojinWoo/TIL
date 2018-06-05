package com.example.student.p293;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import java.util.Date;

import static android.net.http.SslCertificate.restoreState;

public class MainActivity extends AppCompatActivity {

    Date date = new Date();
    SharedPreferences pref;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        pref = getSharedPreferences("pref", Activity.MODE_PRIVATE);
    }

    @Override
    protected void onStart() {
        super.onStart();
        Toast.makeText(this,"onStart",Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onResume() {
        super.onResume();
        Toast.makeText(this,"onResume",Toast.LENGTH_SHORT).show();
        restoreState();

    }

    @Override
    protected void onPause() {
        super.onPause();
        Toast.makeText(this,"onPause",Toast.LENGTH_SHORT).show();
        saveState();
    }


    @Override
    protected void onRestart() {
        super.onRestart();
        Toast.makeText(this,"onRestart",Toast.LENGTH_SHORT).show();

    }
    @Override
    protected void onStop() {
        super.onStop();
        Toast.makeText(this,"onStop",Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Toast.makeText(this,"onDestroy",Toast.LENGTH_SHORT).show();
    }
    public void restoreState(){
        if(pref != null){
            if(pref.contains("cnt")){
                //String rdate = pref.getString("date","");
                int rcnt = pref.getInt("cnt",0);
                Toast.makeText(this,rcnt+"", Toast.LENGTH_SHORT).show();
            }
        }
    }
    public void saveState(){
        SharedPreferences.Editor editor = pref.edit();

        if(pref != null){
            if(pref.contains("cnt") ){
                int rcnt = pref.getInt("cnt",0);
                editor.putInt("cnt", ++rcnt);
                editor.commit();
            }else {
                int cnt = 0;
                editor.putInt("cnt", ++cnt);
                editor.commit();
            }
        }
        editor.commit();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        //현재 this(main activity) 위에 dialog 띄우기
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Sad day tonight");
        builder.setMessage("I'm so disappointed because of barca");
        builder.setNegativeButton("NO", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

            }
        });
        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                SharedPreferences.Editor editor = pref.edit();
                editor.clear();
                editor.commit();
                finish();
            }
        });
        builder.show();
    }
}
