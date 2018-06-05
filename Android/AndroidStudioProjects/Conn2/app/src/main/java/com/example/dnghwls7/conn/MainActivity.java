package com.example.dnghwls7.conn;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    EditText editId, editpwd;
    CheckBox autologin;
    SharedPreferences pref;
    SharedPreferences.Editor editor;
    Button btn_login;

    Boolean loginchecked = true;

    Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        init();


        //if autologin checked, get input
        if(pref.getBoolean("autoLogin",true)){
            editId.setText(pref.getString("id",""));
            editpwd.setText(pref.getString("pwd",""));
            autologin.setChecked(true);
            Toast.makeText(MainActivity.this,"자동로그인",Toast.LENGTH_SHORT).show();
            intent = new Intent(MainActivity.this,CarMainActivity.class);
            startActivity(intent);
            finish();
        }
    }

    public void init(){
        editId = findViewById(R.id.edit_login);
        editpwd = findViewById(R.id.edit_password);
        btn_login = findViewById(R.id.btn_login);
        autologin = findViewById(R.id.autologin);


        //MODE_WORLD_READABLE : 읽기 공유
        //MODE_WORLD_WRITEABLE : 쓰기 공유
        //0 : 읽기, 쓰기 가능
        pref = this.getSharedPreferences("pref",0);
        editor = pref.edit();

        //init key setting
        editor.putString("id","12");
        editor.putString("pwd","11");
        editor.putBoolean("autoLoglin",false);
        editor.commit();
        autologin.setChecked(false);

        //set Checkbox
        autologin.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    Toast.makeText(MainActivity.this,"자동로그인 클릭 됨",Toast.LENGTH_SHORT).show();
                    loginchecked  = true;
                }else{
                    Toast.makeText(MainActivity.this,"자동로그인 해제 됨",Toast.LENGTH_SHORT).show();
                    loginchecked = false;
                    editor.clear();
                    editor.commit();
                }
            }
        });
    }

    //click login button
    public void ClickLogin(View v){
        //not checked
        String id = editId.getText().toString();
        String pwd = editpwd.getText().toString();
        Boolean validation = loginValidation(id, pwd);

        if(validation){
            //Toast.makeText(MainActivity.this,"Login Success",Toast.LENGTH_SHORT).show();
            if(loginchecked){
                //if autologin checked, save values
                editor.putString("id",id);
                editor.putString("pwd",pwd);
                editor.putBoolean("autoLogin",false);
                editor.commit();
            }
            Toast.makeText(MainActivity.this,"로그인",Toast.LENGTH_SHORT).show();
            intent = new Intent(MainActivity.this,CarMainActivity.class);
            startActivity(intent);
            finish();
        }else{
            Toast.makeText(MainActivity.this,"Login falied..",Toast.LENGTH_SHORT).show();
        }
    }

    //check Id and Password
    private boolean loginValidation(String id, String pwd){
        if (pref.getString("id","").equals(id) && pref.getString("pwd","").equals(pwd)){
            //login success
            return true;
        }else {
            //login fail
            Toast.makeText(MainActivity.this,"Login fail",Toast.LENGTH_SHORT).show();
            return false;
        }
    }

    public void toLogout(){
        editor.putBoolean("autoLogin",true);

    }


}
