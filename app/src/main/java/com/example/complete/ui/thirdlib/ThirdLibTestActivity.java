package com.example.complete.ui.thirdlib;

import android.content.Intent;

import com.example.complete.R;
import com.example.complete.base.BaseActivity;
import com.example.complete.ui.EventOneActivity;
import com.example.complete.ui.OKHttpActivity;
import com.example.complete.ui.RxJavaTestActivity;

import butterknife.OnClick;

public class ThirdLibTestActivity extends BaseActivity {
    @Override
    protected int getLayoutId() {
        return R.layout.activity_third_lib;
    }

    @OnClick(R.id.tv_eventbus)
    public void eventBus() {
        startActivity(new Intent(this , EventOneActivity.class));
    }

    @OnClick(R.id.tv_rxjava)
    public void rxJava() {
        startActivity(new Intent(this , RxJavaTestActivity.class));
    }

    @OnClick(R.id.tv_okhttp)
    public void okhttp() {
        OKHttpActivity.startActivity(this);
    }

}
