package com.example.complete.second;

import android.os.Bundle;

import com.example.complete.R;
import com.example.complete.base.BaseFragment;
import com.example.complete.databinding.FragmentSecondBinding;

public class SecondFragment extends BaseFragment<FragmentSecondBinding> {
    @Override
    protected int getLayoutId() {
        return R.layout.fragment_second;
    }

    @Override
    protected void initData() {
        super.initData();
        mContentBinding.setHandler(new SecondHandler());
    }

    public static SecondFragment getInstance(){
        SecondFragment fragment = new SecondFragment();
        Bundle bundle = new Bundle();
        fragment.setArguments(bundle);
        return fragment;
    }

}
