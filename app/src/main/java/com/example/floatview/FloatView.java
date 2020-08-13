package com.example.floatview;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PathMeasure;
import android.os.Build;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;

import java.io.InputStream;


/**
 * Created by xiao.q
 * Date: 2020/5/11 14:06
 */
public class FloatView extends View {
    private Paint mPaint = new Paint();
    private Path mPath = new Path();
    private int size = 0;
    private int angle = 0;
    private boolean isOnly = true;
    private int color = Color.RED;
    private Bitmap bitmap;

    public FloatView(Context context) {
        super(context);
    }

    public FloatView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public FloatView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        bitmap = BitmapFactory.decodeResource(getContext().getResources(), R.drawable.a123);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        mPaint = new Paint();
        mPaint.setAntiAlias(true);
        mPaint.setStyle(Paint.Style.STROKE);
        if (isOnly) {
            mPath.addArc(0, 0, size, size, 0, 360);
        } else {
            mPath.addArc(0, 0, size, size, angle, 360 - 2 * angle);
        }
        PathMeasure measure = new PathMeasure(mPath, false);
        float[] starPoint = new float[]{0f, 0f};
        float[] endPoint = new float[]{0f, 0f};
        measure.getPosTan(0, starPoint, null);
        measure.getPosTan(measure.getLength(), endPoint, null);
        mPath.quadTo(size / 2, size / 2, starPoint[0], starPoint[1]);
        setMeasuredDimension(size, size);
    }

    public void setIsOnly(boolean isOnly) {
        this.isOnly = isOnly;
        invalidate();
    }

    public void setSize(int size) {
        this.size = size;
        invalidate();
    }

    public void setAngle(int angle) {
        this.angle = angle;
        invalidate();
    }

    public void setColor(int color) {
        this.color = color;
        invalidate();
    }

    private int rotat = 0;

    public void setRotat(int angle) {
        rotat = -angle;
        invalidate();
    }


    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.clipPath(mPath);
        Resources rec = getResources();
        InputStream in = rec.openRawResource(R.drawable.a123);
        Bitmap bitmap = BitmapFactory.decodeStream(in);
        int width = bitmap.getWidth();
        int height = bitmap.getHeight();
        float scaleWidth = (float) size / width;
        float scaleHeight = (float) size / height;
        Matrix matrix = new Matrix();
        matrix.postScale(scaleWidth, scaleHeight);
        Bitmap newBitmap1 = Bitmap.createBitmap(bitmap, 0, 0, width, height, matrix, false);
        // 绘制图片
        canvas.drawBitmap(newBitmap1, 0, 0, mPaint);
    }
}
