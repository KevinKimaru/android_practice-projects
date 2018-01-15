package com.kevin.toyapp;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class MainActivity extends ListActivity {
    String[] activities = {"ScrollToys", "Internet", "Recycler", "Intents", "WebPages",
            "Lifecycle", "VisualizerActivity", "Waitlist", "ContProviders", "TodoList",
            "HydrationReminder", "BoardingPass"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setListAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, activities));
    }

    @Override
    protected void onListItemClick(ListView l, View v, int position, long id) {
        super.onListItemClick(l, v, position, id);
        String activity = activities[position];
        Class mClass = null;
        try {
            mClass = Class.forName("com.kevin.toyapp." + activity);
            Intent intent = new Intent(MainActivity.this, mClass);
            startActivity(intent);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
