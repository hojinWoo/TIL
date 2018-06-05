package com.example.dnghwls7.listviewdemo1;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    ListView listView;
    TextView selectText;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listView=(ListView)findViewById(R.id.listView);
        selectText=(TextView) findViewById(R.id.selectText);
        //listview객체를 데이타와 엮으려면 adapter 개체를 만들어서 연결작업을 해줘야 함

        ArrayAdapter adapter=ArrayAdapter.createFromResource(
                getApplicationContext(),
                R.array.fruits,
                android.R.layout.simple_list_item_1
        );

        listView.setAdapter(adapter);

        //setOnItemClickListener

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                TextView tv = (TextView)view;
                Log.i("item",tv.getText().toString());
                selectText.setText(tv.getText().toString());
            }
        });

    }

}
