package com.example.helloworld;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.os.Build;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;



public class ProportionBar extends View {

    private Context mContext;
    private int mHeight;
    private int mWidth;
    private Paint mPaint;
    private Path mPath;
    private int[] mColors = new int[]{
            Color.parseColor("#5ED1DB"),
            Color.parseColor("#CA85F6"),
            Color.parseColor("#FFDD56"),
            Color.parseColor("#EAEAEB")};
    private float[] radii = new float[]{15, 15, 0, 0, 0, 0, 15, 15};
    private float[] radi = new float[]{0, 0, 15, 15, 15, 15, 0, 0};

    int left = 0;
    int right = 0;
    double percent[] = new double[]{};

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
        mPaint = new Paint();
        mPaint.setAntiAlias(true);
        mPath = new Path();
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        mWidth = getMeasuredWidth();
        mHeight = getMeasuredHeight();

        for (int i = 0; i <= percent.length; i++) {
            // 存储占比为1的情况（空余部分也作为一种存储内容）
            if (percent[0] == 0.0 && percent[1] == 0 && percent[2] == 0) {
                mPaint.setColor(mColors[3]);
                canvas.drawRoundRect(0, 0, mWidth, mHeight, 15, 15, mPaint);
                break;
            } else if (percent[0] == 1) {
                mPaint.setColor(mColors[0]);
                canvas.drawRoundRect(0, 0, mWidth, mHeight, 15, 15, mPaint);
                break;
            } else if (percent[1] == 1) {
                mPaint.setColor(mColors[1]);
                canvas.drawRoundRect(0, 0, mWidth, mHeight, 15, 15, mPaint);
                break;
            } else if (percent[2] == 1) {
                mPaint.setColor(mColors[2]);
                canvas.drawRoundRect(0, 0, mWidth, mHeight, 15, 15, mPaint);
                break;
            }

            Log.d("@@@@@", "运行: ");
            if (i == 0) {
                mPaint.setColor(mColors[0]);
                left = right;
                right = left + (int) (mWidth * percent[i]);
                mPath.addRoundRect(left, 0, right, mHeight, radii, Path.Direction.CW);
                canvas.drawPath(mPath, mPaint);
                mPath.close();
                mPath.reset();
            } else if (i == percent.length) {
                mPaint.setColor(mColors[3]);
                left = right;
                right = left + (int) (mWidth * (1 - percent[0] - percent[1] - percent[2]));
                mPath.addRoundRect(left, 0, right, mHeight, radi, Path.Direction.CW);
                canvas.drawPath(mPath, mPaint);
                mPath.close();
                mPath.reset();
            } else {
                mPaint.setColor(mColors[i]);
                left = right;
                right = left + (int) (mWidth * percent[i]);
                if (left == 0) {
                    mPath.addRoundRect(left, 0, right, mHeight, radii, Path.Direction.CW);
                    canvas.drawPath(mPath, mPaint);
                    mPath.close();
                    mPath.reset();
                } else if (right == mWidth) {
                    mPath.addRoundRect(left, 0, right, mHeight, radi, Path.Direction.CW);
                    canvas.drawPath(mPath, mPaint);
                    mPath.close();
                    mPath.reset();
                } else {
                    canvas.drawRect(left, 0, right, mHeight, mPaint);
                }
            }
        }
    }

    /**
     * 传递存储量的比值
     *
     * @param scales
     */
    public void setScales(double[] scales) {
        percent = scales;
        Log.d("@@@@@", "percent[0]: " + percent[0]);
        Log.d("@@@@@", "percent[1]: " + percent[1]);
        Log.d("@@@@@", "percent[2]: " + percent[2]);
        invalidate();
    }
}