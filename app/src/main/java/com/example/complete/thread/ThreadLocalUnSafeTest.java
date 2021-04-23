package com.example.complete.thread;

import androidx.annotation.Nullable;

import com.example.complete.utils.SleepUtils;

/**
 * ThreadLocal线程不安全测试
 *
 * 去掉static可以变为线程安全
 */
public class ThreadLocalUnSafeTest {

    private static class Number {
        private int num;

        public Number(int num) {
            this.num = num;
        }

        public int getNum() {
            return num;
        }

        public void setNum(int num) {
            this.num = num;
        }
    }

    private static ThreadLocal<Number> threadLocal = new ThreadLocal<Number>(){
        @Nullable
        @Override
        protected Number initialValue() {
            return new Number(0);
        }
    };

    private static class Thread1 extends Thread {

        private static Number number = new Number(0);

        @Override
        public void run() {
            super.run();
            number.setNum(number.getNum() + 1);
            threadLocal.set(number);
            SleepUtils.sleep(200);
            System.out.println(Thread.currentThread().getName() + threadLocal.get().getNum());
        }
    }

    public void test() {
        for (int i = 0; i < 5;i++) {
            new Thread1().start();
        }
    }

}
