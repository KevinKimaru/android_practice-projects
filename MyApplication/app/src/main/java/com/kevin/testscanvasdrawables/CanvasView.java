package com.kevin.testscanvasdrawables;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.RectF;
import android.support.annotation.Nullable;
import android.text.format.DateUtils;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by Kevin Kimaru Chege on 7/8/2017.
 */

public class CanvasView extends View {

    private float mRadius;
    private float mStrokeWidth;
    private int mValue;
    Context mContext;
    Paint mPaint;
    Path mPath;
    RectF mRectF;
    float angle = 270;

    public CanvasView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        mContext = context;
        TypedArray typedArray = context.getTheme().obtainStyledAttributes(
                attrs,
                R.styleable.CirclePath,
                0, 0
        );
        mRadius = typedArray.getDimension(R.styleable.CirclePath_radius, 50f);
        mValue = typedArray.getInteger(R.styleable.CirclePath_value, 100);
        mStrokeWidth = mRadius / 20;

        mPaint = new Paint();
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setAntiAlias(true);
        mPaint.setStrokeWidth(mStrokeWidth);


        mPath = new Path();

        mRectF = new RectF();
        mRectF.set(mStrokeWidth, mStrokeWidth, 2 * mRadius, 2 * mRadius);

        angle = 270;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        mPaint.setColor(Color.BLACK);
        drawCircle(canvas, mPaint, 0, 360);

        mPaint.setColor(Color.MAGENTA);
        drawCircle(canvas, mPaint, 270, 360f * mValue / 100);

        long MINUTE = DateUtils.MINUTE_IN_MILLIS;
        long SECOND = DateUtils.SECOND_IN_MILLIS;

        long CURR_TIME = System.currentTimeMillis();
        long CURR_TIME_TRUN = CURR_TIME / MINUTE * MINUTE;
        long DIFFERENCE_MILLISECONDS = CURR_TIME - CURR_TIME_TRUN;
        float DIFFERENCE_SECONDS = DIFFERENCE_MILLISECONDS / SECOND;

        mValue = 100 - -((int) (100 * (DIFFERENCE_SECONDS) / 60));

        mPaint.setTextSize(20);
        canvas.drawText(mValue + "", 2 * mRadius + 10, 2 * mRadius + 10, mPaint);
        canvas.drawText(DIFFERENCE_SECONDS + "", 2 * mRadius + 10, 2 * mRadius + 40, mPaint);

        angle = 270 + DIFFERENCE_SECONDS * (360f * DIFFERENCE_SECONDS / 60);
        canvas.save();
        canvas.rotate(angle,
                mRadius + mStrokeWidth / 2,
                mRadius + mStrokeWidth / 2 );
        canvas.drawLine( mRadius + mStrokeWidth / 2,
                mRadius + mStrokeWidth / 2,
                mRadius + mStrokeWidth / 2 + mRadius,
                mRadius + mStrokeWidth / 2,
                mPaint
                );
        canvas.restore();

        invalidate();

    }

    private void drawCircle(Canvas canvas, Paint paint, float start, float sweep) {
        mPath.reset();
        if (sweep == 360) {
            mPath.addCircle(
                    mRadius + mStrokeWidth / 2,
                    mRadius + mStrokeWidth / 2,
                    mRadius - mStrokeWidth / 2,
                    Path.Direction.CCW
            );
        } else if (sweep > 0) {
            mPath.arcTo(mRectF, start, sweep, false);
        }
        canvas.drawPath(mPath, paint);
    }

    public void setValue(int value) {
        mValue = value;
        invalidate();
    }
}
