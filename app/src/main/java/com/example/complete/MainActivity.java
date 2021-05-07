package com.example.complete;

import android.content.Context;
import android.graphics.Color;
import android.view.View;

import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

import com.example.common.utils.FragmentUtils;
import com.example.complete.base.BaseActivity;
import com.example.complete.databinding.ActivityMainBinding;
import com.example.complete.home.FirstFragment;
import com.example.complete.fragment.MeFragment;
import com.example.complete.second.SecondFragment;
import com.example.complete.third.ThirdFragment;

import net.lucode.hackware.magicindicator.buildins.commonnavigator.CommonNavigator;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.abs.CommonNavigatorAdapter;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.abs.IPagerIndicator;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.abs.IPagerTitleView;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.titles.ClipPagerTitleView;

import java.util.ArrayList;
import java.util.List;


public class MainActivity extends BaseActivity<ActivityMainBinding> {

    private String[] tabs = new String[]{"首页" , "第二页" , "第三页" , "我的"};
    private List<Fragment> fragments;
    private int mCurrFragmentIndex;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    protected void initData() {
        super.initData();
        initTab();
        fragments = new ArrayList<>();
        fragments.add(FirstFragment.getInstance());
        fragments.add(SecondFragment.getInstance());
        fragments.add(ThirdFragment.getInstance());
        fragments.add(MeFragment.getInstance());
        showFragment(0);
    }

    private void showFragment(int index) {
        Fragment fragment = fragments.get(mCurrFragmentIndex);
        if (fragment.isAdded()) {
            FragmentUtils.hideFragment(getSupportFragmentManager() , fragment);
        }
        mCurrFragmentIndex = index;
        fragment = fragments.get(mCurrFragmentIndex);
        if (fragment.isAdded())
            FragmentUtils.showFragment(getSupportFragmentManager() , fragment);
        else
            FragmentUtils.addFragment(getSupportFragmentManager() , R.id.fl_container , fragment);
    }

    private void initTab(){
        CommonNavigator commonNavigator = new CommonNavigator(this);
        commonNavigator.setAdjustMode(true);
        CommonNavigatorAdapter commonNavigatorAdapter = new CommonNavigatorAdapter() {
            @Override
            public int getCount() {
                return tabs.length;
            }

            @Override
            public IPagerTitleView getTitleView(Context context, int index) {
                ClipPagerTitleView clipPagerTitleView = new ClipPagerTitleView(context);

                clipPagerTitleView.setText(tabs[index]);
                clipPagerTitleView.setTextColor(ContextCompat.getColor(MainActivity.this , R.color.color_999));
                clipPagerTitleView.setClipColor(Color.BLACK);

                clipPagerTitleView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        //mContentBinding.vpMain.setCurrentItem(index);
                        mContentBinding.miMain.onPageSelected(index);
                        mContentBinding.miMain.onPageScrolled(index , 0 , 0);
                        showFragment(index);

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
        //ViewPagerHelper.bind(mContentBinding.miMain , mContentBinding.vpMain);
    }
}
