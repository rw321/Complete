package com.example.complete.thread;

public class UnAtomicLongTest implements Runnable {
    public static volatile long test1;
    private final long val;

    public UnAtomicLongTest(long val) {
        this.val = val;
    }

    @Override
    public void run() {
        while(!Thread.interrupted()) {
            test1 = val;
            System.out.println("=========" + test1);
        }
    }
}
