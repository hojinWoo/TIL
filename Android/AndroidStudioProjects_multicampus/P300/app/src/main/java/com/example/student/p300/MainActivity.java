package com.example.student.p300;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.support.annotation.DrawableRes;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ResourceBundle;

public class MainActivity extends AppCompatActivity {

    TextView editText;
    TextView editText2;
    Intent intent;

    ImageView imageView;

    ProgressBar progressBar;

    ProgressDialog progressDialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        editText  = findViewById(R.id.text1);
        editText2 = findViewById(R.id.text2);
        progressBar = findViewById(R.id.progressBar);
        progressBar.setMax(100);

        imageView = findViewById(R.id.imageView);

        //loading
        progressDialog = new ProgressDialog(MainActivity.this);
        progressDialog.setCancelable(false);
    }

    @Override
    protected void onNewIntent(Intent intent) {
        processIntent(intent);

        super.onNewIntent(intent);
    }

    private void processIntent(Intent intent){

        if(intent != null){
            String command = intent.getStringExtra("command");
            int cnt = intent.getIntExtra("cnt",0);
            editText2.setText(cnt+"");
            //Toast.makeText(this,"command : "+cnt+", name :"+cnt, Toast.LENGTH_SHORT).show();
            progressBar.setProgress(
                    cnt * 10
            );

            if(cnt %2 ==0) {
                imageView.setImageDrawable(getDrawable(R.drawable.sana));
                //imageView.setImageResource(R.drawable.sana);

            }else{
                //imageView.setImageDrawable(getDrawable(R.drawable.twice1));
                imageView.setImageResource(R.drawable.twice1);
            }
        }
    }

    public void clickBt(View v){

        String name = editText.getText().toString();

        intent = new Intent(this,MyService.class);
        intent.putExtra("command","show");
        intent.putExtra("name",name);

        startService(intent);
    }

    public void clickBt2(View v){
        progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        progressDialog.setMessage("Loading...");
        progressDialog.create();
        progressDialog.show();
        progressDialog.setCancelable(true);
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        if(intent != null){
            stopService(intent);
        }
    }

    @Override
    public void onBackPressed() {
        //super가 있으면 바로 꺼짐.
        //super.onBackPressed();
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("경고!");
        builder.setMessage("끝내시겠습니까?");
        builder.setIcon(R.drawable.icon);
        builder.setNegativeButton("아니요", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                return;
            }
        });
        builder.setPositiveButton("네", new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                finish(); //onDestroy() 자동 호출
            }
        });
        builder.setNeutralButton("취소", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                return;
            }
        });

        builder.create().show();
    }
}
