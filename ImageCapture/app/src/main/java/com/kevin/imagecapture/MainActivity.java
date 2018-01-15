package com.kevin.imagecapture;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.hardware.Camera;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.IOException;

public class MainActivity extends AppCompatActivity {
    ImageView mImageView;
    Camera mCamera;
    ShowCamera mShowCamera;
    FrameLayout frameLayout;
    private static Camera isCameraAvailable() {
        Camera cam = null;
        try {
            cam = Camera.open();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return cam;
    }
    private Camera.PictureCallback captured = new Camera.PictureCallback(){
        @Override
        public void onPictureTaken(byte[] data, Camera camera) {
         Bitmap bitmap = BitmapFactory.decodeByteArray(data, 0, data.length);
            if (bitmap == null) {
                Toast.makeText(MainActivity.this, "Not Taken", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(MainActivity.this, "Taken", Toast.LENGTH_SHORT).show();
                mImageView.setImageBitmap(bitmap);
            }
            mCamera.release();
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mImageView = (ImageView) findViewById(R.id.image);
        mCamera = isCameraAvailable();
        mShowCamera = new ShowCamera(this, mCamera);
        frameLayout = (FrameLayout) findViewById(R.id.camera_preview);
        frameLayout.addView(mShowCamera);
    }

    public void capture(View view) {
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        startActivityForResult(intent, 5);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == 5 && resultCode == RESULT_OK) {
            super.onActivityResult(requestCode, resultCode, data);
            Bitmap bitmap = (Bitmap) data.getExtras().get("data");
            mImageView.setImageBitmap(bitmap);

        }
    }

    public void hardCapture(View view) {
        mCamera.takePicture(null, null, captured);
    }

    private class ShowCamera extends SurfaceView {

        SurfaceHolder mSurfaceHolder;
        Camera mCamera;

        public ShowCamera(Context context, Camera camera) {
            super(context);
            mCamera = camera;
            mSurfaceHolder = getHolder();
            mSurfaceHolder.addCallback(new SurfaceHolder.Callback() {
                @Override
                public void surfaceCreated(SurfaceHolder holder) {
                    try {
                        mCamera.setPreviewDisplay(mSurfaceHolder);
                        mCamera.startPreview();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }

                @Override
                public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {

                }

                @Override
                public void surfaceDestroyed(SurfaceHolder holder) {

                }
            });
        }
    }
}
