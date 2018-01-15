package emobilis.com.sqlite;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Adapter;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.List;

public class FetchDataActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fetch_data);


        SQLiteDatabase db = openOrCreateDatabase("ThetaDb", MODE_PRIVATE, null);

        Cursor cursor = db.rawQuery("SELECT * FROM user", null);

        if(cursor.getCount() < 1) {
            Toast.makeText(this, "No records found", Toast.LENGTH_SHORT).show();
        }

        StringBuffer buffer = new StringBuffer();

        while(cursor.moveToNext()) {
            String userN = cursor.getString(0);
            String passN = cursor.getString(1);

            buffer.append(userN);
            buffer.append(passN);

        }

        AlertDialog.Builder alert = new AlertDialog.Builder(this);

        alert.setTitle("login Details");
        alert.setMessage(buffer);
        alert.setCancelable(true);
        alert.show();


        ListView listView = (ListView) findViewById(R.id.listView);
        ArrayAdapter<String> itemsAdapter =
                new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, (List<String>) cursor);
        listView.setAdapter(itemsAdapter);
    }
}
