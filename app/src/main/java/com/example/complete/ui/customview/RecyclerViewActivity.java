package com.example.complete.ui.customview;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.complete.R;
import com.example.complete.adapter.SuspendTestAdapter;
import com.example.complete.base.BaseActivity;
import com.example.complete.bean.Star;
import com.example.complete.databinding.ActivityRecyclerBinding;

import java.util.ArrayList;
import java.util.List;

public class RecyclerViewActivity extends BaseActivity<ActivityRecyclerBinding> {
    @Override
    protected int getLayoutId() {
        return R.layout.activity_recycler;
    }


    @Override
    protected void initData() {
        super.initData();

        List<Star> data = new ArrayList<>();
        for (int i =0; i < 4;i++) {
            for (int j = 0; j < 7;j++) {
                if (i % 2 == 0) {
                    data.add(new Star("何炅" + j , "快乐大本营"));
                }else {
                    data.add(new Star("汪涵" + j , "天天向上"));
                }
            }
        }

        mContentBinding.rvContent.setLayoutManager(new LinearLayoutManager(this
                , RecyclerView.VERTICAL , false));

        mContentBinding.rvContent.setAdapter(new SuspendTestAdapter(data));

    }
}
