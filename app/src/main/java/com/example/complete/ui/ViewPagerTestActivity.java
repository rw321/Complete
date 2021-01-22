package com.example.complete.ui;

import android.os.Handler;
import android.os.Message;
import android.support.v4.view.ViewPager;

import com.example.complete.R;
import com.example.complete.adapter.ViewPagerTestAdapter;
import com.example.complete.base.BaseActivity;
import com.example.complete.view.ScaleTransformer;

import java.security.KeyStore;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

public class ViewPagerTestActivity extends BaseActivity {

    private static final int FREQUENCY_VIEW_PAGER = 3000;

    @BindView(R.id.viewPager)
    ViewPager viewPager;

    private int currentPosition;
    private boolean enableScroll = true;
    private List<Integer> list;

    Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if (msg.what == FREQUENCY_VIEW_PAGER) {
                if (enableScroll) {
                    currentPosition = viewPager.getCurrentItem();
                    currentPosition++;
                    //不需要为了循环轮播来判断是否到达最后一页，在监听器中已经为我们做了此操作
                    viewPager.setCurrentItem(currentPosition);
                }
                handler.sendEmptyMessageDelayed(FREQUENCY_VIEW_PAGER , FREQUENCY_VIEW_PAGER);
            }
        }
    };

    @Override
    protected int getLayoutId() {
        return R.layout.activity_viewpager;
    }

    @Override
    protected void initData() {
        super.initData();
        viewPager.setPageMargin(80);
        viewPager.setOffscreenPageLimit(3);
        viewPager.setPageTransformer(false , new ScaleTransformer());
        list = new ArrayList<>();
        list.add(1);
        list.add(1);
        list.add(1);
        list.add(1);
        list.add(1);
        ViewPagerTestAdapter adater = new ViewPagerTestAdapter(list, this);
        viewPager.setAdapter(adater);

        currentPosition = 1;
        viewPager.setCurrentItem(currentPosition);
        handler.sendEmptyMessageDelayed(FREQUENCY_VIEW_PAGER , FREQUENCY_VIEW_PAGER);
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int i, float v, int i1) {
            }

            @Override
            public void onPageSelected(int i) {
                currentPosition = i;
            }

            @Override
            public void onPageScrollStateChanged(int state) {
                //验证当前的滑动是否结束
                if (state == ViewPager.SCROLL_STATE_IDLE) {
                    if (currentPosition == 0) {
                        viewPager.setCurrentItem(list.size() - 2, false);//切换，不要动画效果
                    } else if (currentPosition == list.size() - 1) {
                        viewPager.setCurrentItem(1, false);//切换，不要动画效果
                    }
                    enableScroll = true;
                }else {
                    enableScroll = false;
                }
            }
        });

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (handler != null) {
            handler.removeCallbacksAndMessages(null);
            handler = null;
        }
    }
}
