package com.example.student.p555;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.media.midi.MidiDeviceService;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.concurrent.ExecutionException;

//Log-in process
public class MainActivity extends AppCompatActivity {

    EditText editText1, editText2;
    TextView textView;
    LoginTask loginTask;
    Button button;
    ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        editText1 = findViewById(R.id.editText1);
        editText2 = findViewById(R.id.editText2);
        textView = findViewById(R.id.textView);
        button = findViewById(R.id.button);

        progressDialog = new ProgressDialog(MainActivity.this);


    }

    public void clickLogin(View v){
        loginTask = new LoginTask();
        String id = editText1.getText().toString();
        String pwd = editText2.getText().toString();
        textView.setText("Waiting");
        String result = "";

        /*try {
            //그냥 get을 쓰면 pre와 post가 동작이 안된다.....
            result = loginTask.execute(id,pwd).get();
            //task를 get으로 받고 기다리기
            if (result.equals("1")){
                textView.setText("Login OK!");
            }else{
                textView.setText("Login Fail");
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }*/

        //get으로 처리하는 것은 pre와 post 사용이 안되어서 안됨..
        ;
        loginTask.execute(id,pwd);
    }

    //String(ID, PASSWORD), VOID (중간에 넘길 것이 없음), String(결과)
    class LoginTask extends AsyncTask<String, Void,String>{
        //전처리 connect

        @Override
        protected void onPreExecute() {
            button.setEnabled(false);
            progressDialog.setMessage("Progress..");
            progressDialog.setCancelable(false);
            progressDialog.show();


            //super.onPreExecute();
        }

        //main thread (server와 연결)
        @Override
        protected String doInBackground(String... strings) {
            //id, pwd를 받음 by Generic
            String id = strings[0];
            String pwd = strings[1];
            String result = "";
            //먼저 가상으로 하기
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            if(id.equals("ㄱ") && pwd.equals("ㄴ")){
                result = "1";
            }else{
                result = "0";
            }
            return result;
        }

        //Thread가 끝나면 return값이 String에 들어간다
        //후처리 close
        @Override
        protected void onPostExecute(String s) {
            button.setEnabled(true);
            progressDialog.dismiss();
            AlertDialog.Builder dialog = new AlertDialog.Builder(MainActivity.this);
            if(s.equals("1")){
                textView.setText("Login OK!");
                dialog.setTitle("Wow");
                dialog.setMessage("Login Success");
                //dialog.setNegativeButton();

                //ctrl + space
                dialog.setPositiveButton("확인", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        editText1.setText("");
                        editText2.setText("");
                        return;
                    }
                });
                AlertDialog alert = dialog.create();
                alert.show();
            }else{
                textView.setText("Login Fail");
                dialog.setTitle("Alert");
                dialog.setMessage("Login Fail");
                //dialog.setNegativeButton();

                //ctrl + space
                dialog.setPositiveButton("확인", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        editText1.setText("");
                        editText2.setText("");
                        return;
                    }
                });
                AlertDialog alert = dialog.create();
                alert.show();
            }
            //super.onPostExecute(s);
        }
    }

}
