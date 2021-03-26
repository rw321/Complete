package com.example.common.utils;

import android.content.Context;
import android.content.SharedPreferences;

import android.text.TextUtils;

import androidx.annotation.NonNull;

import com.example.common.app.BaseApp;

public class SPUtils {

    private SPUtils() {
        throw new RuntimeException("SPUtils can not new");
    }

    private static final String FILE_NAME = "share";
    private static final int SP_MODE = Context.MODE_PRIVATE;

    public static boolean put(@NonNull String key,@NonNull Object value) {
        SharedPreferences preferences = BaseApp.getAppContext().getSharedPreferences(FILE_NAME, SP_MODE);
        SharedPreferences.Editor edit = preferences.edit();
        if (value instanceof String) {
            if (!TextUtils.isEmpty((CharSequence) value)) {
                edit.putString(key, (String) value);
            }
        } else if (value instanceof Boolean) {
            edit.putBoolean(key, (Boolean) value);
        } else if (value instanceof Float) {
            edit.putFloat(key, (Float) value);
        } else if (value instanceof Integer) {
            edit.putInt(key, (Integer) value);
        } else if (value instanceof Long){
            edit.putLong(key, (Long) value);
        }
        boolean commit = edit.commit();
        return commit;
    }

    public static String getString(@NonNull String key) {
        SharedPreferences sharedPreferences = BaseApp.getAppContext().getSharedPreferences(FILE_NAME, SP_MODE);
        return sharedPreferences.getString(key, null);
    }

    public static int getInt(@NonNull String key) {
        SharedPreferences sharedPreferences = BaseApp.getAppContext().getSharedPreferences(FILE_NAME, SP_MODE);
        return sharedPreferences.getInt(key, -1);
    }

    public static long getLong(@NonNull String key) {
        SharedPreferences sharedPreferences = BaseApp.getAppContext().getSharedPreferences(FILE_NAME, SP_MODE);
        return sharedPreferences.getInt(key, -1);
    }

    public static boolean getBoolean(String key) {
        SharedPreferences sharedPreferences = BaseApp.getAppContext().getSharedPreferences(FILE_NAME, SP_MODE);
        return sharedPreferences.getBoolean(key, false);
    }

    public static void remove(String key) {
        SharedPreferences preferences = BaseApp.getAppContext().getSharedPreferences(FILE_NAME, SP_MODE);
        SharedPreferences.Editor edit = preferences.edit();
        edit.remove(key);
        edit.commit();
    }



}
