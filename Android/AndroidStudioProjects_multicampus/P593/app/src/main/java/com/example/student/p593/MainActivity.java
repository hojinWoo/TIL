package com.example.student.p593;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.os.AsyncTask;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class MainActivity extends AppCompatActivity {
    EditText editText,editText2;
    LoginTask loginTask;
    LocationTask locationTask;

    ProgressDialog progressDialog;

    boolean flag = true;
    AlertDialog.Builder alBuiler;
    AlertDialog dialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        editText = findViewById(R.id.editText);
        editText2 = findViewById(R.id.editText2);

        progressDialog = new ProgressDialog(MainActivity.this);
        alBuiler = new AlertDialog.Builder(MainActivity.this);
        new Thread(r).start();
    }

    Runnable r  = new Runnable(){
        @Override
        public void run() {
            while(flag) {
                try{
                    //3초마다 나의 위치 전송 > 밧데리가 계속 소모
                    Thread.sleep(3000);
                }catch (InterruptedException e){
                    e.printStackTrace();
                }

                //get location
                locationTask = new LocationTask("http://70.12.114.133/android/location.jsp");
                locationTask.execute(37.12,127.123);
            }
        }
    };



    class LocationTask extends AsyncTask<Double, String, String>{

        String url;
        public LocationTask(){}
        public LocationTask(String url){
            this.url=url;
        }

        @Override
        protected String doInBackground(Double... doubles) {

            double lat = doubles[0];
            double lon = doubles[1];

            url += "?lat="+lat+"&lon="+lon;

            //HTTP REQUEST
            URL url;
            HttpURLConnection con = null;
            try{
                url = new URL(this.url);
                //Connection
                con = (HttpURLConnection)url.openConnection();
                if(con != null){
                    con.setConnectTimeout(5000);
                    //con.setReadTimeout(10000);
                    con.setRequestMethod("GET");
                    con.setRequestProperty("Accept","*/*");
                    if(con.getResponseCode()!=HttpURLConnection.HTTP_OK){
                        return null;
                    }
                }
            }catch (Exception e){
                progressDialog.dismiss();

                return e.getMessage();

            }finally {
                con.disconnect();
            }
            return null;
        }

        @Override
        protected void onPostExecute(String s) {
            progressDialog.dismiss();
            if(s !=null) {
                Toast.makeText(MainActivity.this, "" + s, Toast.LENGTH_SHORT).show();
            }
        }
    }


    //network to server (JAVA : android)
    public void clickBt(View v){
        String id = editText.getText().toString();
        String pwd = editText2.getText().toString();
        //more safe to code
        if(id == null || pwd == null || id.equals("") || pwd.equals("")){
            return;
        }

        loginTask = new LoginTask("http://70.12.114.133/android/login.jsp");
        loginTask.execute(id.trim(), pwd.trim());
    }

    //Thread to server (input, update, return)
    class LoginTask extends AsyncTask<String, String, String>{

        String url;
        //constructor
        public LoginTask(){

        }
        public LoginTask(String url){
            this.url = url;
        }

        @Override
        protected void onPreExecute() {
            progressDialog.setMessage("Login..");
            progressDialog.setCancelable(false);
            progressDialog.show();
        }

        @Override
        protected String doInBackground(String... strings) {
            String id = strings[0];
            String pwd = strings[1];
            //To JSP
            url += "?id="+id+"&pwd="+pwd;

            //HTTP REQUEST
            StringBuilder sb = new StringBuilder();
            URL url;
            HttpURLConnection con = null;
            BufferedReader reader = null;
            try{
                url = new URL(this.url);
                //Connection
                con = (HttpURLConnection)url.openConnection();
                if(con != null){
                    con.setConnectTimeout(5000);
                    //con.setReadTimeout(10000);
                    con.setRequestMethod("GET");
                    con.setRequestProperty("Accept","*/*");
                    if(con.getResponseCode()!=HttpURLConnection.HTTP_OK){
                        return null;
                    }

                    reader = new BufferedReader(new InputStreamReader(con.getInputStream()));
                    String line = null;
                    while(true){
                        line = reader.readLine();
                        if(line == null){
                            break;
                        }
                        //1 or 0
                        sb.append(line);
                    }
                }
            }catch (Exception e){
                progressDialog.dismiss();
                return e.getMessage(); //postExecute
                //
            }finally {
                try {
                    reader.close();
                    con.disconnect();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            return null;
        }



        @Override
        protected void onPostExecute(String s) {
            progressDialog.dismiss();
            super.onPostExecute(s);
            if(s.trim().equals("1")){
                Toast.makeText(MainActivity.this,"Login OK",Toast.LENGTH_SHORT).show();
            }else if(s.trim().equals("2")){
                Toast.makeText(MainActivity.this,"Login Fail",Toast.LENGTH_SHORT).show();
            }
            if(s !=null) {
                Toast.makeText(MainActivity.this, "" + s, Toast.LENGTH_SHORT).show();
            }
        }
    }


    @Override
    public void onBackPressed() {
        //android.support.v7.app
        alBuiler.setTitle("Alert");
        alBuiler.setMessage("Are you finish?");
        alBuiler.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                flag = false;
                try{
                    Thread.sleep(500);
                }catch (InterruptedException e){
                    e.printStackTrace();
                }
                finish();
            }
        });
        alBuiler.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                return;
            }
        });
        dialog = alBuiler.create();
        dialog.show();
    }

    //finish가 되면 destroy
    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}
