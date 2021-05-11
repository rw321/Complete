package com.example.complete.thread;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class LockTest {

    static Lock lock = new ReentrantLock();

    static volatile int count;

    private void increment() {
        lock.lock();
        try{
            count++;
            System.out.println("=======" + count);
        }finally {
            lock.unlock();
        }
    }

    public void test() {
        for (int i = 0;i < 5;i++) {
            new Thread(){
                @Override
                public void run() {
                    super.run();
                    while(count < 100){
                        increment();
                    }
                }
            }.start();
        }
    }

}
