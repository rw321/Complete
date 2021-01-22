package com.example.complete.adapter;

import android.database.DataSetObservable;
import android.database.DataSetObserver;

public abstract class ObserverAdapter<T> extends Adapter<T> implements IObserver {

    DataSetObservable mDataSetObservable = new DataSetObservable();

    @Override
    public void registerDataSetObserver(DataSetObserver observer) {
        mDataSetObservable.registerObserver(observer);
    }

    @Override
    public void unRegisterDataSetObserver(DataSetObserver observer) {
        mDataSetObservable.unregisterObserver(observer);
    }

    @Override
    public void notifyDataSetChanged() {
        mDataSetObservable.notifyChanged();
    }
}
