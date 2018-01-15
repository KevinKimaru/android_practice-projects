package com.example.android.androidyoutube;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

public class Accelerate extends AppCompatActivity implements SensorEventListener {

    float x, y, sensorX, sensorY;
    Bitmap ball;
    SensorManager sm;
    MyBringBackSurface ourSurfaceView;

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
                float centerX = canvas.getWidth() / 2;
                float centerY = canvas.getHeight() / 2;
                canvas.drawRGB(255, 0, 30);
                canvas.drawBitmap(ball, centerX + sensorX * 50, centerY + sensorY * 50, null);

                ourHolder.unlockCanvasAndPost(canvas);
            }

        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        sm = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        if (sm.getSensorList(Sensor.TYPE_ACCELEROMETER).size() != 0) {
            Sensor s = sm.getSensorList(Sensor.TYPE_ACCELEROMETER).get(0);
            sm.registerListener(this, s, SensorManager.SENSOR_DELAY_FASTEST);
        }
        ball = BitmapFactory.decodeResource(getResources(), R.drawable.ballin);
        x = y = sensorX = sensorY = 0;
        ourSurfaceView = new MyBringBackSurface(this);
        ourSurfaceView.resume();
        setContentView(ourSurfaceView);

    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        try {
            Thread.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        sensorX = event.values[0];
        sensorY = event.values[1];
    }


    @Override
    protected void onPause() {
        sm.unregisterListener(this);
        super.onPause();
    }


    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }
}

