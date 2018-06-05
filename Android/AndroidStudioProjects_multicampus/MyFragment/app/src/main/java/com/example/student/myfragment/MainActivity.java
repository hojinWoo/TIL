package com.example.student.myfragment;

import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.io.FileDescriptor;
import java.io.PrintWriter;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    MainFragement main;
    LoginFragement login;
    RegisterFragement register;
    FragmentManager manager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        main = new MainFragement();
        login = new LoginFragement();
        register = new RegisterFragement();

        manager = getSupportFragmentManager();

        if (savedInstanceState == null) {
            //main화면 중앙에 새로운 fragement를 붙여서 commit하기
            getSupportFragmentManager().beginTransaction().add(R.id.container, new MainFragement()).commit();
        }
    }

    public void clickBt(View v){
        if(v.getId() == R.id.button){
            manager.beginTransaction().replace(R.id.container, main).commit();
        }else if(v.getId() == R.id.bt_login){
            manager.beginTransaction().replace(R.id.container, login).commit();
        }else if(v.getId() == R.id.bt_register){
            register.setType(1);
            manager.beginTransaction().replace(R.id.container, register).commit();
        }else if(v.getId() == R.id.bt_register2){
            register.setType(2);
            manager.beginTransaction().replace(R.id.container, register).commit();
        }
    }


    //Fragement(android.app) : 예전 version
    //Fragement(android.support.v4.app) : version4 이후로는 새롭게
    public static class MainFragement extends Fragment{
        @Nullable
        @Override
        public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
            //main layout을 framelayout에 올리는데 실제 main은 아니기 때문에 false로 설정
            View v = inflater.inflate(R.layout.mainlayout, container, false);
            return v;
        }
    }
    public static class LoginFragement extends Fragment{
        @Nullable
        @Override
        public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
            //main layout을 framelayout에 올리는데 실제 main은 아니기 때문에 false로 설정
            View v = inflater.inflate(R.layout.loginglayout, container, false);
            return v;
        }
    }
    public static class RegisterFragement extends Fragment{

        int type;

        public void setType(int type){
            this.type = type;
        }

        @Nullable
        @Override
        public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
            //main layout을 framelayout에 올리는데 실제 main은 아니기 때문에 false로 설정
            View v = inflater.inflate(R.layout.registerlayout, container, false);
            TextView tv = v.findViewById(R.id.tv_register);
            if(type == 1){
                tv.setText("TO HOME");
            }else if(type == 2){
                tv.setText("FROM LOGIN");
            }

            return v;
        }
    }

}
