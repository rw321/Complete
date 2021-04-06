package com.example.complete.ui.design;

import android.content.Context;
import android.content.Intent;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.example.complete.R;
import com.example.complete.base.BaseActivity;
import com.example.complete.databinding.ActivityDesignModelBinding;
import com.example.complete.design.HangryClient;
import com.example.complete.design.Message;
import com.example.complete.design.MessageImpl;
import com.example.complete.design.MessageProxy;
import com.example.complete.design.PersonComplete;
import com.example.complete.design.User;
import com.example.complete.design.YellowPeople;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

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
        proxyTest();
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


    public static void startActivity(Context context) {
        Intent intent = new Intent(context , StyleModuleActivity.class);
        context.startActivity(intent);
    }
}
