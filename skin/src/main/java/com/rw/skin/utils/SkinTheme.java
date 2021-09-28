package com.rw.skin.utils;

import android.app.Activity;
import android.content.Context;
import android.content.res.TypedArray;
import android.os.Build;

public class SkinTheme {

    private static int [] APPCOMPAT_COLOR_PRIMARY_DAR_ATTRS = {
            androidx.appcompat.R.attr.colorPrimaryDark
    };

    private static int[] STATUSBAR_COLOR_ATTRS ={
            androidx.appcompat.R.attr.statusBarBackground,
    };

    public static int[] getResId(Context context , int [] attrs){
        int []resId = new int[attrs.length];
        TypedArray typedArray = context.obtainStyledAttributes(attrs);
        for (int i = 0;i < attrs.length ;i++) {
            resId[i] = typedArray.getResourceId(i , 0);
        }
        typedArray.recycle();
        return resId;
    }

    public static void updateStatusBarColor(Activity activity) {
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.LOLLIPOP) {
            return;
        }

        int[] resIds = getResId(activity, STATUSBAR_COLOR_ATTRS);
        int statusBarColorResId = resIds[0];

        if (statusBarColorResId != 0) {
            int color = SkinResource.getInstance().getColor(statusBarColorResId);
            activity.getWindow().setStatusBarColor(color);
        } else {
            //获得 colorPrimaryDark
            int colorPrimaryDarkResId = getResId(activity, APPCOMPAT_COLOR_PRIMARY_DAR_ATTRS)[0];
            if (colorPrimaryDarkResId != 0) {
                int color = SkinResource.getInstance().getColor(colorPrimaryDarkResId);
                activity.getWindow().setStatusBarColor(color);
            }
        }

    }

}
