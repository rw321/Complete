package com.example.complete.fragment;

import android.os.Bundle;

import com.example.complete.R;
import com.example.complete.base.BaseFragment;

public class ThirdFragment extends BaseFragment {
    @Override
    protected int getLayoutId() {
        return R.layout.fragment_third;
    }

    public static ThirdFragment getInstance() {
        ThirdFragment fragment = new ThirdFragment();
        Bundle bundle = new Bundle();
        fragment.setArguments(bundle);
        return fragment;
    }

}
