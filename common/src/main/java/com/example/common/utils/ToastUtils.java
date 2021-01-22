package com.example.common.utils;

import android.content.Context;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.common.R;
import com.example.common.app.BaseApp;

public class ToastUtils {

    private static Toast toast;

    private static Toast customToast;

    private ToastUtils() {
        throw new UnsupportedOperationException("cannot be instantiated");
    }

    /**
     * toast
     * @param text
     */
    public static void show(String text) {

        if (BaseApp.getAppContext() == null) {
            return;
        }

        if (customToast != null) {
            customToast.cancel();
        }

        if (toast ==null) {
            toast = Toast.makeText(BaseApp.getAppContext(), text, Toast.LENGTH_SHORT);
        }else {
            toast.setText(text);
        }
        toast.setGravity(Gravity.BOTTOM, 0, DenityUtils.dp2px(64));
        toast.show();
    }

    /**
     * toast at location
     * @param text
     * @param gravity
     */
    public static void showAtLocation(String text , int gravity , int offsetX , int offsetY) {

        if (BaseApp.getAppContext() == null) {
            return;
        }

        if (customToast != null) {
            customToast.cancel();
        }

        if (toast ==null) {
            toast = Toast.makeText(BaseApp.getAppContext(), text, Toast.LENGTH_SHORT);
        }else {
            toast.setText(text);
        }
        toast.setGravity(gravity, offsetX, offsetY);
        toast.show();
    }

    /**
     * custom view toast
     * @param text
     */
    public static void showCustomToast(String text) {

        if (BaseApp.getAppContext() == null) {
            return;
        }

        if (toast != null) {
            toast.cancel();
        }

        LayoutInflater inflater = LayoutInflater.from(BaseApp.getAppContext());
        View toastView = inflater.inflate(R.layout.layout_toast, null);   // 用布局解析器来解析一个布局去适配Toast的setView方法
        TextView tvText = toastView.findViewById(R.id.tv_text);
        tvText.setText(text);

        if (customToast == null) {
            customToast = new Toast(BaseApp.getAppContext());
        }
        customToast.setView(toastView);
        customToast.setDuration(Toast.LENGTH_SHORT);
        customToast.show();
    }

    /**
     * custom view toast
     * @param toastView
     */
    public static void showCustomToast(View toastView) {

        if (BaseApp.getAppContext() == null) {
            return;
        }

        if (toast != null) {
            toast.cancel();
        }

        if (customToast == null) {
            customToast = new Toast(BaseApp.getAppContext());
        }
        customToast.setView(toastView);
        customToast.setDuration(Toast.LENGTH_SHORT);
        customToast.show();
    }

}
