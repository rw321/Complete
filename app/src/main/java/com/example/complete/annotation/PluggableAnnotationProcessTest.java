package com.example.complete.annotation;

import com.example.module_annotation.ToBeTest;

/**
 * 插件化注解处理
 */
public class PluggableAnnotationProcessTest {

    @ToBeTest(owner = "Alex")
    public static void test() {
        System.out.println("pluggable annotation process test");
    }


    public static void main(String[] args) {
        test();
    }

}
