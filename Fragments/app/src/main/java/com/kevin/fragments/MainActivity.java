package com.kevin.fragments;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.res.Configuration;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Configuration config = getResources().getConfiguration();

        FragmentManager fragmentManager = getFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

        //Check the device orientation and load the appropriate fragment

        if (config.orientation == Configuration.ORIENTATION_LANDSCAPE) {
            Lm_Fragment lm_fragment = new Lm_Fragment();
            fragmentTransaction.replace(android.R.id.content, lm_fragment);
        } else {
            Pm_Fragment pm_fragment = new Pm_Fragment();
            fragmentTransaction.replace(android.R.id.content, pm_fragment);
        }

        fragmentTransaction.commit();


    }
}
