package com.example.complete.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.example.complete.R;

import java.util.List;

public class MyViewPagerAdapter extends BaseAdapter<Integer> {

    private Context mContext;

    private List<Integer> mDatas;

    public MyViewPagerAdapter(Context mContext, List<Integer> mDatas) {
        this.mContext = mContext;
        this.mDatas = mDatas;
    }

    @Override
    public int getCount() {
        return mDatas == null ? 0 : mDatas.size();
    }

    @Override
    public int getItemPositoin(Integer t) {
        int index = mDatas.indexOf(t);
        return index == -1 ? 0 : index;
    }

    @Override
    public Integer getItem(int position) {
        return position < 0 || position >= mDatas.size() ? null : mDatas.get(position);
    }

    public List<Integer> getData() {
        return mDatas;
    }



    public View getView(int position, ViewGroup parent) {
        if (parent == null || getCount() == 0) {
            return null;
        }
        View view = View.inflate(mContext , R.layout.item_guide , null);
        LinearLayout llBg = view.findViewById(R.id.ll_bg);
        llBg.setBackgroundColor(mDatas.get(position));
        return view;
    }
}
