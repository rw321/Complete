package com.example.complete.design.proxy;

import android.app.Activity;
import android.view.View;

import java.lang.annotation.Annotation;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class BindClickDeal {


    public void bind(Activity activity) {
        if (activity == null)
            return;
        Class<?> clazz = activity.getClass();
        Method[] methods = clazz.getDeclaredMethods();
        for (int i = 0 ; i < methods.length;i++){
            Method method1 = methods[i];
            Annotation[] annotations = method1.getAnnotations();
            for (int j = 0; j < annotations.length;j++) {
                Annotation annotation = annotations[j];
                if (annotation instanceof BindClick) {
                    int[] value = ((BindClick) annotation).value();
                    for (int l = 0; l < value.length;l++) {
                        View view = activity.findViewById(value[l]);
                        view.setOnClickListener((View.OnClickListener) Proxy.newProxyInstance(clazz.getClassLoader(), new Class[]{View.OnClickListener.class}, new InvocationHandler() {
                            @Override
                            public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                                method1.invoke(activity);
                                return null;
                            }
                        }));
                    }
                }
            }
        }
    }

    public void create(Class clazz){



    }

}
