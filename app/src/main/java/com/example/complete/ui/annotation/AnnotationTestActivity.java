package com.example.complete.ui.annotation;

import android.widget.TextView;

import com.example.complete.R;
import com.example.complete.base.BaseActivity;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import butterknife.OnClick;

public class AnnotationTestActivity extends BaseActivity {

    @getView(R.id.tv_text)
    TextView tvText;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_annotation;
    }

    @Override
    protected void initData() {
        super.initData();
        dealAnnotation();
        //AnnoDeal.getInstance().register(this);
    }

    private void dealAnnotation() {
        Field[] fields = this.getClass().getDeclaredFields();
        for (Field field : fields) {
            try {
                if (field.getAnnotations()!= null) {
                    if (field.isAnnotationPresent(getView.class)) {
                        field.setAccessible(true);
                        getView annotation = field.getAnnotation(getView.class);
                        field.set(this , findViewById(annotation.value()));
                    }
                }
            }catch(Exception e) {

            }

        }
    }

    private void dealMethodAnnotation() {
        Method[] methods = this.getClass().getDeclaredMethods();
        for (Method method : methods) {
            try{
                if (method.getAnnotations() != null) {
                    if (method.isAnnotationPresent(RunOnUIThread.class)) {
                        method.setAccessible(true);
                        this.runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                try {
                                    method.invoke(AnnotationTestActivity.this);
                                } catch (IllegalAccessException e) {
                                    e.printStackTrace();
                                } catch (InvocationTargetException e) {
                                    e.printStackTrace();
                                }
                            }
                        });
                    }
                }
            }catch(Exception e){

            }
        }
    }

    @OnClick(R.id.tv_text)
    public void onClick(){
        System.out.println("threadId =====" + Thread.currentThread().getId());
        new Thread() {
            @Override
            public void run() {
                super.run();
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                //System.out.println("threadId =====" + Thread.currentThread().getId());
                AnnoDeal.getInstance().runOnUI(AnnotationTestActivity.this , "refreshUI");
            }
        }.start();
    }

    @RunOnUIThread
    public void refreshUI() {
        System.out.println("threadId =====" + Thread.currentThread().getId());
        tvText.setText("哈哈哈");

    }

}
