package com.example.android.androidyoutube;

import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RemoteViews;

public class WidgetConfig extends AppCompatActivity {

    EditText info;
    Button b;
    AppWidgetManager awm;
    int awID;
    Context c;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_widget_config);

        b = (Button) findViewById(R.id.bWidgetConfig);
        info = (EditText) findViewById(R.id.etWidgetConfig);
        c = WidgetConfig.this;

        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        if (bundle != null) {
            awID = bundle.getInt(AppWidgetManager.EXTRA_APPWIDGET_ID,
                    AppWidgetManager.INVALID_APPWIDGET_ID
                    );
        } else {
            finish();
        }

        awm = AppWidgetManager.getInstance(c);

        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String e = info.getText().toString();

                RemoteViews views = new RemoteViews(c.getPackageName(), R.layout.widget);
                views.setTextViewText(R.id.tvConfigInput, e);

                Intent i = new Intent(c, SplashActivity.class);
                PendingIntent pendingIntent = PendingIntent.getActivity(
                        c,
                        0,
                        i,
                        0
                );
                views.setOnClickPendingIntent(R.id.bWidgetOpen, pendingIntent);
                awm.updateAppWidget(awID, views);

                Intent result = new Intent();
                result.putExtra(AppWidgetManager.EXTRA_APPWIDGET_ID, awID);
                setResult(RESULT_OK, result);

                finish();
            }
        });
    }
}
