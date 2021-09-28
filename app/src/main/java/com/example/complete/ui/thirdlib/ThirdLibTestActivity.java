package com.example.complete.ui.thirdlib;

import android.content.Intent;

import com.example.complete.R;
import com.example.complete.base.BaseActivity;
import com.example.complete.databinding.ActivityThirdLibBinding;
import com.example.complete.rxjava.RxJavaTestActivity;
import com.example.complete.ui.OKHttpActivity;

public class ThirdLibTestActivity extends BaseActivity<ActivityThirdLibBinding> {
    @Override
    protected int getLayoutId() {
        return R.layout.activity_third_lib;
    }

    @Override
    protected void initData() {
        super.initData();
        mContentBinding.tvRxjava.setOnClickListener(v -> rxJava());
    }

    public void rxJava() {
        startActivity(new Intent(this , RxJavaTestActivity.class));
    }

    public void okhttp() {
        OKHttpActivity.startActivity(this);
    }

}
