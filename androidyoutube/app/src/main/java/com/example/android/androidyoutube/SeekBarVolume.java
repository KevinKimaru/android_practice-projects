package com.example.android.androidyoutube;

import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.SeekBar;

public class SeekBarVolume extends AppCompatActivity {

    SeekBar mSeekBar;
    MediaPlayer mMediaPlayer;
    AudioManager mAudioManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_seek_bar_volume);

        mSeekBar = (SeekBar) findViewById(R.id.sbVolume);
        mMediaPlayer = MediaPlayer.create(this, R.raw.splash_tone);
        mMediaPlayer.start();
        mAudioManager = (AudioManager) getSystemService(AUDIO_SERVICE);

        int maxV = mAudioManager.getStreamMaxVolume(AudioManager.STREAM_MUSIC);
        int curV = mAudioManager.getStreamVolume(AudioManager.STREAM_MUSIC);
        mSeekBar.setMax(maxV);
        mSeekBar.setProgress(curV);
        mSeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                mAudioManager.setStreamVolume(AudioManager.STREAM_MUSIC, progress, 0);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
    }

    @Override
    protected void onPause() {
        super.onPause();
        mMediaPlayer.release();
    }
}
