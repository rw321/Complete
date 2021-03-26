package com.example.complete.ui.thirdlib;

import android.content.Intent;

import com.example.complete.R;
import com.example.complete.base.BaseActivity;
import com.example.complete.ui.OKHttpActivity;
import com.example.complete.ui.RxJavaTestActivity;

public class ThirdLibTestActivity extends BaseActivity {
    @Override
    protected int getLayoutId() {
        return R.layout.activity_third_lib;
    }

    public void rxJava() {
        startActivity(new Intent(this , RxJavaTestActivity.class));
    }

    public void okhttp() {
        OKHttpActivity.startActivity(this);
    }

}
