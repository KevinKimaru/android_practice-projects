package emobilis.com.listview;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    String[] cars = new String[]{"volvo", "bmw", "chevrolet", "mercedes"};

    private ListView mListView;
    private ArrayAdapter mArrayAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mListView = (ListView) findViewById(R.id.list);
        mArrayAdapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, cars);

        if(mListView != null){
            mListView.setAdapter(mArrayAdapter);
        }

        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if(position == 0) {
                    Toast.makeText(MainActivity.this, "volvo", Toast.LENGTH_SHORT).show();
                }

                if(position == 0) {
                    Toast.makeText(MainActivity.this, "volvo", Toast.LENGTH_SHORT).show();
                }

            }
        });


    }
}
