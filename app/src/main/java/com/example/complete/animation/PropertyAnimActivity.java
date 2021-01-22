package com.example.complete.animation;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.os.Bundle;
import android.view.View;
import android.view.animation.BounceInterpolator;
import android.widget.ImageView;

import com.example.complete.R;
import com.example.complete.base.BaseActivity;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 属性动画没有setFillAfter , 因为动画执行过程中,控件属性已经真实改变,控件已经不在初始状态了,所以没必要在恢复到初始状态
 */
public class PropertyAnimActivity extends BaseActivity {
    @BindView(R.id.iv_pic)
    ImageView ivPic;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_property_anim;
    }

    public void valueImpl(View view) {
        ValueAnimator anim = ValueAnimator.ofFloat(1.0f, 0.0f);
        anim.setDuration(3000);
        anim.setRepeatCount(2);   //从0开始执行n+1次
        anim.setRepeatMode(ValueAnimator.REVERSE);  //动画重复模式 重复or翻转
        anim.setStartDelay(1000);   //延时执行
        anim.start();
        anim.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                ivPic.setAlpha((Float) animation.getAnimatedValue());
            }
        });
    }

    /**
     * 如果动画只传终止状态而没有初始状态的话,起始状态就是当前状态,这样动画只会执行一次,因为执行一次之后控件的当前状态就是终止状态,这样起始和终止相同,反映到Ui上就是没有变化了
     * @param view
     */
    public void objectAnimImpl(View view) {
        //传负值控件的透明度还是0,直到动画过渡到0状态之后才开始在UI上反映出来
        //ObjectAnimator anim = ObjectAnimator.ofFloat(ivPic , "alpha" , -1.0f , 1.0f);

        //rotation 沿着Z轴旋转,即围绕控件中心做二维平面上的旋转
        //rotationX 沿着X轴旋转,即围绕控件水平中轴线做三维平面上的旋转
        //rotationY 沿着Y轴旋转,即围绕控件垂直中轴线做三维平面上的旋转
        //ObjectAnimator anim = ObjectAnimator.ofFloat(ivPic , "rotationX" ,90 , 360); //负数是逆时针旋转 正数是顺时针旋转

        //translationX在X轴方向的位移 正值是向右位移,负值是向左移动
        //translationY在Y轴方向的位移 正值是向上位移,负值是向下移动
        //ObjectAnimator anim = ObjectAnimator.ofFloat(ivPic , "translationX" ,0 , -200);

        //scaleX属性改变的是控件的宽度,随着动画的执行,控件的宽度在不断的变化
        //scaleY属性改变的是控件的高度
        ObjectAnimator anim = ObjectAnimator.ofFloat(ivPic , "scaleY" ,1 , 0 , 1);
        anim.setDuration(3000);
        anim.start();

        anim.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {

            }

            @Override
            public void onAnimationEnd(Animator animation) {

            }

            @Override
            public void onAnimationCancel(Animator animation) {

            }

            @Override
            public void onAnimationRepeat(Animator animation) {

            }
        });

        anim.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                super.onAnimationEnd(animation);
            }

            @Override
            public void onAnimationStart(Animator animation) {
                super.onAnimationStart(animation);
            }
        });

    }

    public void animSetImpl(View view) {
        AnimatorSet set = new AnimatorSet();
        ObjectAnimator anim1 = ObjectAnimator.ofFloat(ivPic , "translationX" ,0 , 200);
        ObjectAnimator anim2 = ObjectAnimator.ofFloat(ivPic , "translationY" ,0 , 200);
        ObjectAnimator anim3 = ObjectAnimator.ofFloat(ivPic , "translationX" ,200 , 0);
        //set.play(anim1).with(anim2);  //两个动画同时执行
        //set.play(anim1).after(anim2);   //anim1先执行
        //set.play(anim1).before(anim2);   //anim2先执行
        set.play(anim1).before(anim2).before(anim3);   //anim1先执行 anim2和anim3之后同时执行 ,没有实现顺序调用,不知道原因,所以不建议这样链式调用
        // ,如果要实现多个动画联动,最好用AnimatorSet重复嵌套
        set.setDuration(1000);
        set.start();

    }

    public void viewPropertyAnim(View view) {
        //y值是正值是向上位移 组合动画是同时执行无先后顺序
        ivPic.animate().alpha(0.5f).x(200).y(200).setDuration(5000).setInterpolator(new BounceInterpolator());
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }
}
