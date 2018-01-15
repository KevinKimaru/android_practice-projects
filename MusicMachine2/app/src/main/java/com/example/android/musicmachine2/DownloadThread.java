package com.example.android.musicmachine2;

import android.os.Looper;
import android.util.Log;

/**
 * Created by Kevin Kimaru Chege on 6/22/2017.
 */

public class DownloadThread extends Thread{

    private static final String TAG = DownloadThread.class.getSimpleName();
    public DownloadHandler mHandler;

    @Override
    public void run() {
//        downloadSong();
//        for (String song : Playlist.songs) {
//            downloadSong();
//        }
        Looper.prepare(); //creates looper for this thread and also creates the message queue
        mHandler = new DownloadHandler();
        Looper.loop(); //start looping over the message queue
    }

//    private void downloadSong() {
//        long endTime = System.currentTimeMillis() + 10 * 1000;
//        while (System.currentTimeMillis() < endTime) {
//            try {
//                Thread.sleep(1000);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//        }
//        Log.d(TAG, "Song downloaded");
//    }
}
