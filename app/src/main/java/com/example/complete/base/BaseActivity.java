package com.example.complete.base;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;

import com.example.complete.R;
import com.example.complete.databinding.LayoutBaseBinding;

public abstract class BaseActivity<DB extends ViewDataBinding> extends AppCompatActivity {

    Context mContext;

    protected DB mContentBinding;
    private LayoutBaseBinding rootDataBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mContext = this;
        rootDataBinding = DataBindingUtil.inflate(
                LayoutInflater.from(this),
                R.layout.layout_base, null, false);
        setContentView(rootDataBinding.getRoot());
        rootDataBinding.root.removeAllViews();
        if (getLayoutId() != 0) {
            mContentBinding = DataBindingUtil.inflate(LayoutInflater.from(this), getLayoutId(), null, false);
            if (mContentBinding != null)
                rootDataBinding.root.addView(mContentBinding.getRoot());
        }

        initData();
        initListener();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

    }

    protected abstract int getLayoutId();

    protected void initData() {

    }

    protected void initListener() {

    }

}
