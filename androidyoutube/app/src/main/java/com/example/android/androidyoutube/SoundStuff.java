package com.example.android.androidyoutube;

import android.media.AudioManager;
import android.media.MediaPlayer;
import android.media.SoundPool;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

public class SoundStuff extends AppCompatActivity implements View.OnClickListener, View.OnLongClickListener {

    SoundPool sp;
    int explosion = 0;
    MediaPlayer mp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        View v =  new View(this);
        v.setOnClickListener(this);
        v.setOnLongClickListener(this);
        setContentView(v);

        sp = new SoundPool(5, AudioManager.STREAM_MUSIC, 0);
        explosion = sp.load(this, R.raw.explosion, 1);
        mp = MediaPlayer.create(this, R.raw.splash_tone);


    }

    @Override
    public void onClick(View v) {
        if (explosion != 0)
        sp.play(explosion, 1, 1, 0, 0, 1);
    }

    @Override
    public boolean onLongClick(View v) {
        mp.start();
        return false;
    }
}
