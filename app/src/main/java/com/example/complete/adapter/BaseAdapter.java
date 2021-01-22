package com.example.complete.adapter;

import android.database.DataSetObservable;
import android.database.DataSetObserver;
import android.view.View;
import android.view.ViewGroup;

public class BaseAdapter<T> implements IAdapter<T> {

    DataSetObservable observable = new DataSetObservable();

    @Override
    public void registerDataSetObserver(DataSetObserver observer) {
        observable.registerObserver(observer);
    }

    @Override
    public void unRegisterDataSetObserver(DataSetObserver observer) {
        observable.unregisterObserver(observer);
    }

    @Override
    public void notifyDataSetChanged() {
        observable.notifyChanged();
    }

    @Override
    public int getCount() {
        return 0;
    }

    @Override
    public int getItemPositoin(T t) {
        return 0;
    }

    @Override
    public T getItem(int position) {
        return null;
    }

    @Override
    public View getView(int position, View convertView , ViewGroup parent) {
        return null;
    }

    @Override
    public int getItemTypeCount() {
        return 0;
    }

    @Override
    public int getItemViewType(int position) {
        return 0;
    }
}
