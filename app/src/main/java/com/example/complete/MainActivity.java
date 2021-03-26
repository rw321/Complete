package com.example.complete;

import android.content.Context;
import android.graphics.Color;
import android.view.View;

import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import com.example.complete.base.BaseActivity;
import com.example.complete.base.ViewPagerAdapter;
import com.example.complete.databinding.ActivityMainBinding;
import com.example.complete.fragment.FirstFragment;
import com.example.complete.fragment.MeFragment;
import com.example.complete.fragment.SecondFragment;
import com.example.complete.fragment.ThirdFragment;

import net.lucode.hackware.magicindicator.buildins.commonnavigator.CommonNavigator;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.abs.CommonNavigatorAdapter;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.abs.IPagerIndicator;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.abs.IPagerTitleView;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.titles.ClipPagerTitleView;

import java.util.ArrayList;
import java.util.List;


public class MainActivity extends BaseActivity<ActivityMainBinding> {

    private String[] tabs = new String[]{"首页" , "第二页" , "第三页" , "我的"};

    @Override
    protected int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    protected void initData() {
        super.initData();
        initTab();
        List<Fragment> fragments = new ArrayList<>();
        fragments.add(FirstFragment.getInstance());
        fragments.add(SecondFragment.getInstance());
        fragments.add(ThirdFragment.getInstance());
        fragments.add(MeFragment.getInstance());
        mContentBinding.vpMain.setAdapter(new ViewPagerAdapter(getSupportFragmentManager() , fragments));

    }

    private void initTab(){
        CommonNavigator commonNavigator = new CommonNavigator(this);
        CommonNavigatorAdapter commonNavigatorAdapter = new CommonNavigatorAdapter() {
            @Override
            public int getCount() {
                return tabs.length;
            }

            @Override
            public IPagerTitleView getTitleView(Context context, int index) {
                ClipPagerTitleView clipPagerTitleView = new ClipPagerTitleView(context);

                clipPagerTitleView.setText(tabs[index]);
                clipPagerTitleView.setTextColor(Color.parseColor("#f2c4c4"));
                clipPagerTitleView.setClipColor(Color.BLACK);

                clipPagerTitleView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        mContentBinding.vpMain.setCurrentItem(index);
                    }
                });

                return clipPagerTitleView;
            }

            @Override
            public IPagerIndicator getIndicator(Context context) {
                return null;
            }
        };
        commonNavigator.setAdapter(commonNavigatorAdapter);
        mContentBinding.miMain.setNavigator(commonNavigator);
    }

    @Override
    protected void initListener() {
        super.initListener();
        mContentBinding.vpMain.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                mContentBinding.miMain.onPageScrolled(position , positionOffset , positionOffsetPixels);
            }

            @Override
            public void onPageSelected(int position) {
                mContentBinding.miMain.onPageSelected(position);
            }

            @Override
            public void onPageScrollStateChanged(int state) {
                mContentBinding.miMain.onPageScrollStateChanged(state);
            }
        });
    }
}
