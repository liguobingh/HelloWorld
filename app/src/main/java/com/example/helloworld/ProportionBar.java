package com.example.helloworld;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;


public class ProportionBar extends View {

    private Context mContext;
    private int height;
    private int mWidth;
    private Paint mPaint;
    private Path mPath;
    private int[] mColors = new int[]{
            Color.parseColor("#F9F9FA"),
            Color.parseColor("#FFDD56"),
            Color.parseColor("#CA85F6"),
            Color.parseColor("#5ED1DB")};
    private static final int BG_COLOR = Color.parseColor("#FFFFFF");
    private float[] radii = new float[]{15, 15, 0, 0, 0, 0, 15, 15};
    private float[] radi = new float[]{15, 15, 15, 15, 15, 15, 15, 15};
    double scale[];

    public ProportionBar(Context context) {
        this(context, null);
    }

    public ProportionBar(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public ProportionBar(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mContext = context;
        init(mContext);
    }

    private void init(Context context) {
//        setWillNotDraw(false);


//        mWidth = context.getResources().getDisplayMetrics().widthPixels - ScreenUtils.dip2px(context, 200);
//        mWidth = ScreenUtils.dip2px(context, 564);


        mPaint = new Paint();
        mPaint.setAntiAlias(true);
        mPath = new Path();
        scale = new double[]{};
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        setMeasuredDimension(getDefaultSize(getSuggestedMinimumWidth(), widthMeasureSpec),
                getDefaultSize(getSuggestedMinimumHeight(), heightMeasureSpec));
//        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

//    @Override
//    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
//        mWidth = w;
//        super.onSizeChanged(w, h, oldw, oldh);
//    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        height = getMeasuredHeight();
        Log.d("@@@@@", "init-height: " + height);
        mWidth = getMeasuredWidth();
        Log.d("@@@@@", "init-mWidth: " + mWidth);

        int left;
        int right;

        mPaint.setColor(mColors[0]);
        left = 0;
        right = (int) (mWidth * scale[0]);
        canvas.drawRect(left, 0, right, height, mPaint);

        mPaint.setColor(mColors[1]);
        left = right;
        right = (int) (right + mWidth * scale[1]);
        canvas.drawRect(left, 0, right, height, mPaint);

        mPaint.setColor(mColors[2]);
        left = right;
        right = (int) (right + mWidth * scale[2]);
        canvas.drawRect(left, 0, right, height, mPaint);

        mPaint.setColor(mColors[3]);
        left = right;
        right = (int) (right + mWidth * (1 - scale[0] - scale[1] - scale[2]));
        canvas.drawRect(left, 0, right, height, mPaint);

    }

    /**
     * 给数据赋值
     *
     * @param scales
     */
    public void setScales(double[] scales) {
        scale = scales;
        invalidate();
    }
}