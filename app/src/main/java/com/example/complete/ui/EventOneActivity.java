package com.example.complete.ui;

import android.content.Intent;

import com.example.common.utils.ToastUtils;
import com.example.complete.R;
import com.example.complete.base.BaseActivity;
import com.example.complete.bean.EventTest;
import com.example.complete.utils.EventBusUtils;

import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import butterknife.OnClick;

public class EventOneActivity extends BaseActivity {
    @Override
    protected int getLayoutId() {
        return R.layout.activity_event_one;
    }

    @OnClick(R.id.tv_test)
    public void test() {
        startActivity(new Intent(this , EventTwoActivity.class));
    }

    @OnClick(R.id.tv_register)
    public void register() {
        EventBusUtils.register(this);
    }

    @OnClick(R.id.tv_unregister)
    public void unregister() {
        EventBusUtils.unregister(this);
    }

//    @Subscribe(threadMode = ThreadMode.MAIN , sticky = true)
//    public void onEvent(EventTest event) {
//        ToastUtils.showCustomToast("receiver event");
//    }

    @Subscribe(threadMode = ThreadMode.POSTING , sticky = true , priority = 10)
    public void onEvent10(EventTest event) {
        System.out.println("receiver event 10");
    }

    @Subscribe(threadMode = ThreadMode.POSTING , sticky = true , priority = 20)
    public void onEvent20(EventTest event) {
        System.out.println("receiver event 20");
        //EventBusUtils.cancelEventIntercept(event);
    }

    @Override
    protected boolean isRegisterEventBus() {
        return true;
    }
}
