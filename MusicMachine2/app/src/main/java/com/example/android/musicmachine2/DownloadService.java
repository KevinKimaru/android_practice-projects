package com.example.android.musicmachine2;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.os.Message;
import android.support.annotation.IntDef;
import android.support.annotation.Nullable;
import android.util.Log;

/**
 * Created by Kevin Kimaru Chege on 6/22/2017.
 */

public class DownloadService extends Service {

    private static final String TAG = DownloadService.class.getSimpleName();
    private DownloadHandler mHandler;

    @Override
    public void onCreate() {
        final DownloadThread thread = new DownloadThread();
        thread.setName("Download Thread");
        thread.start();
        while (thread.mHandler == null) {

        }
        mHandler = thread.mHandler;
        mHandler.setService(this);
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        String song = intent.getStringExtra(MainActivity.KEY_SONG);
//        downloadSong(song);
        Message message = Message.obtain();
        message.obj = song;
        message.arg1 = startId;
        mHandler.sendMessage(message);
        return Service.START_REDELIVER_INTENT;
    }

//    private void downloadSong(String song) {
//        long endTime = System.currentTimeMillis() + 10 * 1000;
//        while (System.currentTimeMillis() < endTime) {
//            try {
//                Thread.sleep(1000);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//        }
//        Log.d(TAG, song + " downloaded");
//    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
}
