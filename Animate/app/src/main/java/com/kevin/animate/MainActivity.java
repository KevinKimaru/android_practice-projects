package com.kevin.animate;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {
    ImageView mImageView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mImageView = (ImageView) findViewById(R.id.image);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_items, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        super.onOptionsItemSelected(item);
        switch (item.getItemId()) {
            case R.id.rotate:
                Animation animation = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.rotate);
                mImageView.startAnimation(animation);
                return true;
            case R.id.scale:
                Animation animation1 = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.scale);
                mImageView.startAnimation(animation1);
                return true;
            case R.id.fade:
                Animation animation2 = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.fade);
                mImageView.startAnimation(animation2);
                return true;
        }
        return false;
    }
}
