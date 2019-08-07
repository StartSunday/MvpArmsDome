package com.sun.component.commonres.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.AttributeSet;

import androidx.appcompat.widget.AppCompatTextView;

import com.sun.component.commonres.R;

public class BottomStampTextView extends AppCompatTextView {

    private int height;
    private int width;
    private int stampColor;
    private Paint mPaint;

    public BottomStampTextView(Context context) {
        this(context, null);
    }

    public BottomStampTextView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public BottomStampTextView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        TypedArray a = getContext().obtainStyledAttributes(attrs, R.styleable.BottomStampTextView, defStyleAttr, 0);
        stampColor = a.getColor(R.styleable.BottomStampTextView_stampColor, Color.TRANSPARENT);
        a.recycle();
        mPaint = new Paint();
        mPaint.setStyle(Paint.Style.FILL);
        mPaint.setAntiAlias(true);
        mPaint.setColor(stampColor);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        width = w;
        height = h;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        boolean isChinese = getText().toString().matches("[\u4e00-\u9fa5]+");
        if (isChinese) {
            canvas.drawRoundRect(new RectF(0, height * 0.76f, width, height * 0.92f), height * 0.8f, height * 0.8f, mPaint);
        } else {
            canvas.drawRoundRect(new RectF(0, height * 0.7f, width, height * 0.86f), height * 0.8f, height * 0.8f, mPaint);
        }
        super.onDraw(canvas);
    }
}
