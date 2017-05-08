package com.example.holmesk.taijitu;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.View;

/**
 * 作者：holmes k
 * 时间：2017.05.08 08:27
 */


public class MyView extends View {

    private Rect mBound;
    private Paint mPaint;
    private String myText;
    private int myColor;
    private int mySize;

    public MyView(Context context) {
        this(context, null);
    }

    public MyView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public MyView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        /**
         * 获得我们所定义的自定义样式属性
         */
        TypedArray array = context.getTheme().obtainStyledAttributes(attrs, R.styleable.MyView, defStyleAttr, 0);

        int i = array.getIndexCount();

        for (int j = 0; j < i; j++) {

            int attr = array.getIndex(j);
            switch (attr) {
                case R.styleable.MyView_titleText:
                    myText = array.getString(attr);
                    break;
                case R.styleable.MyView_titleTextColor:
                    // 默认颜色设置为蓝色
                    myColor = array.getColor(attr, Color.BLUE);
                    break;
                case R.styleable.MyView_titleTextSize:
                    // 默认设置为16sp，TypeValue也可以把sp转化为px
                    mySize = array.getDimensionPixelSize(attr, (int) TypedValue.applyDimension(
                            TypedValue.COMPLEX_UNIT_SP, 16, getResources().getDisplayMetrics()));
                    break;
            }
        }

        array.recycle();

        /**
         * 获得绘制文本的宽和高
         */
        mPaint = new Paint();
        mPaint.setTextSize(mySize);
        mPaint.setColor(myColor);
        mBound = new Rect();
        mPaint.getTextBounds(myText, 0, myText.length(), mBound);

    }

    //测量视图的宽高
    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {

        int widthMode = MeasureSpec.getMode(widthMeasureSpec);
        int widthSize = MeasureSpec.getSize(widthMeasureSpec);
        int heightMode = MeasureSpec.getMode(heightMeasureSpec);
        int heightSize = MeasureSpec.getSize(heightMeasureSpec);
        int width;
        int height;
        if (widthMode == MeasureSpec.EXACTLY) {
            width = widthSize;
        } else {
            mPaint.setTextSize(mySize);
            mPaint.getTextBounds(myText, 0, myText.length(), mBound);
            float textWidth = mBound.width();
            int desired = (int) (getPaddingLeft() + textWidth + getPaddingRight());
            width = desired;
        }

        if (heightMode == MeasureSpec.EXACTLY) {
            height = heightSize;
        } else {
            mPaint.setTextSize(mySize);
            mPaint.getTextBounds(myText, 0, myText.length(), mBound);
            float textHeight = mBound.height();
            int desired = (int) (getPaddingTop() + textHeight + getPaddingBottom());
            height = desired;
        }


        setMeasuredDimension(width, height);
    }

    //绘制自定义view
    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        mPaint.setColor(Color.RED);
        canvas.drawRect(0, 0, getMeasuredWidth(), getMeasuredHeight(), mPaint);

        mPaint.setColor(myColor);
        canvas.drawText(myText, getWidth() / 2 - mBound.width() / 2,
                getHeight() / 2 + mBound.height() / 2, mPaint);
    }
}
