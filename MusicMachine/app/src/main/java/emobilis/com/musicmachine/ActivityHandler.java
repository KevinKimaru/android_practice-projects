package emobilis.com.musicmachine;

import android.os.Handler;
import android.os.Message;
import android.os.RemoteException;

/**
 * Created by Kevin Kimaru Chege on 4/18/2017.
 */

public class ActivityHandler extends Handler {

    private MainActivity mMainActivity;

    public ActivityHandler(MainActivity mainActivity){
        mMainActivity = mainActivity;
    }

    @Override
    public void handleMessage(Message msg) {
        if(msg.arg1 == 0){
            //Music is not playing
            if(msg.arg2 == 1){
                mMainActivity.changePlayButtonText("Play");
            }else {
                //Play the music
                Message message = Message.obtain();
                message.arg1 = 0;
                try {
                    msg.replyTo.send(message);
                } catch (RemoteException e) {
                    e.printStackTrace();
                }
                //Change pause button to play
                mMainActivity.changePlayButtonText("Pause");
            }
        }else if(msg.arg1 == 1) {
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
                //Change play button to pause
                mMainActivity.changePlayButtonText("Play");
            }
        }
    }
}
