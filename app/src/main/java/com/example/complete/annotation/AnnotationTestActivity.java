package com.example.complete.annotation;

import android.widget.TextView;

import com.example.complete.R;
import com.example.complete.base.BaseActivity;
import com.example.complete.databinding.ActivityAnnotationBinding;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

/**
 * 注解测试
 * Retentionpolicy.SOURCE   APT(Annotation Processor Tools)
 * RetentionPolicy.CLASS    插桩
 * RetentionPolicy.RUNTIME  反射
 *
 * APT运行在编译阶段
 * javac将.java文件转成.class文件
 * javac在编译时采集到所有的注解信息 ->包装成一个节点Element -> javac调用注解处理程序
 * 注解处理程序 继承AbstractProcessor
 * 注解处理器需要去注册,注册方法是在main下创建META-INF.services文件夹
 * 在该文件夹下添加javax.annotation.processing.Processor文件
 * 在该文件中放入注解的全类名
 *
 * Type是个接口,有四个子接口继承自Type
 * ParameterizedType 得到具体的泛型类型
 *
 */
public class AnnotationTestActivity extends BaseActivity<ActivityAnnotationBinding> {

    @GetView(R.id.tv_text)
    TextView tvText;

    @BindIntentExtra("name")
    private String name;
    @BindIntentExtra("age")
    private int age;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_annotation;
    }

    @Override
    protected void initData() {
        super.initData();
        dealAnnotation();

        //加大括号这个对象就变成匿名内部类,然后javac在编译的过程中就会生成ChildTypeReference类
        // ,这样的话泛型就变成成员变量保留了
        Type type = new TypeReference<BaseResponse<ContentResult>>(){



        }.getType();
        System.out.println("=======" + type);

        System.out.println("==========" + name);
        System.out.println("==========" + age);



    }

    private void dealAnnotation() {
        Field[] fields = this.getClass().getDeclaredFields();
        for (Field field : fields) {
            try {
                if (field.getAnnotations()!= null) {
                    if (field.isAnnotationPresent(GetView.class)) {
                        field.setAccessible(true);
                        GetView annotation = field.getAnnotation(GetView.class);
                        field.set(this , findViewById(annotation.value()));
                    }

                    if (field.isAnnotationPresent(BindIntentExtra.class)) {
                        field.setAccessible(true);
                        BindIntentExtra annotation = field.getAnnotation(BindIntentExtra.class);
                        String type = field.getType().getSimpleName();
                        if ("String".equals(type))
                            field.set(this , getIntent().getStringExtra(annotation.value()));
                        else if ("int".equals(type)){
                            field.set(this , getIntent().getIntExtra(annotation.value() , 0));

                        }

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

    /**
     * 由于泛型擦除,T会变成Object
     * @param <T>
     */
    private class TypeReference<T>{

        Type type;

        public TypeReference() {
            Type genericSuperclass = getClass().getGenericSuperclass();
            ParameterizedType parameterizedType = (ParameterizedType) genericSuperclass;
            //泛型可能有多个,所以返回一个集合
            Type[] actualTypeArguments = parameterizedType.getActualTypeArguments();
            type = actualTypeArguments[0];
        }

        public Type getType() {
            return type;
        }
    }


    private class ChildTypeReference{
        BaseResponse<ContentResult> t;
    }


}
