package emobilis.com.musicmachine;

import android.app.IntentService;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.util.Log;

/**
 * Created by Kevin Kimaru Chege on 4/17/2017.
 */

public class DownloadIntentService extends IntentService {
    private static final String TAG = MainActivity.class.getSimpleName() ;

    public DownloadIntentService(){
        super("DownloadIntentService");
        setIntentRedelivery(true);
    }

    @Override
    protected void onHandleIntent(@Nullable Intent intent) {
        String song = intent.getStringExtra(MainActivity.KEY_SONG);
        //downloadSong(song);
    }

    private void downloadSong() {
        long endTime = System.currentTimeMillis() + 10 * 1000;

        while(System.currentTimeMillis() < endTime){
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        Log.d(TAG, "Song downloaded!");
    }
}
