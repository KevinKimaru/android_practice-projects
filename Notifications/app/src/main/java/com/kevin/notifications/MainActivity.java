package com.kevin.notifications;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.TaskStackBuilder;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    Button start;
    Button cancel;
    Button update;


    private NotificationManager mNotificationManager;
    private int notificationId = 100;
    private int numMessages = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        start = (Button) findViewById(R.id.start);
        start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                displayNotification();
            }
        });
        cancel = (Button) findViewById(R.id.cancel);
        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cancelNotification();
            }
        });
        update = (Button) findViewById(R.id.update);
        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updateNotification();
            }
        });

    }

    private void displayNotification() {
        NotificationCompat.Builder builder = new NotificationCompat.Builder(this);

        builder.setContentTitle("New message");
        builder.setContentText("You've received new message");
        builder.setTicker("New message alert!");
        builder.setSmallIcon(R.mipmap.ic_launcher);
        builder.setNumber(++numMessages);
        builder.setAutoCancel(true);
        //builder.setContentInfo("Hey");
        builder.setDefaults(Notification.DEFAULT_VIBRATE);

        NotificationCompat.InboxStyle inboxStyle = new  NotificationCompat.InboxStyle();
        String[] events = new String[5];
        events[0] = new String("This is line 0");
        events[1] = new String("This is line 1");
        events[2] = new String("This is line 2");
        events[3] = new String("This is line 3");
        events[4] = new String("This is line 4");
        inboxStyle.setBigContentTitle("Big title details:");
        //moves events into the bigview
        for (int i = 0; i < events.length; i++) {
            inboxStyle.addLine(events[i]);
        }

        builder.setStyle(inboxStyle);

        Intent intent = new Intent(this, NotificationView.class);
        TaskStackBuilder taskStackBuilder = TaskStackBuilder.create(this);
        taskStackBuilder.addParentStack(NotificationView.class);
        taskStackBuilder.addNextIntent(intent);
        PendingIntent pendingIntent = taskStackBuilder.getPendingIntent(0, PendingIntent.FLAG_UPDATE_CURRENT);

        builder.setContentIntent(pendingIntent);

        mNotificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        mNotificationManager.notify(notificationId, builder.build());
    }

    private void cancelNotification() {
        mNotificationManager.cancel(notificationId);
    }

    private void updateNotification() {
        NotificationCompat.Builder builder = new NotificationCompat.Builder(this);

        builder.setContentTitle("New message");
        builder.setContentText("You've received new message");
        builder.setTicker("New message alert!");
        builder.setSmallIcon(R.mipmap.ic_launcher);
        builder.setNumber(++numMessages);

        Intent intent = new Intent(this, NotificationView.class);
        TaskStackBuilder taskStackBuilder = TaskStackBuilder.create(this);
        taskStackBuilder.addParentStack(NotificationView.class);
        taskStackBuilder.addNextIntent(intent);
        PendingIntent pendingIntent = taskStackBuilder.getPendingIntent(0, PendingIntent.FLAG_UPDATE_CURRENT);

        builder.setContentIntent(pendingIntent);

        mNotificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        mNotificationManager.notify(notificationId, builder.build());
    }
}
