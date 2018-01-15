package com.example.android.androidyoutube;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ViewFlipper;

public class Flipper extends AppCompatActivity implements View.OnClickListener {

    ViewFlipper flippy;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_flipper);

        flippy = (ViewFlipper) findViewById(R.id.viewFlipper1);
        flippy.setOnClickListener(this);
        flippy.setFlipInterval(4000);
        flippy.startFlipping();
    }

    @Override
    public void onClick(View v) {
        flippy.showNext();
    }
}
