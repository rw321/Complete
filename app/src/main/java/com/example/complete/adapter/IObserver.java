package com.example.complete.adapter;

import android.database.DataSetObserver;

public interface IObserver {

    void registerDataSetObserver(DataSetObserver observer);

    void unRegisterDataSetObserver(DataSetObserver observer);

    void notifyDataSetChanged();

}
