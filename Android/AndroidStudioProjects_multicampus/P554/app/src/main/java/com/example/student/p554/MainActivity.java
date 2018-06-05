package com.example.student.p554;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.util.concurrent.ExecutionException;

public class MainActivity extends AppCompatActivity {

    TextView textView;
    ProgressBar progressBar;
    MyTask myTask;
    Button button;
    ProgressDialog progressDialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textView = findViewById(R.id.textView);
        button = findViewById(R.id.button);
        progressBar = findViewById(R.id.progressBar);
        progressDialog = new ProgressDialog(MainActivity.this);
        progressBar.setMax(15);
    }
    public void clickBt(View v){
        myTask = new MyTask("192.168.111.100");
        /*try {
            int result = myTask.execute().get();
            //여기서 멈춘다. (log in 같은 경우 이런 경우에 사용)
            Log.d("click","@@@@");
        }catch (InterruptedException e){
            e.printStackTrace();
        }catch (ExecutionException e){
            e.printStackTrace();
        }*/
        myTask.execute();
        Log.d("click","@@@@");
    }
    //Thread (진행 전 후에도 다른 것을 할 수 있음)
    //AsyncTask : network 시 많이 필요 // Generic
    //argument type이 중요
    //chating, 조회,
    class MyTask extends AsyncTask<String, Integer, Integer>{
        String msg;

        public MyTask(){

        }
        public MyTask(String msg){
            this.msg = msg;
        }
        //Thread 동작 전에 하기
        @Override
        protected void onPreExecute() {
            textView.setText("start Thread.");
            button.setEnabled(false);
            progressDialog.setTitle("Loading..");
            progressDialog.setMessage("ssassassa");
            progressDialog.setCancelable(false);
            progressDialog.show();
        }

        //Thread에서 Run 부분 (argument 추가 가능)
        @Override
        //Main Thread
        protected Integer doInBackground(String... Strings) {
            int result=0;
            Log.d("doInBackground","start");

            for(int i=1;i<=5;i++){
                try{
                    Thread.sleep(1000);
                    //result++;
                }catch (InterruptedException e){
                    e.printStackTrace();
                }
                result +=i;
                //자체 함수. 자동적으로 result값을 onProgressUpdate에 넣어줌
                publishProgress(result);
            }
            Log.d("doInBackground","finish");
            return result;
        }

        //가운데 argument
        @Override
        protected void onProgressUpdate(Integer... values) {
            //init
            progressBar.setProgress(values[0].intValue());

        }

        //Thread 종료 이후
        @Override
        protected void onPostExecute(Integer integer) {
            textView.setText("End Thread");
            button.setEnabled(true);
            progressDialog.dismiss();
        }
    }

}
