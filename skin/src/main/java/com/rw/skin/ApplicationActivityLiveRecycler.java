package com.rw.skin;

import android.app.Activity;
import android.app.Application;
import android.os.Build;
import android.os.Bundle;
import android.util.ArrayMap;
import android.view.LayoutInflater;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.core.view.LayoutInflaterCompat;

import com.rw.skin.utils.SkinTheme;

import java.lang.reflect.Field;
import java.util.Observable;

@RequiresApi(api = Build.VERSION_CODES.KITKAT)
public class ApplicationActivityLiveRecycler implements Application.ActivityLifecycleCallbacks {


    private Observable observable;
    private ArrayMap<Activity , SkinLayoutInflateFactory> mSkinFactories = new ArrayMap<>();

    public ApplicationActivityLiveRecycler(Observable observable) {
        this.observable = observable;
    }

    @Override
    public void onActivityCreated(@NonNull Activity activity, @Nullable Bundle savedInstanceState) {
        //在Activity的onCreate()中回调, 在setContentView之前调用

        /**
         *  更新状态栏
         */
        SkinTheme.updateStatusBarColor(activity);

        /**
         *  更新布局视图
         */
        //获得Activity的布局加载器
        LayoutInflater layoutInflater = activity.getLayoutInflater();

        try {
            //Android 布局加载器 使用 mFactorySet 标记是否设置过Factory
            //如设置过抛出一次
            //设置 mFactorySet 标签为false
            Field field = LayoutInflater.class.getDeclaredField("mFactorySet");
            field.setAccessible(true);
            field.setBoolean(layoutInflater, false);
        } catch (Exception e) {
            e.printStackTrace();
        }

        //使用factory2 设置布局加载工程
        SkinLayoutInflateFactory skinLayoutInflaterFactory = new SkinLayoutInflateFactory(activity);
        LayoutInflaterCompat.setFactory2(layoutInflater, skinLayoutInflaterFactory);
        mSkinFactories.put(activity, skinLayoutInflaterFactory);

        observable.addObserver(skinLayoutInflaterFactory);

    }

    @Override
    public void onActivityStarted(@NonNull Activity activity) {

    }

    @Override
    public void onActivityResumed(@NonNull Activity activity) {

    }

    @Override
    public void onActivityPaused(@NonNull Activity activity) {

    }

    @Override
    public void onActivityStopped(@NonNull Activity activity) {

    }

    @Override
    public void onActivitySaveInstanceState(@NonNull Activity activity, @NonNull Bundle outState) {

    }

    @Override
    public void onActivityDestroyed(@NonNull Activity activity) {
        SkinLayoutInflateFactory factory = mSkinFactories.remove(activity);
        SkinManager.getInstance().deleteObserver(factory);
    }
}
