package com.kevin.audiomodes;

import android.content.Context;
import android.media.AudioManager;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    Button vibrate, ring, silent, mode;
    TextView status;
    AudioManager mAudioManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        vibrate = (Button) findViewById(R.id.vibrate);
        ring = (Button) findViewById(R.id.ring);
        silent = (Button) findViewById(R.id.silent);
        mode = (Button) findViewById(R.id.mode);

        status = (TextView) findViewById(R.id.status);

        mAudioManager = (AudioManager) getSystemService(Context.AUDIO_SERVICE);
    }

    public void vibrate(View view) {
        mAudioManager.setRingerMode(AudioManager.RINGER_MODE_VIBRATE);
        Toast.makeText(this, "Vibrate mode", Toast.LENGTH_SHORT).show();
        status.setText("Current Status: Vibrate");
    }

    public void ring(View view) {
        mAudioManager.setRingerMode(AudioManager.RINGER_MODE_NORMAL);
        status.setText("Current Status: Ring");
        Toast.makeText(this, "Ring mode", Toast.LENGTH_SHORT).show();
    }

    public void silent(View view) {
        mAudioManager.setRingerMode(AudioManager.RINGER_MODE_SILENT);
        status.setText("Current Status: Silent");
        Toast.makeText(this, "Silent mode", Toast.LENGTH_SHORT).show();
    }

    public void mode(View view) {
        int mod = mAudioManager.getRingerMode();
        if (mod == AudioManager.RINGER_MODE_NORMAL) {
            status.setText("Current Status: Ring");
        } else if(mod == AudioManager.RINGER_MODE_VIBRATE) {
            status.setText("Current Status: Vibrate");
        } else if (mod == AudioManager.RINGER_MODE_SILENT) {
            status.setText("Current Status: Silent");
        } else {

        }
    }
}
