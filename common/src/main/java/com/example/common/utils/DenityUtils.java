package com.example.common.utils;

import android.util.TypedValue;

import com.example.common.app.BaseApp;

public class DenityUtils {

    private DenityUtils() {
        throw new UnsupportedOperationException("cannot be instantiated");
    }

    /**
     * dp to px
     * @param dpValue
     * @return
     */
    public static int dp2px(float dpValue) {
        return (int) (TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dpValue, BaseApp.getAppContext().getResources().getDisplayMetrics()) + 0.5);
    }

    /**
     * px to dp
     * @param pxValue
     * @return
     */
    public static int px2dp(float pxValue){
        float scale = BaseApp.getAppContext().getResources().getDisplayMetrics().density;
        return (int)(pxValue / scale + 0.5f);
    }

    /**
     * sp to px
     * @param spValue
     * @return
     */
    public static int sp2px(float spValue){
        return (int) (TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP, spValue, BaseApp.getAppContext().getResources().getDisplayMetrics()) + 0.5);
    }

    /**
     * px to sp
     * @param pxValue
     * @return
     */
    public static int px2sp(float pxValue){
        float fontScale= BaseApp.getAppContext().getResources().getDisplayMetrics().scaledDensity;
        return (int) (pxValue / fontScale + 0.5f);
    }

}
