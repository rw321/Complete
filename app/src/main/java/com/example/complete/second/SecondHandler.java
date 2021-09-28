package com.example.complete.second;

import android.content.Intent;
import android.view.View;

import com.example.complete.annotation.AnnotationTestActivity;
import com.example.complete.arithmetic.ArithmeticTestActivity;
import com.example.complete.generic.GenericTestActivity;
import com.example.complete.design.StyleModuleActivity;
import com.example.complete.thread.ThreadTestActivity;
import com.example.complete.ui.thirdlib.ThirdLibTestActivity;

public class SecondHandler {

    /**
     * 泛型
     * @param view
     */
    public void skipToGenaric(View view) {
        view.getContext().startActivity(new Intent(view.getContext(), GenericTestActivity.class));
    }

    /**
     * 注解
     * @param view
     */
    public void skipToAnnotation(View view) {
        Intent intent = new Intent(view.getContext(), AnnotationTestActivity.class);
        intent.putExtra("name" , "Alex");
        intent.putExtra("age" , 18);
        view.getContext().startActivity(intent);

    }

    /**
     * 设计模式学习
     * @param view
     */
    public void skipToDesignModel(View view){
        StyleModuleActivity.startActivity(view.getContext());
    }

    /**
     * 多线程
     * @param view
     */
    public void skipToMultThread(View view){
        ThreadTestActivity.startActivity(view.getContext());
    }

    /**
     * 算法
     * @param view
     */
    public void skipToArithmetic(View view){
        view.getContext().startActivity(new Intent(view.getContext() , ArithmeticTestActivity.class));
    }

    /**
     * 第三方库
     * @param view
     */
    public void skipToThirdLib(View view){
        view.getContext().startActivity(new Intent(view.getContext() , ThirdLibTestActivity.class));
    }


}
