package team4.ajoubuscarpool;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

public class payment extends AppCompatActivity {
    Button ok, cancel;
    EditText cardNum, cvc;
    Spinner spin;
    ArrayAdapter adapter;
    String cardStr, cvcStr;
    mySQLiteOpenHelper open;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment);

        open = new mySQLiteOpenHelper(getApplicationContext(), "list", null, 1);
        ok = (Button) findViewById(R.id.okButton);
        ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                open.insert("PAY(customerName,customerInfo)", "'customer1','student'");
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

        cardNum = (EditText) findViewById(R.id.cardInput);
        cardStr = String.valueOf(cardNum.getText());
        cvc = (EditText) findViewById(R.id.CVCInput);
        cvcStr = String.valueOf(cvc.getText());
        spin = (Spinner) findViewById(R.id.companySelect);

        String[] str = new String[2];
        str[0] = "신한";
        str[1] = "삼성";

        adapter = new ArrayAdapter(getApplicationContext(), R.layout.listitem, str);
        spin.setAdapter(adapter);
    }
}
