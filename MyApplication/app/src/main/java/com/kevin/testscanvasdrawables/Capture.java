package com.kevin.testscanvasdrawables;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class Capture extends AppCompatActivity {

    GraphicalView mGraphicalView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mGraphicalView = new GraphicalView(this);
        setContentView(mGraphicalView);
    }
}
