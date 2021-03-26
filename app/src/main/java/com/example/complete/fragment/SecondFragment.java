package com.example.complete.fragment;

import android.os.Bundle;

import com.example.complete.R;
import com.example.complete.base.BaseFragment;

public class SecondFragment extends BaseFragment {
    @Override
    protected int getLayoutId() {
        return R.layout.fragment_second;
    }

    public static SecondFragment getInstance(){
        SecondFragment fragment = new SecondFragment();
        Bundle bundle = new Bundle();
        fragment.setArguments(bundle);
        return fragment;
    }

}
