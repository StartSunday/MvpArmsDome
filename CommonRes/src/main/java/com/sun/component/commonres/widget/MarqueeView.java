package com.sun.component.commonres.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.text.TextPaint;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.View;
import android.view.WindowManager;

import androidx.annotation.Nullable;

import com.jess.arms.utils.ArmsUtils;
import com.sun.component.commonres.R;

public class MarqueeView extends View {
    private float mTextSize = 100; //字体大小
    private int mTextColor = Color.RED; //字体的颜色
    private boolean mIsRepeat;//是否重复滚动
    private int mStartPoint;// 开始滚动的位置  0是从最左面开始    1是从最末尾开始
    private int mDirection;//滚动方向 0 向左滚动   1向右滚动
    private int mSpeed;//滚动速度
    private TextPaint mTextPaint;
    private String margueeString = "";
    private int textWidth = 0, textHeight = 0;
    private int ShadowColor = Color.BLACK;
    public int currentX = 0;// 当前x的位置
    public int sepX = 1;//每一步滚动的距离
    private int width;
    private boolean scroll;

    public MarqueeView(Context context) {
        this(context, null);
    }

    public MarqueeView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public MarqueeView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(attrs, defStyleAttr);
    }

    private void init(AttributeSet attrs, int defStyleAttr) {
        TypedArray a = getContext().obtainStyledAttributes(attrs, R.styleable.MarqueeView, defStyleAttr, 0);
        mTextColor = a.getColor(R.styleable.MarqueeView_textcolor, Color.RED);
        mTextSize = a.getDimension(R.styleable.MarqueeView_textSize, 48);
        mIsRepeat = a.getBoolean(R.styleable.MarqueeView_isRepeat, false);
        mStartPoint = a.getInt(R.styleable.MarqueeView_startPoint, 0);
        mDirection = a.getInt(R.styleable.MarqueeView_direction, 0);
        mSpeed = a.getInt(R.styleable.MarqueeView_speed, 20);
        a.recycle();

        mTextPaint = new TextPaint();
        mTextPaint.setFlags(Paint.ANTI_ALIAS_FLAG);
        mTextPaint.setTextAlign(Paint.Align.LEFT);
    }

    public void setText(String msg) {
        if (!TextUtils.isEmpty(msg)) {
            measurementsText(msg);
        }
    }

    protected void measurementsText(String msg) {
        margueeString = msg;
        mTextPaint.setTextSize(mTextSize);
        mTextPaint.setColor(mTextColor);
        // 设定阴影(柔边, X 轴位移, Y 轴位移, 阴影颜色)
//        mTextPaint.setShadowLayer(5, 3, 3, ShadowColor);
        textWidth = (int) mTextPaint.measureText(margueeString);
        Paint.FontMetrics fontMetrics = mTextPaint.getFontMetrics();
        textHeight = (int) fontMetrics.bottom;
        WindowManager wm = (WindowManager) getContext().getSystemService(Context.WINDOW_SERVICE);
        width = wm.getDefaultDisplay().getWidth();
        if (mStartPoint == 0)
            currentX = 0;
        else
            currentX = width - getPaddingLeft() - getPaddingRight();
    }

    /**
     * 开始滚动
     */
    public void startScroll() {
        scroll = true;
        invalidate();
    }

    /**
     * 停止滚动
     */
    public void stopScroll() {
        scroll = false;
        invalidate();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        int paddingLeft = getPaddingLeft();
        int paddingTop = getPaddingTop();
        int paddingRight = getPaddingRight();
        int paddingBottom = getPaddingBottom();

        int contentWidth = getWidth() - paddingLeft - paddingRight;
        int contentHeight = getHeight() - paddingTop - paddingBottom;

        int centeYLine = paddingTop + contentHeight / 2;//中心线

        if (mDirection == 0) {//向左滚动
            if (contentWidth >= textWidth) {
                currentX = 0;
            } else {
                if (currentX <= -textWidth) {
                    if (!mIsRepeat) {//如果是不重复滚动
                        stopScroll();
                    }
                    currentX = contentWidth;
                } else {
                    currentX -= sepX;
                }
            }
        } else {//  向右滚动
            if (contentWidth >= textWidth) {
                currentX = contentWidth - textWidth;
            } else {
                if (currentX >= contentWidth) {
                    if (!mIsRepeat) {//如果是不重复滚动
                        stopScroll();
                    }
                    currentX = -textWidth;
                } else {
                    currentX += sepX;
                }
            }
        }

        if (canvas != null) {
            canvas.drawText(margueeString, currentX, centeYLine + ArmsUtils.dip2px(getContext(), textHeight) / 2, mTextPaint);
        }
        if (scroll) {
            invalidate();
        }
    }
}
