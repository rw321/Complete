package com.example.complete.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.complete.R;

import java.util.List;

public class ListAdapter extends BaseAdapter {

    private Context mContext;

    private List<Integer> mDatas;

    public ListAdapter(Context mContext, List<Integer> mDatas) {
        this.mContext = mContext;
        this.mDatas = mDatas;
    }

    @Override
    public int getCount() {
        return mDatas == null ? 0 : mDatas.size();
    }


    public View getView(int position, ViewGroup parent) {
        View view = View.inflate(mContext , R.layout.item_list , null);
        TextView tvContent = view.findViewById(R.id.tv_content);
        tvContent.setText(mDatas.get(position) + "");
        return view;
    }
}
