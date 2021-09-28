package com.rw.skin;

import android.app.Activity;
import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.rw.skin.utils.SkinTheme;

import java.lang.reflect.Constructor;
import java.util.HashMap;
import java.util.Observable;
import java.util.Observer;

public class SkinLayoutInflateFactory implements LayoutInflater.Factory2 , Observer {

    private static final String[] mClassPrefixList = {
            "android.widget.",
            "android.webkit.",
            "android.app.",
            "android.view."
    };

    private static final Class[] mConstructorSignature = {
            Context.class , AttributeSet.class
    };

    private static final HashMap<String , Constructor<? extends View>>
        mConstructorMap = new HashMap<>();


    private SkinAttr mSkinAttr;
    private Activity activity;

    public SkinLayoutInflateFactory(Activity activity) {
        this.mSkinAttr = new SkinAttr();
        this.activity = activity;
    }

    @Nullable
    @Override
    public View onCreateView(@Nullable View parent, @NonNull String name, @NonNull Context context, @NonNull AttributeSet attrs) {
        View view = createSdkView(name , context , attrs);
        if (view == null)
            createView(name , context , attrs);
        if (view != null)
            mSkinAttr.look(view , attrs);
        return view;
    }

    private View createSdkView(String name , Context context , AttributeSet set) {
        if (name.indexOf('.')!= -1){
            return null;
        }
        for (String prefixStr : mClassPrefixList) {
            View view = createView(prefixStr + name , context , set);
            if (view != null)
                return view;
        }
        return null;
    }

    private View createView(String name , Context context , AttributeSet set) {
        Constructor<? extends View> constructor = findConstructor(context , name);
        if (constructor == null)
            return null;

        try {
            return constructor.newInstance(constructor , set);
        } catch (Exception e) {

        }

        return null;

    }


    private Constructor<? extends View> findConstructor(Context context, String name) {
        Constructor<? extends View> constructor = mConstructorMap.get(name);
        if (constructor == null) {
            try {
                Class<? extends View> clazz = context.getClassLoader().loadClass
                        (name).asSubclass(View.class);
                constructor = clazz.getConstructor(mConstructorSignature);
                mConstructorMap.put(name, constructor);
            } catch (Exception e) {
            }
        }
        return constructor;
    }




    @Nullable
    @Override
    public View onCreateView(@NonNull String name, @NonNull Context context, @NonNull AttributeSet attrs) {
        return null;
    }

    @Override
    public void update(Observable o, Object arg) {
        SkinTheme.updateStatusBarColor(activity);
        mSkinAttr.applySkin();

    }
}
