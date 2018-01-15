package com.example.android.androidyoutube;

import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class SharedPrefs extends AppCompatActivity implements View.OnClickListener {

    Button save, load;
    EditText sharedData;
    TextView sharedResults;
    public static String fileName = "MySharedString";
    SharedPreferences someData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shared_prefs);

        setupVariables();
        someData = getSharedPreferences(fileName, 0);

    }

    private void setupVariables() {
        save = (Button) findViewById(R.id.bSave);
        load = (Button) findViewById(R.id.bLoad);
        sharedData = (EditText) findViewById(R.id.etSharedPrefs);
        sharedResults = (TextView) findViewById(R.id.tvLoadSharedPrefs);

        save.setOnClickListener(this);
        load.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.bSave:

                String stringData = sharedData.getText().toString();
                SharedPreferences.Editor editor = someData.edit();
                editor.putString("sharedString", stringData);
                editor.commit();

                break;
            case R.id.bLoad:

                someData = getSharedPreferences(fileName, 0);
                String dataReturned = someData.getString("sharedString", "Couldn't Load");
                sharedResults.setText(dataReturned);
                break;
        }
    }
}
