package com.example.common.utils;

import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;

import com.example.common.app.BaseApp;

public class AppUtils {

    private AppUtils() {
        throw new UnsupportedOperationException("cannot be instantiated");
    }

    /**
     * 获取应用程序名称
     * @return
     */
    public static synchronized String getAppName() {
        try {
            PackageManager packageManager = BaseApp.getAppContext().getPackageManager();
            PackageInfo packageInfo = packageManager.getPackageInfo(
                    BaseApp.getAppContext().getPackageName(), 0);
            int labelRes = packageInfo.applicationInfo.labelRes;
            return BaseApp.getAppContext().getResources().getString(labelRes);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * [获取应用程序版本名]
     * @return
     */
    public static synchronized String getVersionName() {
        try {
            PackageManager packageManager = BaseApp.getAppContext().getPackageManager();
            PackageInfo packageInfo = packageManager.getPackageInfo(
                    BaseApp.getAppContext().getPackageName(), 0);
            return packageInfo.versionName;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }


    /**
     * [获取应用程序版本号]
     * @return
     */
    public static synchronized int getVersionCode() {
        try {
            PackageManager packageManager = BaseApp.getAppContext().getPackageManager();
            PackageInfo packageInfo = packageManager.getPackageInfo(
                    BaseApp.getAppContext().getPackageName(), 0);
            return packageInfo.versionCode;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }


    /**
     * [获取应用程序包名]
     * @return
     */
    public static synchronized String getPackageName() {
        try {
            PackageManager packageManager = BaseApp.getAppContext().getPackageManager();
            PackageInfo packageInfo = packageManager.getPackageInfo(
                    BaseApp.getAppContext().getPackageName(), 0);
            return packageInfo.packageName;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }


    /**
     * 获取图标 bitmap
     * @return
     */
    public static synchronized Bitmap getBitmap() {
        PackageManager packageManager = null;
        ApplicationInfo applicationInfo = null;
        try {
            packageManager = BaseApp.getAppContext().getApplicationContext()
                    .getPackageManager();
            applicationInfo = packageManager.getApplicationInfo(
                    BaseApp.getAppContext().getPackageName(), 0);
        } catch (PackageManager.NameNotFoundException e) {
            applicationInfo = null;
        }
        Drawable d = packageManager.getApplicationIcon(applicationInfo); //xxx根据自己的情况获取drawable
        BitmapDrawable bd = (BitmapDrawable) d;
        Bitmap bm = bd.getBitmap();
        return bm;
    }


}
