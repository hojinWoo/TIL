package com.example.student.workshop;


import android.app.ProgressDialog;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.ProgressBar;

public class MainActivity extends AppCompatActivity {

    SubFragement sub;
    FrameLayout container;
    Intent intent1;
    Intent intent2;
    Intent intent3;
    ImageView imageView;
    ProgressBar progressBar;
    FragmentManager manager;

    ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        container = findViewById(R.id.container);
        sub = new SubFragement();
        manager = getSupportFragmentManager();

        progressDialog = new ProgressDialog(MainActivity.this);
        progressDialog.setCancelable(false);
    }

    @Override
    protected void onNewIntent(Intent intent) {
        processIntent(intent);

        super.onNewIntent(intent);
    }

    public void processIntent(Intent intent){
        if(intent != null) {
            if (intent.getStringExtra("command").equals("MyService1")) {
                if(intent.getIntExtra("time",0)==5){
                    progressDialog.dismiss();
                }
                manager.beginTransaction().replace(R.id.container, sub).commit();
            }
            if (intent.getStringExtra("command").equals("MyService2")) {
                progressBar = findViewById(R.id.progressBar);
                progressBar.setMax(100);
                progressBar.setProgress(intent.getIntExtra("cnt2", 0));

            }
            if (intent.getStringExtra("command").equals("MyService3")) {
                imageView = findViewById(R.id.imageView);
                int cnt = intent.getIntExtra("cnt3", 0);
                if (cnt == 1) {
                    imageView.setImageResource(R.drawable.sana);

                } else {
                    imageView.setImageResource(R.drawable.twice1);
                }
            }
        }

    }

    public void clickBt(View v){
        if(v.getId() == R.id.btn_start1){
            progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
            progressDialog.setMessage("Loading...");
            progressDialog.create();
            progressDialog.show();

            intent1 = new Intent(this,MyService1.class);
            intent1.putExtra("command","Myservice1");
            //intent1.putExtra("open","show");
            startService(intent1);
        }else if(v.getId() == R.id.btn_start2){
            intent2 = new Intent(this,MyService2.class);
            intent2.putExtra("command","Myservice2");

            intent3 = new Intent(this,MyService3.class);
            intent3.putExtra("command","Myservice3");
            startService(intent2);
            startService(intent3);
        }

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if(intent1 != null){
            stopService(intent1);
        }if(intent2 != null){
            stopService(intent2);
        }if(intent3 != null){
            stopService(intent3);
        }
    }


    public static class SubFragement extends Fragment{
        @Nullable
        @Override
        public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
            View v =inflater.inflate(R.layout.sublayout, container, false);
            return v;
        }
    }
}
