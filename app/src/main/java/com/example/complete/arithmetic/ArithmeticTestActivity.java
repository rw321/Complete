package com.example.complete.arithmetic;

import com.example.complete.R;
import com.example.complete.base.BaseActivity;
import com.example.complete.databinding.ActivityArithmeticBinding;

/**
 * 算法学习
 */
public class ArithmeticTestActivity extends BaseActivity<ActivityArithmeticBinding> {
    @Override
    protected int getLayoutId() {
        return R.layout.activity_arithmetic;
    }

    @Override
    protected void initData() {
        super.initData();
        mContentBinding.setHandler(new ArithmeticHandler(mContentBinding));
    }


}
