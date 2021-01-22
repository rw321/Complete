package com.example.complete.design;

/**
 * 黑种人类
 */
public class BlackPerson extends PersonComplete {
    @Override
    protected void hair() {
        System.out.println("blacks have brown hair");
    }

    @Override
    protected void skin() {
        System.out.println("blacks have black skin");
    }

    @Override
    protected void eye() {
        System.out.println("blacks have black eyes");
    }
}
