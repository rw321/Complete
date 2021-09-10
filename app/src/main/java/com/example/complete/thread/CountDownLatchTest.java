package com.example.complete.thread;

import com.example.complete.utils.SleepUtils;

import java.util.concurrent.CountDownLatch;

/**
 * 多线程协作中,进入wait的线程只有count归0后,才能继续执行
 */
public class CountDownLatchTest {

    private static CountDownLatch mCount = new CountDownLatch(5);

    static class MainThread extends Thread {
        @Override
        public void run() {
            super.run();
            try {
                System.out.println("主线程开始等待......");
                mCount.await();
                System.out.println("主线程执行中.........");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    static class WorkThread extends Thread {
        @Override
        public void run() {
            super.run();
            SleepUtils.sleep(500);
            System.out.println("work finish............");
            mCount.countDown();
        }
    }

    public void test(){
        new MainThread().start();
        for (int i = 0; i < 5;i++) {
            new WorkThread().start();
        }
    }

}
