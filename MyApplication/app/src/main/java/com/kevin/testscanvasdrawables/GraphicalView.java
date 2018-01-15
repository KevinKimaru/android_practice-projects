package com.kevin.testscanvasdrawables;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Rect;
import android.graphics.RectF;
import android.os.Build;
import android.view.View;

/**
 * Created by Kevin Kimaru Chege on 7/7/2017.
 */

class GraphicalView extends View {

    //bitmap
    Bitmap ball;
    float changingBallY;

    //rectF
    int rectWidth;
    int rectHeight;

    //rgb
    int n;

    //circle
    private float mRadius;
    private float mStrokeWidth;
    private int mValue;
    float value = 100;


    private int radius;

    public GraphicalView(Context context) {
        super(context);
        ball = BitmapFactory.decodeResource(getResources(), R.mipmap.ic_launcher);
        changingBallY = 0;
        value = 100;

        rectHeight = 400;
        rectWidth = 0;


        n = (int) Math.random() * 127;

        mRadius = 50;
        mValue = 100;
        mStrokeWidth = mRadius / 20;

        int radius = 0;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        //==============color
        //canvas.drawColor(Color.CYAN);
        //===============

        //=============bitmap
        canvas.drawBitmap(
                ball,
                canvas.getWidth() / 2 - ball.getWidth() / 2,
                changingBallY,
                null
        );
        if (changingBallY < canvas.getHeight()) {
            changingBallY += 10;
        } else {
            changingBallY = 0;
        }
        //=================


        //rect====================
        if (rectHeight < 200) {
            rectHeight += 1;
        } else {
            rectHeight = 0;
        }

        if (rectWidth < 200) {
            rectWidth += 1;
        } else {
            rectWidth = 0;
        }

        Rect rect = new Rect();
        rect.set(0, 0, rectWidth, rectHeight);
        Paint paint = new Paint();
        paint.setColor(Color.MAGENTA);
        canvas.drawRect(rect, paint);
        //================================

        //==========================text
        long time = System.currentTimeMillis();
        String ret = String.valueOf(time);
        Paint textPaint = new Paint();
        textPaint.setTextSize(40);
        textPaint.setStrokeWidth(5);
        //textPaint.setTextAlign(Paint.Align.CENTER);
        textPaint.setStyle(Paint.Style.STROKE);
        textPaint.setAntiAlias(true);
        textPaint.setColor(Color.MAGENTA);
        canvas.drawText(ret, canvas.getWidth() * 2 / 9, canvas.getHeight() * 4 / 5, textPaint);
        //===========================


        //=========================rgb
        int r = (int) (Math.random() * 127);
        int g = (int) (Math.random() * 127);
        int b = (int) (Math.random() * 127);

        if (r < 255 && b < 255 && g < 255) {
            r += n;
            g += n;
            b += n;
        }

        //canvas.drawRGB(r, g, b);
        //=======================

        //====================RoundRect and RectF
        Paint mPaint = new Paint();
        //mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setStyle(Paint.Style.FILL_AND_STROKE);
        mPaint.setStrokeWidth(10);
        mPaint.setColor(Color.CYAN);
        mPaint.setAntiAlias(true);
        ;

        if (radius < 100) {
            radius += 5;
        } else {
            radius = 0;
        }

        RectF mCircleRec = new RectF();
        mCircleRec.set(200, 200, radius + 200, radius + 200);
        canvas.drawRoundRect(mCircleRec, radius, radius, mPaint);

        //==========================circle
        Paint circlePaint = new Paint();
        paint.setColor(Color.GREEN);
        circlePaint.setStyle(Paint.Style.STROKE);
        canvas.drawCircle(300, 500, 100, circlePaint);
        //=========================

        //======================ARCS
        Paint arcPaint = new Paint();
        arcPaint.setStyle(Paint.Style.STROKE);
        arcPaint.setStrokeWidth(8);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            canvas.drawArc(400, 350, 600, 650, 10, 50, true, arcPaint);
        }

        RectF rectFArc = new RectF();
        rectFArc.set(0, 350, 200, 650);
        canvas.drawArc(rectFArc, 70, 210, true, arcPaint);
        //==============================

        //=======================LINE
        canvas.drawLine(0, 400, canvas.getWidth(), 600, arcPaint);
        //==========================

        //=====================POINTS
        Paint pointPaint = new Paint();
        pointPaint.setStyle(Paint.Style.STROKE);
        pointPaint.setStrokeWidth(20);
        pointPaint.setStrokeCap(Paint.Cap.ROUND);
        canvas.drawPoint(0, 600, pointPaint);

        float[] points = {0, 600, 50, 650, 100, 700, 150, 750};
        canvas.drawPoints(points, pointPaint);
        //==============================

        //=============================TEXTONPATH
        Path path = new Path();

        Paint arcPathPaint = new Paint();
        arcPathPaint.setStyle(Paint.Style.STROKE);
        arcPathPaint.setStrokeWidth(4);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            arcPathPaint.setLetterSpacing(12);
        }

        RectF pathRect = new RectF();
        pathRect.set(150, 300, 350, 500);
        path.addArc(pathRect, 0, 270);
        canvas.drawTextOnPath("111111111111111111111111111111111111111111111111111111111111111111111111111111",
                path, 0, -10, arcPathPaint);
        //====================================

        //================================PATHS
        float radiusPath = 100;
        float strokeSize = radiusPath/20;
        if (value >= 100) {
            value = 0;
        } else if (value < 100){
            value += 0.01;
        }

        float sweep = 360 * value/100;

        RectF pathTest = new RectF();
        pathTest.set(strokeSize, strokeSize, 2*radiusPath, 2*radiusPath);

        Path pathPath = new Path();
        if (sweep == 360) {
            pathPath.addCircle(radiusPath + strokeSize / 2,
                    radiusPath + strokeSize / 2,
                    radiusPath - strokeSize / 2,
                    Path.Direction.CCW);
        } else if (sweep > 0){
            pathPath.arcTo(pathTest, 90, sweep, true);
        }
        Paint pathPaint = new Paint();
        pathPaint.setStyle(Paint.Style.STROKE);
        pathPaint.setStrokeWidth(strokeSize);
        canvas.drawPath(pathPath, pathPaint);
        //====================================

        invalidate();

    }

}
