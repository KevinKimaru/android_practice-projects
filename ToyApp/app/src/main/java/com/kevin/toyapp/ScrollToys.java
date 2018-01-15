package com.kevin.toyapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class ScrollToys extends AppCompatActivity {

    TextView mToysListView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scroll_toys);

        mToysListView = (TextView) findViewById(R.id.tv_toy_names);

        String[] toyNames = ToyBox.getToyNames();

        for(String toyName: toyNames) {
            mToysListView.append(toyName + "\n\n\n");
        }

    }
}
