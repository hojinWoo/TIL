package team4.ajoubuscarpool;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import static android.widget.AdapterView.*;

public class MainActivity extends AppCompatActivity {

    private ListView listView;
    private ArrayAdapter adapter;
    private Toolbar toolbar;
    private SearchView sView;
    mySQLiteOpenHelper openHelper;
    private boolean isOpen = true;
    Animation FabOpen, FabClose, FabRClockwise, FabRanticlockWise;
    FloatingActionButton fab, fabUpload, fabClient;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.inflateMenu(R.menu.menu_main);

        openHelper = new mySQLiteOpenHelper(getApplicationContext(), "list", null, 1);

        Cursor cursor = openHelper.select("select * from CARPOOL");
        listView = (ListView) findViewById(R.id.carpoolList);
        adapter = new ArrayAdapter(this, R.layout.listitem);
        listView.setAdapter(adapter);
        setSupportActionBar(toolbar);

        FabOpen = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.fab_open);
        FabClose = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.fab_close);
        FabRClockwise = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.rotate_clockwise);
        FabRanticlockWise = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.rotate_anticlockwise);

        fab = (FloatingActionButton) findViewById(R.id.fab);
        fabUpload = (FloatingActionButton) findViewById(R.id.fabUpload);
        fabClient = (FloatingActionButton) findViewById(R.id.fabClient);
        fab.setOnClickListener(clickListener);
        fabUpload.setOnClickListener(clickListener);
        fabClient.setOnClickListener(clickListener);

        mkList(cursor);
    }

    View.OnClickListener clickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.fab :
                    if (!isOpen) {
                        fabUpload.startAnimation(FabOpen);
                        fabClient.startAnimation(FabOpen);
                        fab.startAnimation(FabRClockwise);
                        fabUpload.setClickable(true);
                        fabClient.setClickable(true);
                        isOpen = true;
                    } else {
                        fabUpload.startAnimation(FabClose);
                        fabClient.startAnimation(FabClose);
                        fab.startAnimation(FabRanticlockWise);
                        fabUpload.setClickable(false);
                        fabClient.setClickable(false);
                        isOpen = false;
                    }
                    break;
                case R.id.fabUpload :
                    Intent intentUpload = new Intent(getApplicationContext(), makeCarpool.class);
                    startActivity(intentUpload);
                    //startActivityForResult(intentUpload,0);
                    break;
                case R.id.fabClient :
                    Intent intentClient = new Intent(getApplicationContext(), makeRequest.class);
                    startActivity(intentClient);
                    break;
            }
        }
    };

    public void mkList(Cursor cursor) {
        adapter.clear();
        if (cursor.getCount() > 0) {
            while (cursor.moveToNext()) {
                String str = "";
                str = String.valueOf(cursor.getInt(0)) + ". " + cursor.getString(1) + " / " + cursor.getString(2) + " -> " + cursor.getString(3);
                adapter.add(str);
                adapter.notifyDataSetChanged();
            }
        }

        listView.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(getApplicationContext(), showItemDetails.class);
                intent.putExtra("position", position + 1);
                startActivity(intent);
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);

        MenuItem searchItem = menu.findItem(R.id.menu_search);
        sView = (SearchView) searchItem.getActionView();
        sView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                Cursor cursor = openHelper.select("select * from CARPOOL where Destination='" + query + "' or DeparturePoint='" + query + "'");

                if(query.equals("*") || query.equals(null))
                    cursor = openHelper.select("select * from CARPOOL");

                mkList(cursor);
                adapter.notifyDataSetChanged();
                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_show) {
            Intent intent = new Intent(getApplicationContext(), showLists.class);
            intent.putExtra("list", "REQUEST");
            startActivity(intent);
            return true;
        }
        if(id == R.id.action_paid)
        {
            Intent intent = new Intent(getApplicationContext(), showLists.class);
            intent.putExtra("list", "PAY");
            startActivity(intent);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onResume() {
        super.onResume();
        adapter.clear();
        Cursor cursor = openHelper.select("select * from CARPOOL");
        mkList(cursor);
        adapter.notifyDataSetChanged();
    }
}
