package com.kevin.audiorecorder;

import android.media.MediaPlayer;
import android.media.MediaRecorder;
import android.os.Bundle;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.io.IOException;

public class MainActivity extends AppCompatActivity {

    Button start;
    Button stop;
    Button play;

    private MediaRecorder mAudioRecorder;
    private String outputFile = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        start = (Button) findViewById(R.id.start);
        stop = (Button) findViewById(R.id.stop);
        play = (Button) findViewById(R.id.play);

        play.setEnabled(false);
        stop.setEnabled(false);

        outputFile = Environment.getExternalStorageDirectory().getAbsolutePath() + "/myRecording.3gp";

        mAudioRecorder = new MediaRecorder();
        mAudioRecorder.setAudioSource(MediaRecorder.AudioSource.MIC);
        mAudioRecorder.setOutputFormat(MediaRecorder.OutputFormat.THREE_GPP);
        mAudioRecorder.setAudioEncoder(MediaRecorder.OutputFormat.AMR_NB);
        mAudioRecorder.setOutputFile(outputFile);

    }

    public void startRecording(View view) {
        try {
            mAudioRecorder.prepare();
            mAudioRecorder.start();

            start.setEnabled(false);
            stop.setEnabled(true);

            Toast.makeText(this, "Recording started", Toast.LENGTH_SHORT).show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void stopRecording(View view) {
        try {
            mAudioRecorder.stop();
            mAudioRecorder.release();

            mAudioRecorder = null;
            stop.setEnabled(false);
            play.setEnabled(true);
            Toast.makeText(this, "Audio recorded successfully", Toast.LENGTH_SHORT).show();
        } catch (IllegalStateException e) {
            Toast.makeText(this, e + "", Toast.LENGTH_SHORT).show();
        }
    }

    public void playAudio(View view) {
        MediaPlayer mediaPlayer = new MediaPlayer();
        try {
            mediaPlayer.setDataSource(outputFile);
            mediaPlayer.prepare();
            mediaPlayer.start();
            Toast.makeText(this, "Playing audio", Toast.LENGTH_SHORT).show();
        } catch (IOException e) {
            Toast.makeText(this, "" + e, Toast.LENGTH_SHORT).show();
        }
    }

}
