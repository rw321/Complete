package com.example.complete.design;

import android.content.Context;
import android.content.Intent;
import android.view.View;

import com.example.complete.R;
import com.example.complete.base.BaseActivity;
import com.example.complete.databinding.ActivityDesignModelBinding;
import com.example.complete.design.proxy.BindClick;
import com.example.complete.design.proxy.BindClickDeal;
import com.example.complete.design.proxy.EnjoyRetrofit;
import com.example.complete.design.proxy.EnjoyRetrofitApi;
import com.example.complete.design.proxy.Message;
import com.example.complete.design.proxy.MessageImpl;

import java.io.IOException;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

/**
 * 测试设计模式
 */
public class StyleModuleActivity extends BaseActivity<ActivityDesignModelBinding> {
    @Override
    protected int getLayoutId() {
        return R.layout.activity_design_model;
    }

    @Override
    protected void initData() {
        super.initData();
        //proxyTest();
        //studyRetrofitMethod();
        new BindClickDeal().bind(this);
    }

    /**
     * 仿照Retrofit做代理 反射 注解实践
     */
    private void studyRetrofitMethod() {
        EnjoyRetrofit enjoyRetrofit = new EnjoyRetrofit.Build().baseUrl("http://apitest.xiaobu121.com/api2/2_8/").build();
        EnjoyRetrofitApi enjoyRetrofitApi = enjoyRetrofit.create(EnjoyRetrofitApi.class);

        Call responseCall = enjoyRetrofitApi.addShoppingCar("36626", "1", "2");
        responseCall.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                System.out.println("==========异常了");
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                System.out.println("==========" + response.body().string());
            }
        });

    }

    /**
     * 如何在内存中生成Class对象
     * ProxyClassFactory类内部调用ProxyGenerator.generatorProxy("要生成类的类名","要代理的接口数组"),返回二进制数组,然后调用Proxy.defineClass0()生成Class对象
     */
    private void proxyTest() {
        //静态代理
        //new MessageProxy(new MessageImpl()).send();
        //动态代理
        MessageImpl messageImpl = new MessageImpl();
        Object o = Proxy.newProxyInstance(MessageImpl.class.getClassLoader(), new Class[]{Message.class}, new InvocationHandler() {
            @Override
            public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                return method.invoke(messageImpl , args);
            }
        });

        Message message = (Message) o;
        message.send();

    }

    @BindClick({R.id.btn1,R.id.btn2})
    public void click(){
        System.out.println("=============点击了");
    }


    public static void startActivity(Context context) {
        Intent intent = new Intent(context , StyleModuleActivity.class);
        context.startActivity(intent);
    }
}
