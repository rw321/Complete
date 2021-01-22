package com.example.common.app;

import android.app.Application;
import android.content.Context;

public class BaseApp extends Application {

    private static Context mContext;

    @Override
    public void onCreate() {
        super.onCreate();
        mContext = getApplicationContext();

    }

    /**获取系统上下文：用于ToastUtil类*/
    public static Context getAppContext() {
        return mContext;
    }

}
