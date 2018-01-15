package com.example.android.androidyoutube;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;

public class GFXSurface extends AppCompatActivity implements View.OnTouchListener {

    MyBringBackSurface ourSurfaceview;
    float x, y, sX, sY, fX, fY, dX, dY, aniX, aniY, scaledX, scaledY;
    Bitmap test, plus;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ourSurfaceview = new MyBringBackSurface(this);
        ourSurfaceview.setOnTouchListener(this);
        x = 0;
        y = 0;
        sX = 0;
        sY = 0;
        fX = 0;
        fY = 0;
        dX = dY = aniX = aniY = scaledX = scaledY = 0;
        test = BitmapFactory.decodeResource(getResources(), R.drawable.ballin);
        plus = BitmapFactory.decodeResource(getResources(), R.drawable.plus);
        setContentView(ourSurfaceview);
    }

    @Override
    protected void onPause() {
        super.onPause();
        ourSurfaceview.pause();
    }

    @Override
    protected void onResume() {
        super.onResume();
        ourSurfaceview.resume();
    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {

        try {
            Thread.sleep(50);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        x = event.getX();
        y = event.getY();

        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                sX = event.getX();
                sY = event.getY();
                dX = dY = aniX = aniY = scaledX = scaledY = fX = fY = 0;
                break;
            case MotionEvent.ACTION_UP:
                fX = event.getX();
                fY = event.getY();
                dX = fX - sX;
                dY = fY - sY;
                scaledX = dX/30;
                scaledY = dY/30;
                x = y = 0;
                break;
        }

        return true;
    }

    class MyBringBackSurface extends SurfaceView implements Runnable {

        SurfaceHolder ourHolder;
        Thread ourThread = null;
        boolean isRunning = false;

        public MyBringBackSurface(Context context) {
            super(context);
            ourHolder = getHolder();
        }

        protected void pause() {
            isRunning = false;
            while (true) {
                try {
                    ourThread.join();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                break;
            }
            ourThread = null;
        }

        protected void resume() {
            isRunning = true;
            ourThread = new Thread(this);
            ourThread.start();

        }

        @Override
        public void run() {
            while (isRunning) {
                if (!ourHolder.getSurface().isValid())
                    continue;
                Canvas canvas = ourHolder.lockCanvas();
                canvas.drawRGB(56, 160, 30);
                if (x != 0 && y != 0) {
                    //Bitmap test = BitmapFactory.decodeResource(getResources(), R.drawable.ballin);
                    //canvas.drawBitmap(test, x, y, null);
                    canvas.drawBitmap(test, x - test.getWidth()/2, y - test.getWidth()/2, null);
                }
                if (sX != 0 && sY != 0) {
                    canvas.drawBitmap(plus, sX - plus.getWidth()/2, sY - plus.getWidth()/2, null);
                }
                if (fX != 0 && fY != 0) {
                    canvas.drawBitmap(test, (fX - test.getWidth()/2) - aniX, (fY - test.getWidth()/2)- aniY, null);
                    canvas.drawBitmap(plus, fX - plus.getWidth()/2, fY - plus.getWidth()/2, null);
                }
                aniX = aniX + scaledX;
                aniY = aniY + scaledY;
                ourHolder.unlockCanvasAndPost(canvas);
            }

        }
    }

}

