package com.mycompany.dpdb;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import java.util.EventListener;

public class MainActivity extends AppCompatActivity {

    Button button_converter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        button_converter=(Button)findViewById(R.id.btn1);

        EventListener listener = new EventListener();
        button_converter.setOnClickListener(listener);
    }

    class EventListener implements View.OnClickListener{
        @Override
        public void onClick(View view) {

            Intent intent;

            switch (view.getId()){
                case R.id.btn1:
                    intent = new Intent(getApplicationContext(),FirstActivity.class);
                    startActivityForResult(intent,First);
                    break;
            }

        }
    }
}
