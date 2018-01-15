package com.example.android.androidyoutube;

import android.content.Context;
import android.os.PowerManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class GFX extends AppCompatActivity {

    MyBringBack ourView;
    PowerManager.WakeLock wl;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        //wake-lock
        PowerManager pM = (PowerManager) getSystemService(Context.POWER_SERVICE);
        wl = pM.newWakeLock(PowerManager.FULL_WAKE_LOCK, "whatever");

        super.onCreate(savedInstanceState);
        wl.acquire();
        ourView = new MyBringBack(this);
        setContentView(ourView);
    }

    @Override
    protected void onPause() {
        super.onPause();
        wl.release();
    }
}
