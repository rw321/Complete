package com.example.complete.ui.customview;

import android.graphics.Color;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;

import com.example.complete.R;
import com.example.complete.base.BaseActivity;

import java.util.Arrays;
import java.util.List;

public class TableActivity extends BaseActivity {

    private ViewPager pager;
    private TabLayout tabLayout;
    private List<ExcelFragment> list = Arrays.asList(new ExcelFragment(), new ExcelFragment(),
            new ExcelFragment(), new ExcelFragment(), new ExcelFragment());

    @Override
    protected int getLayoutId() {
        return R.layout.activity_table;
    }

    @Override
    protected void initData() {
        super.initData();
        pager = (ViewPager) findViewById(R.id.pager);
        tabLayout = (TabLayout) findViewById(R.id.tab_layout);
        pager.setAdapter(new ExcelPagerAdapter(getSupportFragmentManager()));
        tabLayout.setupWithViewPager(pager);
        tabLayout.setTabTextColors(Color.WHITE,Color.WHITE);
    }

    class ExcelPagerAdapter extends FragmentStatePagerAdapter {

        public ExcelPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            return list.get(position);
        }

        @Override
        public int getCount() {
            return list.size();
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return "Item:" + position;
        }
    }

}
