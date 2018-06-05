package com.example.student.p211;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class LoginSuccess extends AppCompatActivity {
    TextView ok_id;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_success);
        ok_id = findViewById(R.id.ok_id);
        Intent intent = getIntent();
        String id = intent.getStringExtra("loginid");
        ok_id.setText(id);
    }
    public void onclickBack(View v){
        Intent myIntent = new Intent(getApplicationContext(), MainActivity.class);
        startActivity(myIntent);
    }
}
