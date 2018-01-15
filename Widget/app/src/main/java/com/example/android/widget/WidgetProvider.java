package com.example.android.widget;

import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.widget.RemoteViews;
import android.widget.Toast;

public class WidgetProvider extends AppWidgetProvider {

    public static final String KEY_ITEM = "com.example.android.widget.KEY_ITEM";
    public static final String TOAST_ACTION = "com.example.android.widget.TOAST_ACTION";

    @Override
    public void onReceive(Context context, Intent intent) {
        if (intent.getAction().equals(TOAST_ACTION)) {
            String listItem = intent.getStringExtra(KEY_ITEM);
            Toast.makeText(context, listItem, Toast.LENGTH_SHORT).show();
//            onUpdate(context, AppWidgetManager.getInstance(context), null);
            intent.setAction(AppWidgetManager.ACTION_APPWIDGET_UPDATE);
        }
        super.onReceive(context, intent);
    }

    @Override
    public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds) {
        int[] realAppWidgetIds = AppWidgetManager.getInstance(context)
                .getAppWidgetIds(new ComponentName(context, WidgetProvider.class));
        for (int id: realAppWidgetIds) {
            RemoteViews remoteViews = new RemoteViews(context.getPackageName(), R.layout.activity_main);

            Intent serviceIntent = new Intent(context, WidgetService.class);
            remoteViews.setRemoteAdapter(R.id.listView, serviceIntent);

            int r = (int) (Math.random() * 0xff);
            int g = (int) (Math.random() * 0xff);
            int b = (int) (Math.random() * 0xff);
            int color = (0xff << 24) + (r << 16) + (g << 8) + b;
            remoteViews.setInt(R.id.frameLayout, "setBackgroundColor", color);

            Intent intent = new Intent(context, WidgetProvider.class);
            //intent.setAction(AppWidgetManager.ACTION_APPWIDGET_UPDATE);
            intent.setAction(TOAST_ACTION);
            intent.putExtra(AppWidgetManager.EXTRA_APPWIDGET_IDS, realAppWidgetIds);

            PendingIntent pendingIntent = PendingIntent.getBroadcast(
                    context,
                    0,
                    intent,
                    PendingIntent.FLAG_UPDATE_CURRENT
            );
            //remoteViews.setOnClickPendingIntent(R.id.frameLayout, pendingIntent);
            remoteViews.setPendingIntentTemplate(R.id.listView, pendingIntent);


            appWidgetManager.updateAppWidget(id, remoteViews);
        }
    }
}
