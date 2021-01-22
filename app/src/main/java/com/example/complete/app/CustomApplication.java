package com.example.complete.app;

import com.alibaba.android.arouter.launcher.ARouter;
import com.example.common.app.BaseApp;

public class CustomApplication extends BaseApp {

    @Override
    public void onCreate() {
        super.onCreate();
        ARouter.openLog();
        ARouter.openDebug();
        ARouter.init(CustomApplication.this);
    }

    @Override
    public void onTerminate() {
        super.onTerminate();
        //ARouter.getInstance().destroy();
    }
}
