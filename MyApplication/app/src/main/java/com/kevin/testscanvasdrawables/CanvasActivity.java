package com.kevin.testscanvasdrawables;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.format.DateUtils;
import android.widget.TextView;

public class CanvasActivity extends AppCompatActivity {

    long MINUTE = DateUtils.MINUTE_IN_MILLIS;
    long SECOND = DateUtils.SECOND_IN_MILLIS;

    long CURR_TIME = System.currentTimeMillis();
    long CURR_TIME_TRUN = CURR_TIME / MINUTE * MINUTE;
    long DIFFERENCE_MILLISECONDS = CURR_TIME - CURR_TIME_TRUN;
    float DIFFERENCE_SECONDS = DIFFERENCE_MILLISECONDS / SECOND;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_canvas);

        double prevTime = 1.49950040e12;
        long maxTime = DateUtils.MINUTE_IN_MILLIS;
        long currTime = System.currentTimeMillis();

        int value = 100 - ((int) (100 * (DIFFERENCE_SECONDS) / 60));

        //((CanvasView) findViewById(R.id.canvasArea)).setValue(value);
        ((TextView) findViewById(R.id.testTextView)).setText(DIFFERENCE_SECONDS + "");


    }
}
