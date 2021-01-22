package com.example.complete.base;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.alibaba.android.arouter.launcher.ARouter;
import com.example.complete.utils.EventBusUtils;

import butterknife.ButterKnife;

public abstract class BaseActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutId());
        ARouter.getInstance().inject(this);
        ButterKnife.bind(this);
        if (isRegisterEventBus()) {
            EventBusUtils.register(this);
        }
        initData();
        initListener();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (isRegisterEventBus()) {
            EventBusUtils.unregister(this);
        }
    }

    protected abstract int getLayoutId();

    protected void initData() {

    }

    protected void initListener() {

    }

    protected boolean isRegisterEventBus() {
        return false;
    }

}
