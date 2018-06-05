package team4.ajoubuscarpool;

import android.content.Intent;
import android.database.Cursor;
import android.database.DataSetObserver;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RatingBar;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;

import java.util.ArrayList;

public class makeCarpool extends AppCompatActivity {
    Button ok, cancel;
    EditText dptInput, dstInput;
    EditText minPrice, maxPrice;
    EditText periodInput;
    EditText time;
    mySQLiteOpenHelper transaction;
    Spinner spin;
    String[] arr;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_make_carpool);
        Intent intent = getIntent();

        transaction = new mySQLiteOpenHelper(getApplicationContext(), "list", null, 1);
        Cursor cursor = transaction.select("select * from DRIVER");

        dptInput = (EditText) findViewById(R.id.dptInput);
        dstInput = (EditText) findViewById(R.id.dstInput);
        minPrice = (EditText) findViewById(R.id.minPrice);
        maxPrice = (EditText) findViewById(R.id.maxPrice);
        periodInput = (EditText) findViewById(R.id.periodInput);
        time = (EditText) findViewById(R.id.timeInput);
        spin = (Spinner) findViewById(R.id.selectDriver);
        //spin.setAdapter(adapt);

        arr = new String[5];
        int i = 0;

        while(cursor.moveToNext())
        {
            arr[i++] = cursor.getString(1);
        }

        ArrayAdapter<String> adt = new ArrayAdapter<String>(this, R.layout.listitem, arr);
        spin.setAdapter(adt);

        ok = (Button) findViewById(R.id.selectButton);
        ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String t = String.valueOf(time.getText());
                String dpt = String.valueOf(dptInput.getText());
                String dst = String.valueOf(dstInput.getText());
                String min = String.valueOf(minPrice.getText());
                String max = String.valueOf(maxPrice.getText());
                String period = String.valueOf(periodInput.getText());
                int driverIdx = spin.getSelectedItemPosition() + 1;

                transaction.insert("CARPOOL(Time, DeparturePoint, Destination, MinPrice, MaxPrice, ServicePeriod, driverInfo)", "'" + t + "', '" + dpt + "', " + "'" + dst + "', "
                + min + ", " + max + ", " + "'" + period + "', " + driverIdx);

                finish();
            }
        });
        cancel = (Button) findViewById(R.id.cancelButton);
        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}
