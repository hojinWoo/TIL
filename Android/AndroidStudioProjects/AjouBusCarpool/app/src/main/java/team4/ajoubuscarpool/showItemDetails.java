package team4.ajoubuscarpool;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RatingBar;

public class showItemDetails extends AppCompatActivity {

    Button ok, cancel;
    EditText dptInput, dstInput;
    EditText minPrice, maxPrice;
    EditText periodInput;
    EditText driverName;
    EditText carInfo, career;
    EditText comments;
    RatingBar rate;
    mySQLiteOpenHelper transaction;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_item_details);

        Intent intent = getIntent();
        int position = intent.getExtras().getInt("position");

        transaction = new mySQLiteOpenHelper(getApplicationContext(), "list", null, 1);

        Cursor cursor = transaction.select("select * from CARPOOL where Idx=" + String.valueOf(position));

        cursor.moveToNext();

        ok = (Button) findViewById(R.id.selectButton);
        ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), payment.class);
                startActivity(intent);
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
        dptInput = (EditText) findViewById(R.id.dptInput);
        dptInput.setText(cursor.getString(2));
        dstInput = (EditText) findViewById(R.id.dstInput);
        dstInput.setText(cursor.getString(3));
        minPrice = (EditText) findViewById(R.id.minPrice);
        minPrice.setText(String.valueOf(cursor.getInt(4)));
        maxPrice = (EditText) findViewById(R.id.maxPrice);
        maxPrice.setText(String.valueOf(cursor.getInt(5)));
        periodInput = (EditText) findViewById(R.id.periodInput);
        periodInput.setText(cursor.getString(8));

        cursor = transaction.select("select * from DRIVER where Idx=" + String.valueOf(cursor.getInt(10)));
        cursor.moveToNext();

        driverName = (EditText) findViewById(R.id.driverName);
        driverName.setText(cursor.getString(1));
        carInfo = (EditText) findViewById(R.id.carInfo);
        carInfo.setText(cursor.getString(2));
        career = (EditText) findViewById(R.id.career);
        career.setText(cursor.getString(3));
        comments = (EditText) findViewById(R.id.comments);
        comments.setText(cursor.getString(5));
        rate = (RatingBar) findViewById(R.id.ratingBar);
        rate.setRating(cursor.getFloat(4));
    }
}