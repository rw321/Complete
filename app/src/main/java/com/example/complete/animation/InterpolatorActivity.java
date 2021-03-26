package com.example.complete.animation;

import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.os.Bundle;
import android.view.View;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.AnticipateInterpolator;
import android.view.animation.AnticipateOvershootInterpolator;
import android.view.animation.BounceInterpolator;
import android.view.animation.CycleInterpolator;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.LinearInterpolator;
import android.view.animation.OvershootInterpolator;
import android.widget.ImageView;

import com.example.complete.R;
import com.example.complete.base.BaseActivity;


public class InterpolatorActivity extends BaseActivity {
    ImageView ivPic;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_interpolator;
    }

    public void interpolatorImpl(View view) {
        //AccelerateDecelerateInterpolator 开始和结束比较慢,中间比较快
        //AccelerateInterpolator 越来越快
        //LinearInterpolator 匀速执行
        //DecelerateInterpolator 越来越慢
        //AnticipateInterpolator 动画反方向后退,再执行
        //AnticipateOvershootInterpolator 先后退执行一段,在运行到终点,然后超过终点继续向前运行一段,最后返回终点
        //OvershootInterpolator 动画匀速执行到终点,再向前执行一段,最后再返回终点
        //BounceInterpolator 弹跳效果
        //ivPic.animate().y(1300).setDuration(2000).setInterpolator(new CycleInterpolator(7));
        //CycleInterpolator 正弦曲线效果 先执行到终点,再返回原点,同样的轨迹反方向执行一次返回原点 参数代表每执行一次动画,这样的正选曲线执行几次
//        ObjectAnimator anim = ObjectAnimator.ofFloat(ivPic , "translationY" , 500 , 1000);
//        anim.setDuration(2000);
//        anim.setRepeatCount(0);
//        anim.setRepeatMode(ValueAnimator.REVERSE);
//        anim.setInterpolator(new CycleInterpolator(1));
//        anim.start();

        ivPic.animate().y(1300).setDuration(2000).setInterpolator(new GravityInterpolator());

    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }
}
