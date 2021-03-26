package com.example.complete.fragment;

import android.os.Bundle;

import com.example.complete.R;
import com.example.complete.base.BaseFragment;

public class FirstFragment extends BaseFragment {
    @Override
    protected int getLayoutId() {
        return R.layout.fragment_first;
    }

    public static FirstFragment getInstance() {
        FirstFragment fragment = new FirstFragment();
        Bundle bundle = new Bundle();
        fragment.setArguments(bundle);
        return fragment;
    }

}
