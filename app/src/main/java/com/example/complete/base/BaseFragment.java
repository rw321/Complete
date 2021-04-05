package com.example.complete.base;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import androidx.fragment.app.Fragment;

import com.example.complete.R;
import com.example.complete.databinding.LayoutBaseBinding;

public abstract class BaseFragment<DB extends ViewDataBinding> extends Fragment {

    protected DB mContentBinding;
    private LayoutBaseBinding rootDataBinding;
    private Context mContext;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mContext = container.getContext();
        rootDataBinding = DataBindingUtil.inflate(inflater, R.layout.layout_base, container, false);

        rootDataBinding.root.removeAllViews();
        if (getLayoutId() != 0) {
            mContentBinding = DataBindingUtil.inflate(LayoutInflater.from(container.getContext()), getLayoutId(), null, false);
            if (mContentBinding != null)
                rootDataBinding.root.addView(mContentBinding.getRoot());
        }

        initData();
        return rootDataBinding.getRoot();
    }

    protected abstract int getLayoutId();

    protected void initData(){

    }


}
