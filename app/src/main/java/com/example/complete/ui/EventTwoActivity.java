package com.example.complete.ui;

import com.example.complete.R;
import com.example.complete.base.BaseActivity;
import com.example.complete.bean.EventTest;
import com.example.complete.utils.EventBusUtils;

import butterknife.OnClick;

public class EventTwoActivity extends BaseActivity {
    @Override
    protected int getLayoutId() {
        return R.layout.activity_event_two;
    }

    @OnClick(R.id.tv_send)
    public void send() {
        //EventBusUtils.sendStrickyEvent(new EventTest());
        EventBusUtils.sendEvent(new EventTest());
    }

}
