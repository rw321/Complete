package com.example.complete.thread;

import androidx.annotation.Nullable;

public class ThreadLocalTest {

    private static ThreadLocal<Integer> threadLocal = new ThreadLocal<Integer>(){
        @Nullable
        @Override
        protected Integer initialValue() {
            return 1;
        }
    };


    private static class Thread1 extends Thread {
        @Override
        public void run() {
            super.run();
            Integer value = threadLocal.get();
            value += 1;
            threadLocal.set(value);
            System.out.println("=====" + Thread.currentThread().getName() + ":" + threadLocal.get());
        }
    }

    public void test(){
        for (int i = 0; i < 3;i++) {
            new Thread1().start();
        }
    }

}
