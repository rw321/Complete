package com.example.complete.adapter;

import android.database.DataSetObserver;
import android.view.View;
import android.view.ViewGroup;

public interface IAdapter<T> {

    void registerDataSetObserver(DataSetObserver observer);

    void unRegisterDataSetObserver(DataSetObserver observer);

    void notifyDataSetChanged();

    int getCount();

    int getItemPositoin(T t);

    T getItem(int position);

    View getView(int position,View convertView, ViewGroup parent);

    int getItemTypeCount();

    int getItemViewType(int position);

}
