package com.example.complete.animation;

import android.view.View;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.RotateAnimation;
import android.view.animation.ScaleAnimation;
import android.view.animation.TranslateAnimation;
import android.widget.ImageView;

import com.example.common.utils.ToastUtils;
import com.example.complete.R;
import com.example.complete.base.BaseActivity;


/**
 * 补间动画测试
 */
public class TweenAnimActivity extends BaseActivity {
    ImageView ivPic;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_tween_anim;
    }

    public void translateXML(View view) {
        ivPic.startAnimation(AnimationUtils.loadAnimation(this , R.anim.translate_anim));

    }

    public void translateCode(View view) {
        TranslateAnimation animation = new TranslateAnimation(0 , 100 , 0 , 100);
        animation.setDuration(1000);  //动画执行时间 单位是ms
        animation.setRepeatCount(1);  //动画执行次数 n+1 默认是0就是执行1次
        animation.setRepeatMode(Animation.REVERSE); //动画执行次数>1时,重复方式,是RESTART重复执行还是REVERSE翻转执行
        animation.setFillAfter(true);  //是否保留动画执行结束时的状态
        animation.setStartOffset(1000);
        animation.setInterpolator(new AccelerateDecelerateInterpolator());
        ivPic.startAnimation(animation);
    }

    public void scaleXML(View view) {
        ivPic.startAnimation(AnimationUtils.loadAnimation(this , R.anim.scale_anim));
    }

    public void scaleCode(View view) {
        ScaleAnimation animation = new ScaleAnimation(1 , 0 , 1 , 0 ,Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
        animation.setDuration(1000);
        ivPic.startAnimation(animation);
    }

    public void alphaXML(View view) {
        ivPic.startAnimation(AnimationUtils.loadAnimation(this , R.anim.alpha_anim));
    }

    public void alphaCode(View view) {
        AlphaAnimation animation = new AlphaAnimation(1 , 0);
        animation.setDuration(1000);
        ivPic.startAnimation(animation);
    }

    public void rotateXML(View view) {
        ivPic.startAnimation(AnimationUtils.loadAnimation(this , R.anim.rotate_anim));
    }

    public void rotateCode(View view) {
        RotateAnimation animation = new RotateAnimation(0 , 360 ,
                Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
        animation.setDuration(1000);
        ivPic.startAnimation(animation);
        animation.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {

            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
    }

    public void clickPic(View view) {
        ToastUtils.showCustomToast("点击图片");
    }
}
