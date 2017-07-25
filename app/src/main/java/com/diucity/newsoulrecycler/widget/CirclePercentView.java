package com.diucity.newsoulrecycler.widget;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.LinearGradient;
import android.graphics.Paint;
import android.graphics.RectF;
import android.graphics.Shader;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.View;


public class CirclePercentView extends View {

    private float mRadius;
    private float mStripeWidth;
    private int mHeight;
    private int mWidth;
    private int mCurPercent;
    private int mPercent;
    private float x;
    private float y;
    private int mEndAngle;
    private int mSmallColor;
    private int mBigColor;
    private int mWColor;
    private float mCenterTextSize;
    private boolean canSet;
    private int fPercent;
    private FinnishLinstener linstener;

    public void setLinstener(FinnishLinstener linstener) {
        this.linstener = linstener;
    }

    public boolean isCanSet() {
        return canSet;
    }

    public CirclePercentView(Context context) {
        this(context, null);
    }

    public CirclePercentView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public CirclePercentView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        canSet = true;
        mStripeWidth = dpToPx(20, context);
        mCurPercent = 0;
        mBigColor = Color.parseColor("#35A249");
        mSmallColor = Color.parseColor("#247040");
        mWColor = Color.parseColor("#E6E6E6");
        mCenterTextSize = spToPx(20, context);
        mRadius = dpToPx(100, context);
    }

    public void setmWColor(int mWColor) {
        this.mWColor = mWColor;
    }

    public void setmBigColor(int mBigColor) {
        this.mBigColor = mBigColor;
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int widthMode = MeasureSpec.getMode(widthMeasureSpec);
        int heightMode = MeasureSpec.getMode(heightMeasureSpec);
        int widthSize = MeasureSpec.getSize(widthMeasureSpec);
        int heightSize = MeasureSpec.getSize(heightMeasureSpec);

        if (widthMode == MeasureSpec.EXACTLY && heightMode == MeasureSpec.EXACTLY) {
            mRadius = widthSize / 2;
            x = widthSize / 2;
            y = heightSize / 2;
            mWidth = widthSize;
            mHeight = heightSize;
        }

        if (widthMode == MeasureSpec.AT_MOST && heightMode == MeasureSpec.AT_MOST) {
            mWidth = (int) (mRadius * 2);
            mHeight = (int) (mRadius * 2);
            x = mRadius;
            y = mRadius;

        }

        setMeasuredDimension(mWidth, mHeight);
    }

    @Override
    protected void onDraw(Canvas canvas) {


        mEndAngle = (int) (mCurPercent * 3.6);
        //绘制大圆
        Paint bigCirclePaint = new Paint();
        bigCirclePaint.setAntiAlias(true);
        bigCirclePaint.setColor(mWColor);


        canvas.drawCircle(x, y, mRadius, bigCirclePaint);


        //饼状图
        Paint sectorPaint = new Paint();
        sectorPaint.setColor(mSmallColor);
        sectorPaint.setAntiAlias(true);
        RectF rect = new RectF(0, 0, mWidth, mHeight);


        canvas.drawArc(rect, 270, mEndAngle, true, sectorPaint);


        //绘制小圆,颜色透明
        Paint smallCirclePaint = new Paint();
        smallCirclePaint.setAntiAlias(true);
        smallCirclePaint.setColor(mBigColor);
        canvas.drawCircle(x, y, mRadius - mStripeWidth, smallCirclePaint);


        //绘制文本
        Paint textPaint = new Paint();
        String text;
        if (mCurPercent == 0) {
            text = "点击上传";
        } else {
            text = mCurPercent + "%";
        }


        textPaint.setTextSize(mCenterTextSize);
        float textLength = textPaint.measureText(text);

        textPaint.setColor(Color.WHITE);
        canvas.drawText(text, x - textLength / 2, y, textPaint);


    }

    public void reSet() {
        mCurPercent = 0;
        mPercent = 0;
        invalidate();

    }

    public void setPercent(int percent) {
        if (percent <= mPercent) {
            return;
        }
        if (percent > 100) {
            return;
        }
        if (canSet) {
            setCurPercent(percent);
        } else {
            fPercent = percent;
        }

    }

    private void setCurPercent(int percent) {
        canSet = false;
        mPercent = percent;

        new Thread(new Runnable() {
            @Override
            public void run() {
                int sleepTime = 5;
                for (int i = mCurPercent; i <= mPercent; i++) {
                    if (fPercent > mPercent) {
                        mPercent = fPercent;
                    }
                    if (i % 20 == 0) {
                        sleepTime += 2;
                    }
                    try {
                        Thread.sleep(sleepTime);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    mCurPercent = i;
                    CirclePercentView.this.postInvalidate();
                }
                canSet = true;
                if (linstener != null && mCurPercent == 100)
                    linstener.reach();
            }

        }).start();

    }

    public static int dpToPx(int dp, Context context) {
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp, context.getResources().getDisplayMetrics());
    }

    public static int spToPx(int sp, Context context) {
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP, sp, context.getResources().getDisplayMetrics());
    }

    public interface FinnishLinstener {
        void reach();
    }

    @Override
    public boolean isFocused() {
        return true;
    }
}
