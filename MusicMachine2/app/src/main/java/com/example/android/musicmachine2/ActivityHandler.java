package com.example.android.musicmachine2;

import android.os.Handler;
import android.os.Message;
import android.os.RemoteException;

/**
 * Created by Kevin Kimaru Chege on 6/23/2017.
 */

public class ActivityHandler extends Handler {

    private MainActivity mMainActivity;

    public ActivityHandler(MainActivity mainActivity) {
        mMainActivity = mainActivity;
    }

    @Override
    public void handleMessage(Message msg) {
        if (msg.arg1 == 0) {
            //Music is not playing
            if (msg.arg2 == 1) {
                mMainActivity.changePlayButtonText("Play");
            } else {
                //Play the music
                Message message = Message.obtain();
                message.arg1 = 0;
                try {
                    msg.replyTo.send(message);
                } catch (RemoteException e) {
                    e.printStackTrace();
                }
                //Change play button to say pause
                mMainActivity.changePlayButtonText("Pause");
            }
        } else {
            //Music is playing
            if (msg.arg2 == 1) {
                mMainActivity.changePlayButtonText("Pause");
            } else {
                //Pause the music
                Message message = Message.obtain();
                message.arg1 = 1;
                try {
                    msg.replyTo.send(message);
                } catch (RemoteException e) {
                    e.printStackTrace();
                }
                //Change play button to say play
                mMainActivity.changePlayButtonText("Play");
            }
        }
    }
}
