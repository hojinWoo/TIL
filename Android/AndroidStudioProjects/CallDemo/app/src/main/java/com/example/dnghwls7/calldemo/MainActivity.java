package com.example.dnghwls7.calldemo;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.net.Uri;
import android.provider.ContactsContract;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    Button button1;
    Button button2;
    Button button3;
    Button button4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        button1 = (Button)findViewById(R.id.button1);
        button2 = (Button)findViewById(R.id.button2);
        button3 = (Button)findViewById(R.id.button3);
        button4 = (Button)findViewById(R.id.button4);

        button1.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view) {
                String phone="010-4143-2965"; //전화번호
                Intent callntent = new Intent(Intent.ACTION_CALL);
                callntent.setData(Uri.parse("tel:"+phone));
                try{
                    startActivity(callntent);
                }catch (Exception ex)
                {
                    Log.i("Error",ex.getMessage());
                }
            }
        });
        button2.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view) {
                String phone="010-4143-2965"; //전화번호
                Intent smsIntent = new Intent(Intent.ACTION_SENDTO);
                smsIntent.setData(Uri.parse("sms:"+phone));
                smsIntent.putExtra("sms_body","문자를 보내봅시다");
                startActivity(smsIntent);
            }
        });

        button3.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                String phone="010-4143-2965"; //전화번호
                Intent contactIntent = new Intent(Intent.ACTION_INSERT);
                contactIntent.setType(ContactsContract.Contacts.CONTENT_TYPE);
                contactIntent.putExtra(ContactsContract.Intents.Insert.NAME,"홍길동");
                contactIntent.putExtra(ContactsContract.Intents.Insert.PHONE,phone);
                startActivity(contactIntent);
            }
        });

        button4.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                Intent email = new Intent(Intent.ACTION_SENDTO);
                String mailto = "mailto:dnghwls7@naver.com"+
                        "?cc=" + "alice@example.com"+"&subject="+Uri.encode("제목")+
                        "&body=" +Uri.encode("내용");
                email.setData(Uri.parse(mailto));
                try{
                    startActivity(Intent.createChooser(email, "Send Feedback"));
                }catch (ActivityNotFoundException ex){
                    Toast.makeText(getApplicationContext(),"There are no email clients"
                            ,Toast.LENGTH_LONG).show();
                }
            }
        });

    }
}
