package com.example.floatview;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.yhao.floatwindow.FloatWindow;
import com.yhao.floatwindow.Screen;
import com.yhao.floatwindow.ViewStateListener;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private FloatLayout im;
    private int widthPixels, heightPixels;
    private FloatView view;
    private ArrayList<String> data = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        im = new FloatLayout(this);
        im.setVisibility(View.GONE);
        findViewById(R.id.add).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                im.setVisibility(View.VISIBLE);
                if (data.size() < 5) {
                    data.add("网页title");
                } else {
                    Toast.makeText(MainActivity.this, "最多5个", 1).show();
                }
                im.setmData(data);
            }
        });
        findViewById(R.id.delete).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!data.isEmpty()) {
                    data.remove(data.size() - 1);
                    im.setVisibility(data.size() == 0 ? View.GONE : View.VISIBLE);
                } else {
                    im.setVisibility(View.GONE);
                }
                im.setmData(data);
            }
        });
        im.setBackgroundResource(R.mipmap.float_slid_right);
        DisplayMetrics outMetrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(outMetrics);
        widthPixels = outMetrics.widthPixels;
        heightPixels = outMetrics.heightPixels;
        view = new FloatView(this);
        view.setBackgroundResource(R.mipmap.float_slid_right);
        view.setSize(150);
        view.setIsOnly(false);
        im.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showGuideView();
                im.setVisibility(View.GONE);
            }
        });
        FloatWindow
                .with(getApplicationContext())
                .setView(im)
                .setWidth(150)
                .setHeight(150)
                .setX(widthPixels - 150)                                   //设置控件初始位置
                .setY(Screen.height, 0.3f)
                .setDesktopShow(false)                        //桌面显示
                .setViewStateListener(new ViewStateListener() {
                    @Override
                    public void onPositionUpdate(int i, int i1) {
                        if (i == 0) {
                            im.setBackgroundResource(R.mipmap.float_slid_left);
                        } else if (i == widthPixels - 150) {
                            im.setBackgroundResource(R.mipmap.float_slid_right);
                        } else {
                            im.setBackgroundResource(R.mipmap.float_circlr);
                        }
                        if (FloatWindow.get().getY() < 0) {
                            FloatWindow.get().updateY(0);
                        }
                        if (FloatWindow.get().getY() > heightPixels - 300) {
                            FloatWindow.get().updateY(heightPixels - 300);
                        }
                    }

                    @Override
                    public void onShow() {

                    }

                    @Override
                    public void onHide() {

                    }

                    @Override
                    public void onDismiss() {

                    }

                    @Override
                    public void onMoveAnimStart() {

                    }

                    @Override
                    public void onMoveAnimEnd() {
                    }

                    @Override
                    public void onBackToDesktop() {

                    }
                })
                .build();
    }


    public void showGuideView() {
        ViewParent viewParent = (ViewParent) getWindow().getDecorView();
        if (viewParent == null) return;

        if (viewParent instanceof FrameLayout) {
            final FrameLayout frameParent = (FrameLayout) viewParent;
            final LinearLayout linearLayout = new LinearLayout(this);
            linearLayout.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
            linearLayout.setOrientation(LinearLayout.VERTICAL);
            linearLayout.setBackgroundColor(Color.parseColor("#88000000"));
            linearLayout.setGravity(Gravity.CENTER_VERTICAL);
            linearLayout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    frameParent.removeView(linearLayout);
                    im.setVisibility(View.VISIBLE);
                }
            });
            for (int i = 0; i < data.size(); i++) {
                TextView textView = new TextView(MainActivity.this);
                textView.setText(data.get(i));
                textView.setTextSize(50);
                textView.setPadding(0, 0, 0, 90);
                final int finalI = i;
                textView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Toast.makeText(MainActivity.this, "点击" + finalI, 1).show();
                    }
                });
                linearLayout.addView(textView);
            }
            frameParent.addView(linearLayout);
        }
    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    @Override
    protected void onStop() {
        super.onStop();
    }

}
