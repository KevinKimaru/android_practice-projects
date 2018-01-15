package com.example.android.androidyoutube;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TabHost;
import android.widget.TextView;

import static com.example.android.androidyoutube.R.id.tabhost;

public class Tabs extends AppCompatActivity implements View.OnClickListener {

    TabHost th;
    TextView showresults;
    long start = 0;
    long stop;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tabs);

        th = (TabHost) findViewById(tabhost);
        Button newTab = (Button) findViewById(R.id.bAddTab);
        Button start = (Button) findViewById(R.id.bStartWatch);
        Button stop = (Button) findViewById(R.id.bStopWatch);
        showresults = (TextView) findViewById(R.id.tvShowResults);

        newTab.setOnClickListener(this);
        start.setOnClickListener(this);
        stop.setOnClickListener(this);

        th.setup();

        TabHost.TabSpec specs = th.newTabSpec("tag1");
        specs.setContent(R.id.tab1);
        specs.setIndicator("Stopwatch");
        th.addTab(specs);

        specs = th.newTabSpec("tag2");
        specs.setContent(R.id.tab2);
        specs.setIndicator("Tab 2");
        th.addTab(specs);

        specs = th.newTabSpec("tag3");
        specs.setContent(R.id.tab3);
        specs.setIndicator("Add a tab");
        th.addTab(specs);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.bAddTab:

                TabHost.TabSpec ourSpec = th.newTabSpec("tag1");
                ourSpec.setContent(new TabHost.TabContentFactory() {
                    @Override
                    public View createTabContent(String tag) {
                        TextView text = new TextView(Tabs.this);
                        text.setText("You have just created a new Tab");
                        return text;
                    }
                });
                ourSpec.setIndicator("New Tab");
                th.addTab(ourSpec);
                break;
            case R.id.bStartWatch:

                start = System.currentTimeMillis();

                break;
            case R.id.bStopWatch:

                stop = System.currentTimeMillis();
                if (start != 0) {
                    long result = stop - start;

                    int millis = (int) result;
                    int seconds = (int) result/1000;
                    int minutes = seconds/60;
                    millis = millis % 100;
                    seconds = seconds % 60;

//                    showresults.setText(Long.toString(result));
                    showresults.setText(String.format("%d:%2d:%3d", minutes, seconds, millis));
                }

                break;
        }
    }
}
