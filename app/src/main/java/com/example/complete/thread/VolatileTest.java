package com.example.complete.thread;

import com.example.complete.utils.SleepUtils;


/**
 * 最轻量级的同步机制
 * volatile可以保证线程间共享变量操作的可见性,但不能保证操作的原子性
 */
public class VolatileTest {

    private boolean ready;
    private int number;

    public class PrintThread extends Thread {

        @Override
        public void run() {
            super.run();
            System.out.println("PrintThread is running.......");
            while (!ready){

            }
            System.out.println("number = "+number);
        }
    }

    public void test() {

        new PrintThread().start();
        SleepUtils.sleep(1000);
        number = 51;
        ready = true;
        System.out.println("main is ended!");
    }
}
