package com.jnu.student.myfirstapplication.base;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.FrameLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import com.jnu.student.myfirstapplication.R;

public class BaseAppCompatActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // 设置状态栏颜色
        Window window = getWindow();
        window.setStatusBarColor(getResources().getColor(R.color.deep_black));
        //隐藏标题栏
        if (getSupportActionBar() != null)
            getSupportActionBar().hide();
        // 防止键盘弹出压缩布局
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);
    }

    public void showLoadingAnimation(Context context, String text, int opacity) {
        // 获取当前活动的根布局
        ViewGroup rootLayout = ((Activity) context).findViewById(android.R.id.content);

        // 禁用触摸事件
        ((Activity) context).getWindow().setFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE,
                WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);

        // 禁用触摸事件
        rootLayout.setDescendantFocusability(ViewGroup.FOCUS_BLOCK_DESCENDANTS);

        // 创建帧布局并设置标签
        FrameLayout frameLayout = new FrameLayout(context);
        frameLayout.setTag("loading_animation");

        // 设置帧布局的背景色和不透明度
        int backgroundColor = ContextCompat.getColor(context, R.color.black);
        frameLayout.setBackgroundColor(Color.argb(opacity, Color.red(backgroundColor), Color.green(backgroundColor), Color.blue(backgroundColor)));

        // 创建并设置ProgressBar
        ProgressBar progressBar = new ProgressBar(context, null, android.R.attr.progressBarStyleLarge);
        progressBar.getIndeterminateDrawable().setColorFilter(Color.WHITE, PorterDuff.Mode.SRC_IN);
        FrameLayout.LayoutParams params = new FrameLayout.LayoutParams(
                ViewGroup.LayoutParams.WRAP_CONTENT,
                ViewGroup.LayoutParams.WRAP_CONTENT);
        params.gravity = Gravity.CENTER;
        progressBar.setLayoutParams(params);

        // 创建并设置TextView
        TextView textView = new TextView(context);
        textView.setText(text);
        textView.setTextColor(Color.WHITE);
        textView.setGravity(Gravity.CENTER);
        FrameLayout.LayoutParams textParams = new FrameLayout.LayoutParams(
                ViewGroup.LayoutParams.WRAP_CONTENT,
                ViewGroup.LayoutParams.WRAP_CONTENT);
        textParams.gravity = Gravity.CENTER;
        textParams.topMargin = 200;  // 根据需要调整
        textView.setLayoutParams(textParams);

        // 将ProgressBar和TextView添加到帧布局
        frameLayout.addView(progressBar);
        frameLayout.addView(textView);

        // 将帧布局添加到根布局
        rootLayout.addView(frameLayout);

        // 显示加载动画
        frameLayout.setVisibility(View.VISIBLE);
    }

    public void showLoadingAnimation(Context context) {
        showLoadingAnimation(context, "加载中...", 255);
    }

    public void hideLoadingAnimation(Context context) {
        // 获取当前活动的根布局
        ViewGroup rootLayout = ((Activity) context).findViewById(android.R.id.content);

        // 恢复触摸事件
        ((Activity) context).getWindow().clearFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);

        // 获取帧布局
        FrameLayout frameLayout = rootLayout.findViewWithTag("loading_animation");

        // 如果帧布局存在，则从根布局中移除
        if (frameLayout != null) {
            rootLayout.removeView(frameLayout);
        }
    }


}
