package com.example.holmesk.taijitu;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

/**
 * 作者：holmes k
 * 时间：2017.05.07 19:06
 */


public class TaijiView extends View {
    private Paint mPaint;
    private int mWidth;

    public TaijiView(Context context) {
        this(context, null);
    }

    public TaijiView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public TaijiView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initPaint();
    }

    private void initPaint() {
        mPaint = new Paint();
        mPaint.setColor(Color.BLACK);
        mPaint.setStrokeWidth(5);
        mPaint.setAntiAlias(true);
        mPaint.setDither(true);
        mPaint.setStyle(Paint.Style.FILL);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        mWidth = Math.min(getWidth(), getHeight());
        drawCircle(canvas);
        drawHalfCircle(canvas);
        drawSmallCircle(canvas);
    }

    private void drawCircle(Canvas canvas) {
        mPaint.setColor(Color.BLACK);
        //x轴正轴为0°
//    canvas.drawArc(new RectF(0, 0, width, width), 90, 180, true, mPaint);
        canvas.drawArc(new RectF(0, 0, mWidth, mWidth), 270, -180, true, mPaint);

        mPaint.setColor(Color.YELLOW);
//    canvas.drawArc(new RectF(0, 0, width, width), 90, -180, true, mPaint);
        canvas.drawArc(new RectF(0, 0, mWidth, mWidth), 270, 180, true, mPaint);
    }

    private void drawHalfCircle(Canvas canvas) {
        mPaint.setColor(Color.BLACK);
        canvas.drawArc(new RectF(mWidth / 4, 0, mWidth / 2 + mWidth / 4, mWidth / 2),
                270, 180, true, mPaint);

        mPaint.setColor(Color.YELLOW);
        canvas.drawArc(new RectF(mWidth / 4, mWidth / 2, mWidth / 2 + mWidth / 4, mWidth),
                270, -180, true, mPaint);
    }

    private void drawSmallCircle(Canvas canvas) {
        mPaint.setColor(Color.BLACK);
        canvas.drawCircle(mWidth / 2, mWidth * 3 / 4, 20, mPaint);

        mPaint.setColor(Color.YELLOW);
        canvas.drawCircle(mWidth / 2, mWidth / 4, 20, mPaint);
    }
}
