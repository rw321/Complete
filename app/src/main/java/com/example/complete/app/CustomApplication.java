package com.example.complete.app;

import com.example.common.app.BaseApp;
import com.rw.skin.SkinManager;

public class CustomApplication extends BaseApp {

    @Override
    public void onCreate() {
        super.onCreate();
        SkinManager.getInstance().init(this);
    }

    @Override
    public void onTerminate() {
        super.onTerminate();
    }
}
