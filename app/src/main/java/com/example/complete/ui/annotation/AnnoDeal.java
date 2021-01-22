package com.example.complete.ui.annotation;

import android.os.Handler;
import android.os.Looper;
import android.text.TextUtils;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class AnnoDeal {

    private static AnnoDeal instance;

    Handler handler = new Handler(Looper.getMainLooper());

    private AnnoDeal() {

    }

    public static AnnoDeal getInstance() {
        if (instance == null) {
            synchronized (AnnoDeal.class){
                instance = new AnnoDeal();
            }
        }
        return instance;
    }

    public void runOnUI(Object o , String methodName , Object...objects){
        if (o == null || TextUtils.isEmpty(methodName))
            return;
        handler.post(new Runnable() {
            @Override
            public void run() {
                for (Method method: o.getClass().getDeclaredMethods()) {
                    if (methodName.equals(method.getName())) {
                        try {
                            method.invoke(o , objects);
                        } catch (IllegalAccessException e) {
                            e.printStackTrace();
                        } catch (InvocationTargetException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        });
    }



}
