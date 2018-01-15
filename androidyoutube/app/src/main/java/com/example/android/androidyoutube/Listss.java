package com.example.android.androidyoutube;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class Listss extends ListActivity {

    String classes[] = {"Beginner", "MainActivity", "TextPlay", "RecViewAnaClock", "Email", "Photo", "Get",
            "GFX", "GFXSurface", "SoundStuff", "Slider", "Tabs", "SimpleBrowser",
            "Flipper", "SharedPrefs", "InternalData", "ExternalData", "SQlite1", "SQLView", "Accelerate", "GLExample",
            "GLCubeEx", "Voice", "TextVoice", "SeekBarVolume"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //FULLSCREEN
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setListAdapter(new ArrayAdapter<String>(Listss.this, android.R.layout.simple_list_item_1, classes));
    }

    @Override
    protected void onListItemClick(ListView l, View v, int position, long id) {
        super.onListItemClick(l, v, position, id);
        String cheese = classes[position];
        try {
            Class myClass = Class.forName("com.example.android.androidyoutube." + cheese);
            Intent intent = new Intent(Listss.this, myClass);
            startActivity(intent);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);
        MenuInflater blowUp = getMenuInflater();
        blowUp.inflate(R.menu.cool_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.aboutUs:
                Intent i = new Intent(Listss.this, About.class);
                startActivity(i);
                break;
            case R.id.preferences:
                Intent p = new Intent(Listss.this, Prefs.class);
                startActivity(p);
                break;
            case R.id.exit:
                finish();
        }
        return false;
    }
}

