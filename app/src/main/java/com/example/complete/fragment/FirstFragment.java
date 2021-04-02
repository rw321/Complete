package com.example.complete.fragment;

import android.os.Bundle;

import com.example.complete.R;
import com.example.complete.base.BaseFragment;
import com.example.complete.databinding.FragmentFirstBinding;
import com.example.complete.home.HomeHandler;
import com.example.complete.home.UserInfo;

public class FirstFragment extends BaseFragment<FragmentFirstBinding> {
    @Override
    protected int getLayoutId() {
        return R.layout.fragment_first;
    }

    @Override
    protected void initData() {
        super.initData();
        mContentBinding.setHandler(new HomeHandler());
        UserInfo userInfo = new UserInfo("Alex");
        mContentBinding.setUserInfo(userInfo);
        userInfo.setUserName("任伟");
    }

    public static FirstFragment getInstance() {
        FirstFragment fragment = new FirstFragment();
        Bundle bundle = new Bundle();
        fragment.setArguments(bundle);
        return fragment;
    }

}
