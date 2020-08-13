package com.example.floatview;

import android.content.Context;
import android.graphics.Color;
import android.util.AttributeSet;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;

public class FloatLayout extends FrameLayout {

    public FloatLayout(@NonNull Context context) {
        super(context);
    }

    public FloatLayout(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public FloatLayout(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        setMeasuredDimension(150, 150);
    }

    public void setBackGround(int redId) {
        setBackgroundResource(redId);
    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);
        for (int i = 0; i < getChildCount(); i++) {
            if (getChildCount() == 1) {
                View view = getChildAt(i);
                view.layout(25, 25, 125, 125);
            }
            if (getChildCount() == 2) {
                View view = getChildAt(i);
                view.layout(30 + (i * 60) - (i * 12), 45, 60 + 30 + (i * 60) - (i * 12), 105);
            }
            if (getChildCount() == 3) {
                View view = getChildAt(i);
                if (i == 0) {
                    view.layout(31, 67, 50 + 31, 117);
                } else if (i == 1) {
                    view.layout(31 + 50 - 8, 67, 31 + 50 - 8 + 50, 117);
                } else if (i == 2) {
                    view.layout(50, 29, 100, 79);
                }
            }
            if (getChildCount() == 4) {
                View view = getChildAt(i);
                if (i == 0) {
                    view.layout(20, 50, 50 + 20, 50 + 50);
                } else if (i == 1) {
                    view.layout(80, 50, 50 + 80, 100);
                } else if (i == 2) {
                    view.layout(50, 80, 100, 130);
                } else if (i == 3) {
                    view.layout(50, 20, 100, 70);
                }
            }
            if (getChildCount() == 5) {
                View view = getChildAt(i);
                int angle = 360 / getChildCount();
                double a = Math.toRadians(i * angle) + angle / 2;
                double y = Math.sin(a) * 30 + 75;
                double x = Math.cos(a) * 30 + 75;
                view.layout((int) (x - 25), (int) (y - 25), (int) (x + 25), (int) (y + 25));
            }
        }

    }


    public void setmData(ArrayList<String> mData) {
        if (mData.size() <= 5) {
            removeAllViews();
            for (int i = 0; i < mData.size(); i++) {
                if (mData.size() == 1) {
                    FloatView imageView = new FloatView(getContext());
                    imageView.setSize(100);
                    imageView.setIsOnly(true);
                    addView(imageView);
                }
                if (mData.size() == 2) {
                    FloatView imageView = new FloatView(getContext());
                    imageView.setSize(60);
                    imageView.setIsOnly(i != 0);
                    imageView.setAngle(50);
                    addView(imageView);
                }
                if (mData.size() == 3) {
                    FloatView imageView = new FloatView(getContext());
                    imageView.setSize(50);
                    imageView.setAngle(45);
                    imageView.setIsOnly(false);
                    if (i == 1) {
                        imageView.setRotation(-115);
                        imageView.setRotat(-115);
                    } else if (i == 2) {
                        imageView.setRotation(120);
                        imageView.setRotat(120);
                    } else {
                        imageView.setRotation(5);
                        imageView.setRotat(5);
                    }
                    addView(imageView);
                }
                if (mData.size() == 4) {
                    FloatView imageView = new FloatView(getContext());
                    imageView.setSize(50);
                    imageView.setAngle(45);
                    imageView.setIsOnly(false);
                    if (i == 0) {
                        imageView.setRotation(45);
                    } else if (i == 1) {
                        imageView.setRotation(-135);
                    } else if (i == 2) {
                        imageView.setRotation(-45);
                    } else {
                        imageView.setRotation(135);
                    }
                    addView(imageView);
                }
                if (mData.size() == 5) {
                    FloatView imageView = new FloatView(getContext());
                    imageView.setSize(50);
                    imageView.setAngle(60);
                    imageView.setIsOnly(false);
                    if (i == 0) {
                        imageView.setRotation(-225);
                        imageView.setColor(Color.BLACK);
                    } else if (i == 1) {
                        imageView.setColor(Color.RED);
                        imageView.setRotation(-150);
                    } else if (i == 2) {
                        imageView.setRotation(-80);
                        imageView.setColor(Color.GRAY);

                    } else if (i == 3) {
                        imageView.setRotation(-10);
                        imageView.setColor(Color.GREEN);

                    } else {
                        imageView.setRotation(62);
                        imageView.setColor(Color.BLUE);
                    }
                    addView(imageView);
                }
            }
        } else {
            Toast.makeText(getContext(), "最多5个", 1).show();
        }
    }
}