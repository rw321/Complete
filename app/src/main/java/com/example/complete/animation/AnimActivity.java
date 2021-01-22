package com.example.complete.animation;

import android.content.Intent;
import android.view.View;

import com.example.common.utils.ToastUtils;
import com.example.complete.R;
import com.example.complete.base.BaseActivity;

/**
 * 动画测试
 */
public class AnimActivity extends BaseActivity {

    @Override
    protected int getLayoutId() {
        return R.layout.activity_anim;
    }

    public void anim1(View view) {
        startActivity(new Intent(this , TweenAnimActivity.class));
    }

    public void anim2(View view) {
        startActivity(new Intent(this , PropertyAnimActivity.class));
    }

    public void anim3(View view) {
        startActivity(new Intent(this , InterpolatorActivity.class));
    }

}
