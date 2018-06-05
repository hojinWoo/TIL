package com.example.dnghwls7.formymental;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    ListView listView;
    TextView textView;
    Button button;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listView = (ListView)findViewById(R.id.listView);
        textView = (TextView)findViewById(R.id.textView1);
        button = (Button)findViewById(R.id.btnFirst);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

//                ArrayAdapter adapter=ArrayAdapter.createFromResource(
//                        getApplicationContext(),
//                        R.array.saying,
//                        android.R.layout.simple_list_item_1
//                );

            }
        });







    }
}
