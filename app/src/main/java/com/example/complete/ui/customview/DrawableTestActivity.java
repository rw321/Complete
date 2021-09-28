package com.example.complete.ui.customview;

import com.example.complete.R;
import com.example.complete.base.BaseActivity;
import com.example.complete.databinding.ActivityDrawableBinding;
import com.example.complete.view.FishDrawable;

public class DrawableTestActivity extends BaseActivity<ActivityDrawableBinding> {
    @Override
    protected int getLayoutId() {
        return R.layout.activity_drawable;
    }

    @Override
    protected void initData() {
        super.initData();
        mContentBinding.ivFish.setImageDrawable(new FishDrawable());
    }
}
