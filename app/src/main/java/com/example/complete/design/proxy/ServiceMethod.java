package com.example.complete.design.proxy;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;

import okhttp3.FormBody;
import okhttp3.HttpUrl;
import okhttp3.Request;
import retrofit2.http.Field;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

public class ServiceMethod {

    HttpUrl baseUrl;
    String httpMethod;
    String relativeUrl;
    boolean hasBody;
    ParameterHandler[] parameterHandlers;
    HttpUrl.Builder urlBuild;
    private final okhttp3.Call.Factory callFactory;
    private FormBody.Builder formBuild;


    public ServiceMethod(Build build) {
        this.baseUrl = build.enjoyRetrofit.baseUrl;
        httpMethod = build.httpMethod;
        relativeUrl = build.relativeUrl;
        hasBody = build.hasBody;
        parameterHandlers = build.parameterHandlers;
        callFactory = build.enjoyRetrofit.callFactory;
        if (hasBody) {
            formBuild = new FormBody.Builder();
        }
    }

    public Object invoke(Object[] args) {

        for (int i = 0 ; i < parameterHandlers.length ;i++) {
            ParameterHandler handler = parameterHandlers[i];
            handler.apply(this , (String) args[i]);
        }

        if (urlBuild == null) {
            urlBuild = baseUrl.newBuilder(relativeUrl);
        }
        HttpUrl url = urlBuild.build();
        FormBody formBody = null;
        if (formBuild != null) {
            formBody = formBuild.build();
        }

        Request request = new Request.Builder().url(url).method(httpMethod, formBody).build();
        return callFactory.newCall(request);
    }

    public void addQueryParameter(String key, String value) {
        if (urlBuild == null) {
            urlBuild = baseUrl.newBuilder(relativeUrl);
        }

        urlBuild.addQueryParameter(key , value);


    }

    public void addFieldParameter(String key, String value) {
        formBuild.add(key , value);
    }

    public static class Build{

        EnjoyRetrofit enjoyRetrofit;
        String httpMethod;
        String relativeUrl;
        boolean hasBody;
        private final Annotation[] methodAnnotations;
        private final Annotation[][] parameterAnnotations;
        private ParameterHandler[] parameterHandlers;


        public Build(EnjoyRetrofit enjoyRetrofit, Method method) {
            this.enjoyRetrofit = enjoyRetrofit;
            methodAnnotations = method.getAnnotations();
            parameterAnnotations = method.getParameterAnnotations();

        }

        public ServiceMethod build(){
            for (Annotation annotation : methodAnnotations) {
                if (annotation instanceof POST) {
                    httpMethod = "POST";
                    relativeUrl = ((POST) annotation).value();
                    hasBody = true;
                }else if (annotation instanceof GET) {
                    httpMethod = "GET";
                    relativeUrl = ((GET) annotation).value();
                }
            }

            parameterHandlers = new ParameterHandler[parameterAnnotations.length];

            for (int i = 0; i  < parameterAnnotations.length;i++) {
                Annotation[] parameterAnnotation = parameterAnnotations[i];
                for (Annotation anno: parameterAnnotation) {
                    if (anno instanceof Field) {
                        String value = ((Field) anno).value();
                        parameterHandlers[i] = new ParameterHandler.FieldParameterHandler(value);
                    }else if (anno instanceof Query) {
                        String value = ((Query) anno).value();
                        parameterHandlers[i] = new ParameterHandler.QueryParameterHandler(value);
                    }
                }
            }

            return new ServiceMethod(this);
        }

    }

}
