package com.example.complete.design;

/**
 * 黄种人类
 */
public class YellowPeople extends PersonComplete {
    @Override
    protected void hair() {
        System.out.println("黄种人是黑头发");
    }

    @Override
    protected void skin() {
        System.out.println("黄种人是黄皮肤");
    }

    @Override
    protected void eye() {
        System.out.println("黄种人是黑眼睛");
    }
}
