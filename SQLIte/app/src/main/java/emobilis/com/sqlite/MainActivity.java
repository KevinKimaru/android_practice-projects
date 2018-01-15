package emobilis.com.sqlite;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    Button mButton;
    EditText mEditText, mText;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button mButton = (Button) findViewById(R.id.button);
        final EditText mEditText = (EditText) findViewById(R.id.userName);
        final EditText mText = (EditText) findViewById(R.id.password);

        mButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SQLiteDatabase db = openOrCreateDatabase("ThetaDb", MODE_PRIVATE, null);
                db.execSQL("CREATE TABLE IF NOT EXISTS user (name TEXT, pass VARCHAR)");
                db.execSQL("INSERT INTO user VALUES('" + mEditText.getText() + "', '" + mText.getText() + "')");
                Toast.makeText(MainActivity.this, " SAVED", Toast.LENGTH_SHORT).show();
                Intent intent= new Intent(MainActivity.this, FetchDataActivity.class);
            }
        });


    }
}
