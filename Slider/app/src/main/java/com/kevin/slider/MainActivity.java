package com.kevin.slider;

import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.sothree.slidinguppanel.SlidingUpPanelLayout;

public class MainActivity extends AppCompatActivity {

    Button mButton;
    Button button;
    SlidingUpPanelLayout mPanelLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mButton = (Button) findViewById(R.id.button);
        button = (Button) findViewById(R.id.button1);
        mPanelLayout = (SlidingUpPanelLayout) findViewById(R.id.sliding_layout);

        //mPanelLayout.setPanelState(SlidingUpPanelLayout.PanelState.COLLAPSED);
        //mPanelLayout.setAnchorPoint(0.7f);

        mPanelLayout.addPanelSlideListener(new SlidingUpPanelLayout.PanelSlideListener() {
            @Override
            public void onPanelSlide(View panel, float slideOffset) {
                MediaPlayer player = MediaPlayer.create(MainActivity.this, R.raw.explosion);
                player.start();
            }

            @Override
            public void onPanelStateChanged(View panel, SlidingUpPanelLayout.PanelState previousState, SlidingUpPanelLayout.PanelState newState) {
               
            }
        });

        //mPanelLayout.setParallaxOffset(100);
        //mPanelLayout.setScrollIndicators(View.SCROLL_INDICATOR_TOP);

        mButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //mPanelLayout.setEnabled(false);
                //mPanelLayout.setTouchEnabled(false);
                //mPanelLayout.setOverlayed(true);
            }
        });

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //mPanelLayout.setEnabled(true);
               // mPanelLayout.setTouchEnabled(true);
                //mPanelLayout.setOverlayed(true);
            }
        });

    }
}
