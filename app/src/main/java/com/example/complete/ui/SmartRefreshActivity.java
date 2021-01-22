package com.example.complete.ui;

import android.bluetooth.BluetoothClass;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.NonNull;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.complete.R;
import com.example.complete.adapter.SimpleTextAdapter;
import com.example.complete.base.BaseActivity;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshLoadMoreListener;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SmartRefreshActivity extends BaseActivity {
    @BindView(R.id.rv_content)
    RecyclerView rvContent;
    @BindView(R.id.refreshLayout)
    SmartRefreshLayout refreshLayout;

    Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            refreshLayout.finishRefresh();
            refreshLayout.finishLoadMore();
        }
    };

    @Override
    protected int getLayoutId() {
        return R.layout.activity_smart_refresh;
    }

    @Override
    protected void initData() {
        super.initData();
        setTitle("智能刷新");
        rvContent.setLayoutManager(new LinearLayoutManager(this , LinearLayoutManager.VERTICAL , false));
        List<String> mDatas = new ArrayList<>();
        for (int i = 0; i < 20; i++) {
            mDatas.add("" + i);
        }
        SimpleTextAdapter mAdapter = new SimpleTextAdapter(this , mDatas);
        rvContent.setAdapter(mAdapter);
        rvContent.addItemDecoration(new DividerItemDecoration(this , DividerItemDecoration.VERTICAL));
        refreshLayout.setEnableRefresh(true);
        refreshLayout.setEnableLoadMore(true);
    }

    @Override
    protected void initListener() {
        super.initListener();
        refreshLayout.setOnRefreshLoadMoreListener(new OnRefreshLoadMoreListener() {
            @Override
            public void onLoadMore(@NonNull RefreshLayout refreshLayout) {
                handler.sendEmptyMessageDelayed(100 , 3000);
            }

            @Override
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {
                handler.sendEmptyMessageDelayed(100 , 3000);
            }
        });
    }
}
