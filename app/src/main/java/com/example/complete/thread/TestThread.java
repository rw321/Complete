package com.example.complete.thread;

public class TestThread implements Runnable {

    boolean stop = false;

    @Override
    public void run() {
        while (!stop) {
            System.out.println("my application is running...");
            long time = System.currentTimeMillis();
            while (System.currentTimeMillis() - time < 1000) {
                
            }
            if (Thread.currentThread().isInterrupted())
                return;
        }
        System.out.println("my thread exist under request...");
    }
}
