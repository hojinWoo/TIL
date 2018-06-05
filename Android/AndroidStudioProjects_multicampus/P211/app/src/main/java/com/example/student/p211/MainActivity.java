package com.example.student.p211;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    EditText tx_id, tx_pwd;
    Button bt_login;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        makeUi();

    }

    public void makeUi(){
        tx_id = findViewById(R.id.tx_id);
        tx_pwd = findViewById(R.id.editText2);
        bt_login = findViewById(R.id.btn);
    }
    public void clickLogin(View v){
        // get value from editText
        String id = tx_id.getText().toString();
        String pwd = tx_pwd.getText().toString();
        tx_id.setText("");
        tx_pwd.setText("");
        Toast.makeText(this,id+ " "+ pwd, Toast.LENGTH_SHORT).show();
        Intent myIntent = null;
        if(id.equals("qq") && pwd.equals("11")){
            /*intent : like a map structure*/

            myIntent = new Intent(getApplicationContext(), LoginSuccess.class);
            myIntent.putExtra("loginid",id);
        }else{
            myIntent = new Intent(getApplicationContext(), LoginFail.class);

        }
        startActivity(myIntent);
    }

}
