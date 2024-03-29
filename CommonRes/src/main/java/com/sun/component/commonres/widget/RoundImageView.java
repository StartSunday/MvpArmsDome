package com.sun.component.commonres.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.util.AttributeSet;

import androidx.appcompat.widget.AppCompatImageView;
import androidx.core.view.ViewCompat;

import com.sun.component.commonres.R;


/**
 * Created by yexiaokang on 2018/11/15.
 */
public class RoundImageView extends AppCompatImageView {

    private int mLeftTopRadius;
    private int mRightTopRadius;
    private int mRightBottomRadius;
    private int mLeftBottomRadius;

    private int mFillColor = Color.TRANSPARENT;
    private int mStrokeColor = Color.TRANSPARENT;
    private int mStrokeWidth = 0;
    private Paint mFillPaint = new Paint();
    private Paint mStrokePaint = new Paint();

    private Path mPath = new Path();

    public RoundImageView(Context context) {
        this(context, null);
    }

    public RoundImageView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public RoundImageView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.RoundImageView);
        if (a.hasValue(R.styleable.RoundImageView_corner_radius)) {
            int radius = a.getDimensionPixelOffset(R.styleable.RoundImageView_corner_radius, 0);
            setLeftTopRadius(radius);
            setRightTopRadius(radius);
            setRightBottomRadius(radius);
            setLeftBottomRadius(radius);
        }
        if (a.hasValue(R.styleable.RoundImageView_left_top_radius)) {
            int radius = a.getDimensionPixelOffset(R.styleable.RoundImageView_left_top_radius, 0);
            setLeftTopRadius(radius);
        }
        if (a.hasValue(R.styleable.RoundImageView_right_top_radius)) {
            int radius = a.getDimensionPixelOffset(R.styleable.RoundImageView_right_top_radius, 0);
            setRightTopRadius(radius);
        }
        if (a.hasValue(R.styleable.RoundImageView_right_bottom_radius)) {
            int radius = a.getDimensionPixelOffset(R.styleable.RoundImageView_right_bottom_radius, 0);
            setRightBottomRadius(radius);
        }
        if (a.hasValue(R.styleable.RoundImageView_left_bottom_radius)) {
            int radius = a.getDimensionPixelOffset(R.styleable.RoundImageView_left_bottom_radius, 0);
            setLeftBottomRadius(radius);
        }
        if (a.hasValue(R.styleable.RoundImageView_fill_color)) {
            mFillColor = a.getColor(R.styleable.RoundImageView_fill_color, mFillColor);
        }
        if (a.hasValue(R.styleable.RoundImageView_stroke_color)) {
            mStrokeColor = a.getColor(R.styleable.RoundImageView_stroke_color, mStrokeColor);
        }
        if (a.hasValue(R.styleable.RoundImageView_stroke_width)) {
            mStrokeWidth = a.getDimensionPixelOffset(R.styleable.RoundImageView_stroke_width, 0);
        }
        a.recycle();
        initPaint();
    }

    private void initPaint() {
        mFillPaint.setStyle(Paint.Style.FILL);
        mFillPaint.setAntiAlias(true);
        mFillPaint.setColor(mFillColor);

        mStrokePaint.setStyle(Paint.Style.STROKE);
        mStrokePaint.setAntiAlias(true);
        mStrokePaint.setColor(mStrokeColor);
        mStrokePaint.setStrokeWidth(mStrokeWidth);
    }

    public int getLeftTopRadius() {
        return mLeftTopRadius;
    }

    public void setLeftTopRadius(int leftTopRadius) {
        if (mLeftTopRadius != leftTopRadius) {
            mLeftTopRadius = leftTopRadius;
            ViewCompat.postInvalidateOnAnimation(this);
        }
    }

    public int getRightTopRadius() {
        return mRightTopRadius;
    }

    public void setRightTopRadius(int rightTopRadius) {
        if (mRightTopRadius != rightTopRadius) {
            mRightTopRadius = rightTopRadius;
            ViewCompat.postInvalidateOnAnimation(this);
        }
    }

    public int getRightBottomRadius() {
        return mRightBottomRadius;
    }

    public void setRightBottomRadius(int rightBottomRadius) {
        if (mRightBottomRadius != rightBottomRadius) {
            mRightBottomRadius = rightBottomRadius;
            ViewCompat.postInvalidateOnAnimation(this);
        }
    }

    public int getLeftBottomRadius() {
        return mLeftBottomRadius;
    }

    public void setLeftBottomRadius(int leftBottomRadius) {
        if (mLeftBottomRadius != leftBottomRadius) {
            mLeftBottomRadius = leftBottomRadius;
            ViewCompat.postInvalidateOnAnimation(this);
        }
    }

    public int getFillColor() {
        return mFillColor;
    }

    public void setFillColor(int fillColor) {
        if (mFillColor != fillColor) {
            mFillColor = fillColor;
            mFillPaint.setColor(mFillColor);
            ViewCompat.postInvalidateOnAnimation(this);
        }
    }

    public int getStrokeColor() {
        return mStrokeColor;
    }

    public void setStrokeColor(int strokeColor) {
        if (mStrokeColor != strokeColor) {
            mStrokeColor = strokeColor;
            mStrokePaint.setColor(mStrokeColor);
            ViewCompat.postInvalidateOnAnimation(this);
        }
    }

    public int getStrokeWidth() {
        return mStrokeWidth;
    }

    public void setStrokeWidth(int strokeWidth) {
        if (mStrokeWidth != strokeWidth) {
            mStrokeWidth = strokeWidth;
            mStrokePaint.setStrokeWidth(mStrokeWidth);
            ViewCompat.postInvalidateOnAnimation(this);
        }
    }

    @Override
    protected void onDraw(Canvas canvas) {
        int maxLeft = Math.max(mLeftTopRadius, mLeftBottomRadius);
        int maxRight = Math.max(mRightTopRadius, mRightBottomRadius);
        int minWidth = maxLeft + maxRight;
        int maxTop = Math.max(mLeftTopRadius, mRightTopRadius);
        int maxBottom = Math.max(mLeftBottomRadius, mRightBottomRadius);
        int minHeight = maxTop + maxBottom;
        int width = getWidth();
        int height = getHeight();
        // 只有图片的宽高大于设置的圆角距离的时候才进行裁剪
        if (width >= minWidth && height >= minHeight) {
            mPath.reset();
            // 四个角：右上，右下，左下，左上
            mPath.moveTo(mLeftTopRadius, 0);
            mPath.lineTo(width - mRightTopRadius, 0);
            mPath.quadTo(width, 0, width, mRightTopRadius);

            mPath.lineTo(width, height - mRightBottomRadius);
            mPath.quadTo(width, height, width - mRightBottomRadius, height);

            mPath.lineTo(mLeftBottomRadius, height);
            mPath.quadTo(0, height, 0, height - mLeftBottomRadius);

            mPath.lineTo(0, mLeftTopRadius);
            mPath.quadTo(0, 0, mLeftTopRadius, 0);

            canvas.clipPath(mPath);
        }
        if (mFillColor != Color.TRANSPARENT) {
            canvas.drawPath(mPath, mFillPaint);
        }
        super.onDraw(canvas);
        if (mStrokeWidth > 0) {
            canvas.drawPath(mPath, mStrokePaint);
        }
    }
}
