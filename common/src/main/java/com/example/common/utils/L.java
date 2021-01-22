package com.example.common.utils;

import android.util.Log;

public class L {

    private static final Boolean isOpen=true;
    private static final String TAG = "RW";

    private L() {
        throw new UnsupportedOperationException("cannot be instantiated");
    }

    //普通版
    public static void d(String msg) {
        //打开日志
        if(isOpen){
            Log.d(TAG , msg);
        }else {
            //关闭日志
        }
    }

    //进行String类型转换，所以我们可以随意传入任意类型的参数
    public static void d(Object msg) {
        if (isOpen) {
            //打开日志
            String string = msg.toString();
            Log.d(TAG , string);
        }else {
            //关闭日志
        }

    }

}
