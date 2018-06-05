package team4.ajoubuscarpool;

import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

public class showLists extends AppCompatActivity {

    Button retButton;
    ListView list;
    ArrayAdapter adapter;
    mySQLiteOpenHelper opener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_lists);

        Intent intent = getIntent();
        String listName = intent.getExtras().getString("list");

        list = (ListView) findViewById(R.id.anyList);
        retButton = (Button) findViewById(R.id.retButton);
        adapter = new ArrayAdapter(getApplicationContext(), R.layout.listitem);
        opener = new mySQLiteOpenHelper(getApplicationContext(), "list", null, 1);

        Cursor cursor = opener.select("select * from " + listName);

        while(cursor.moveToNext())
        {
            String str = ".";
            switch(listName){
                case "REQUEST":
                    str = cursor.getInt(0) + ". " + cursor.getString(3) + " / " + cursor.getString(1) + " -> " + cursor.getString(2);
                    break;
                case "PAY":
                    str = cursor.getInt(0) + ". " + cursor.getString(1) + " / " + cursor.getString(2);
                    break;
            }

            adapter.add(str);
            adapter.notifyDataSetChanged();
        }

        list.setAdapter(adapter);
        retButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}
