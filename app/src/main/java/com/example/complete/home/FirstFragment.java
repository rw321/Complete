package com.example.complete.home;

import android.content.Intent;
import android.os.Bundle;

import com.example.complete.R;
import com.example.complete.base.BaseFragment;
import com.example.complete.databinding.FragmentFirstBinding;
import com.example.complete.nested.NestedTestActivity;
import com.example.complete.ui.customview.DrawableTestActivity;
import com.example.complete.ui.customview.PercentTextActivity;
import com.example.complete.ui.customview.RecyclerViewActivity;


public class FirstFragment extends BaseFragment<FragmentFirstBinding> {

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_first;
    }

    @Override
    protected void initData() {
        super.initData();
        mContentBinding.setHandler(new HomeHandler());

        mContentBinding.tvNested.setOnClickListener((v -> startActivity(new Intent(mContext , NestedTestActivity.class))));
        mContentBinding.tvPercentText.setOnClickListener((v -> startActivity(
                new Intent(mContext , PercentTextActivity.class))));

        mContentBinding.tvDrawable.setOnClickListener((v -> startActivity(
                new Intent(mContext , DrawableTestActivity.class))));

        mContentBinding.tvRecycler.setOnClickListener((v -> startActivity(
                new Intent(mContext , RecyclerViewActivity.class))));

    }



    public static FirstFragment getInstance() {
        FirstFragment fragment = new FirstFragment();
        Bundle bundle = new Bundle();
        fragment.setArguments(bundle);
        return fragment;
    }

}
