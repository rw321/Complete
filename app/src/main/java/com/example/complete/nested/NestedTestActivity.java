package com.example.complete.nested;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.complete.R;
import com.example.complete.base.BaseActivity;
import com.example.complete.databinding.ActivityNestedBinding;

import java.util.ArrayList;
import java.util.List;

public class NestedTestActivity extends BaseActivity<ActivityNestedBinding> {

    @Override
    protected int getLayoutId() {
        return R.layout.activity_nested;
    }

    @Override
    protected void initData() {
        super.initData();

        mContentBinding.rvContent.setFocusableInTouchMode(false);
        mContentBinding.rvContent.requestFocus();

        mContentBinding.rvContent.setLayoutManager(new LinearLayoutManager(this , RecyclerView.VERTICAL , false));

        List<String> data = new ArrayList<>();
        for (int i = 0 ; i < 20; i++) {
            data.add("第" + (i+1) + "条数据");
        }

        mContentBinding.rvContent.setAdapter(new NestedTestAdapter(data));
    }
}
