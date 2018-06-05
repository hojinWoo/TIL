package com.example.dnghwls7.myapplication;

import android.content.Intent;
import android.media.MediaCas;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;



public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    private Button btn_cal;
    private Button btn_img;
    private Button btn_close;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btn_cal=(Button)findViewById(R.id.btn_cal);
        btn_img=(Button)findViewById(R.id.btn_img);
        btn_close=(Button)findViewById(R.id.btn_close);

        btn_cal.setOnClickListener(this);
        btn_img.setOnClickListener(this);
        btn_close.setOnClickListener(this);

        /*Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });*/
    }

        @Override
        public void onClick(View v) {
            Intent intent;

            switch (v.getId()) {
                case R.id.btn_cal:
                    intent = new Intent(getApplicationContext(),CalculActivity.class);
                    startActivityForResult(intent,1);
                    break;
                case R.id.btn_img:
                    intent = new Intent(getApplicationContext(),ImageActivity.class);
                    startActivityForResult(intent,2);
                    break;
                case R.id.btn_close:
                    break;
            }
        }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

        @Override
        public boolean onOptionsItemSelected(MenuItem item) {
            // Handle action bar item clicks here. The action bar will
            // automatically handle clicks on the Home/Up button, so long
            // as you specify a parent activity in AndroidManifest.xml.
            int id = item.getItemId();

            //noinspection SimplifiableIfStatement
            if (id == R.id.action_settings) {
                return true;
            }

            return super.onOptionsItemSelected(item);
        }


}
