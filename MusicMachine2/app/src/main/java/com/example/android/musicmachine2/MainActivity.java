package com.example.android.musicmachine2;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.os.Message;
import android.os.Messenger;
import android.os.RemoteException;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = MainActivity.class.getSimpleName();
    public static final String KEY_SONG = "song";
    private boolean mBound = false;
    //private PlayerService mPlayerService;
    private Button mDownloadButton;
    private Button mPlayButton;
    private Messenger mServiceMessenger;
    private Messenger mActivityeMessenger = new Messenger(new ActivityHandler(this));
    private ServiceConnection mServiceConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder binder) {
            mBound = true;
//            PlayerService.LocalBinder localBinder = (PlayerService.LocalBinder) binder;
//            mPlayerService = localBinder.getService();
//            if (mPlayerService.isPlaying()) {
//                mPlayButton.setText("Pause");
//            }
            mServiceMessenger = new Messenger(binder);
            Message message = Message.obtain();
            message.arg1 = 2;
            message.arg2 = 1;
            message.replyTo = mActivityeMessenger;
            try {
                mServiceMessenger.send(message);
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            mBound = false;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        mDownloadButton = (Button) findViewById(R.id.downloadButton);
        mPlayButton = (Button) findViewById(R.id.playButton);

//        final DownloadThread thread = new DownloadThread();
//        thread.setName("Download Thread");
//        thread.start();

        mDownloadButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this, "Downloading", Toast.LENGTH_SHORT).show();

//                Runnable runnable = new Runnable() {
//                    @Override
//                    public void run() {
//                        downloadSong();
//                    }
//                };
                //Thread thread = new Thread(runnable);
//                DownloadThread thread = new DownloadThread();
//                thread.setName("Download Thread");
//                thread.start();

                //send messages to handler for processing
                for (String song : Playlist.songs) {
//                    Message message = Message.obtain();
//                    message.obj = song;
//                    thread.mHandler.sendMessage(message);
//                    Intent intent = new Intent(MainActivity.this, DownloadService.class);
                    Intent intent = new Intent(MainActivity.this, DownloadIntentService.class);
                    intent.putExtra(KEY_SONG, song);
                    startService(intent);
                }
            }
        });

        mPlayButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mBound) {
//                    if (mPlayerService.isPlaying()) {
//                        mPlayerService.pause();
//                        mPlayButton.setText("Play");
//                    } else {
//                        Intent intent = new Intent(MainActivity.this, PlayerService.class);
//                        startService(intent);
//                        mPlayerService.play();
//                        mPlayButton.setText("Pause");
//                    }
                    Intent intent = new Intent(MainActivity.this, PlayerService.class);
                    startService(intent);
                    Message message = Message.obtain();
                    message.arg1 = 2;
                    message.replyTo = mActivityeMessenger;
                    try {
                        mServiceMessenger.send(message);
                    } catch (RemoteException e) {
                        e.printStackTrace();
                    }
                }
            }
        });

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

    public void changePlayButtonText(String text) {
        mPlayButton.setText(text);
    }

    @Override
    protected void onStart() {
        super.onStart();
        Intent intent = new Intent(this, PlayerService.class);
        bindService(intent, mServiceConnection, Context.BIND_AUTO_CREATE);
    }

    @Override
    protected void onStop() {
        super.onStop();
        if (mBound) {
            unbindService(mServiceConnection);
            mBound = false;
        }
    }
}


