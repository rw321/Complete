package com.example.complete.fragment;

import android.os.Bundle;

import com.example.complete.R;
import com.example.complete.base.BaseFragment;

public class MeFragment extends BaseFragment {
    @Override
    protected int getLayoutId() {
        return R.layout.fragment_me;
    }

    public static MeFragment getInstance() {
        MeFragment fragment = new MeFragment();
        Bundle bundle = new Bundle();
        fragment.setArguments(bundle);
        return fragment;
    }

}
