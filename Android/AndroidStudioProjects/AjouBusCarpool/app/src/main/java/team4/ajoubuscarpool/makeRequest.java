package team4.ajoubuscarpool;

import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class makeRequest extends AppCompatActivity {
    Button ok, cancel;
    EditText dptInput, dstInput;
    EditText minPrice, maxPrice;
    EditText periodInput;
    EditText time;
    mySQLiteOpenHelper transaction;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_make_request);

        dptInput = (EditText) findViewById(R.id.dptInput);
        dstInput = (EditText) findViewById(R.id.dstInput);
        minPrice = (EditText) findViewById(R.id.minPrice);
        maxPrice = (EditText) findViewById(R.id.maxPrice);
        time = (EditText) findViewById(R.id.timeInput);
        periodInput = (EditText) findViewById(R.id.periodInput);

        transaction = new mySQLiteOpenHelper(getApplicationContext(), "list", null, 1);

        ok = (Button) findViewById(R.id.selectButton);
        ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String dpt = String.valueOf(dptInput.getText());
                String dst = String.valueOf(dstInput.getText());
                String min = String.valueOf(minPrice.getText());
                String max = String.valueOf(maxPrice.getText());
                String t = String.valueOf(time.getText());
                String period = String.valueOf(periodInput.getText());
                int cnt = 0;

                Cursor cursor = transaction.select("select * from REQUEST where DeparturePoint='" + dpt + "' AND Destination='" + dst + "'");

                if(cursor.getCount() != 0) {
                    cnt = cursor.getCount();
                    cnt++;
                }
                transaction.insert("REQUEST(DeparturePoint, Destination, Time, MinPrice, MaxPrice, Count, ServicePeriod)",
                        "'" + dpt + "', '" + dst + "', '" + t + "', " + min + ", " + max + ", " + cnt + ", '" + period + "'");

                if(cursor.getCount() > 15)
                {
                    transaction.delete("REQUEST", "DeparturePoint='" + dpt + "' AND Destination='" + dst + "'");
                }

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
