package com.example.dnghwls7.intentdemo2;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

/**
 * Created by dnghwls7 on 2016-08-25.
 */
public class FirstActivity extends AppCompatActivity {

    Button btnConfirm;
    Button btnCancle;
    EditText editText;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.setContentView(R.layout.first_layout);

        btnConfirm=(Button)findViewById(R.id.button);
        btnCancle=(Button)findViewById(R.id.button2);

        btnConfirm.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                editText = (EditText)findViewById(R.id.editText);
                Intent intent = new Intent();
                intent.putExtra("name",editText.getText().toString());
                setResult(RESULT_OK,intent);
                finish();
            }
        });

        btnCancle.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                setResult(RESULT_CANCELED,intent);
                finish();
            }
        });
    }
}
