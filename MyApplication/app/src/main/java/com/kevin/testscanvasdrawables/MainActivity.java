package com.kevin.testscanvasdrawables;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    TextView mIncrement;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mIncrement = (TextView) findViewById(R.id.increment);

    }

    public void goToCanvas(View view) {
        Intent intent = new Intent(this, Capture.class);
        startActivity(intent);
    }

    public void goToOtherCanvas(View view) {
        Intent intent = new Intent(this, CanvasActivity.class);
        startActivity(intent);
    }
}
