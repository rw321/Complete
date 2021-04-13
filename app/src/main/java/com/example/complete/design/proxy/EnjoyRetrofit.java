package com.example.complete.design.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import okhttp3.Call;
import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;

public class EnjoyRetrofit {

    okhttp3.Call.Factory callFactory;
    HttpUrl baseUrl;
    private final Map<Method, ServiceMethod> serviceMethodCache = new ConcurrentHashMap<>();

    private EnjoyRetrofit(Call.Factory callFactory, HttpUrl baseUrl) {
        this.callFactory = callFactory;
        this.baseUrl = baseUrl;
    }

    public <T>T create(Class<T> clazz) {
        return (T) Proxy.newProxyInstance(clazz.getClassLoader(), new Class[]{clazz}, new InvocationHandler() {
            @Override
            public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                ServiceMethod serviceMethod = loadServiceMethod(method);
                return serviceMethod.invoke(args);
            }
        });
    }

    private ServiceMethod loadServiceMethod(Method method) {
        ServiceMethod result = serviceMethodCache.get(method);
        if (result != null) {
            return serviceMethodCache.get(method);
        }
        synchronized (serviceMethodCache){
            result = serviceMethodCache.get(method);
            if (result != null) {
                return serviceMethodCache.get(method);
            }
            result = new ServiceMethod.Build(this , method).build();
            serviceMethodCache.put(method , result);

        }
        return result;

    }


    public static class Build{
        okhttp3.Call.Factory callFactory;
        HttpUrl baseUrl;

        public Build client(OkHttpClient client) {
            callFactory = client;
            return this;
        }

        public Build baseUrl(String url) {
            baseUrl = HttpUrl.get(url);
            return this;
        }

        public EnjoyRetrofit build(){
            if (baseUrl == null)
                throw new IllegalStateException("Base Url required");
            if (callFactory == null)
                callFactory = new OkHttpClient();
            return new EnjoyRetrofit(callFactory , baseUrl);

        }

    }

}
