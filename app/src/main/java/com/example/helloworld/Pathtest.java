package com.example.helloworld;

import android.annotation.TargetApi;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.os.Build;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

public class Pathtest extends View {
    private int mHeight;
    private int mWidth;
    private Path mPath;
    private Paint mPaint;
    private float[] radii = new float[]{15, 15, 0, 0, 0, 0, 15, 15};
    private float[] radi = new float[]{0, 0, 15, 15, 15, 15, 0, 0};

    public Pathtest(Context context) {
        this(context, null);
    }

    public Pathtest(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public Pathtest(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    public void init() {
        mPath = new Path();
        mPaint = new Paint();
        mPaint.setAntiAlias(true);
        mPaint.setColor(Color.parseColor("#5ED1DB"));
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        mWidth = getMeasuredWidth();
        mHeight = getMeasuredHeight();

        mPath.addRoundRect(0, 0, 200, mHeight, radii, Path.Direction.CW);
        canvas.drawPath(mPath, mPaint);
        mPath.close();
        mPath.reset();
    }
}
