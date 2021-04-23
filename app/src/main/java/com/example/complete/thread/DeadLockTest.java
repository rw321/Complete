package com.example.complete.thread;

import com.example.complete.utils.SleepUtils;

import java.util.concurrent.locks.ReentrantLock;

public class DeadLockTest {

    private static Object o1 = new Object();
    private static Object o2 = new Object();
    private static ReentrantLock lock1 = new ReentrantLock();
    private static ReentrantLock lock2 = new ReentrantLock();

    public static class Thread1 extends Thread {
        @Override
        public void run() {
            super.run();
            synchronized (o1){
                System.out.println("Thread1拿到了资源1");
                SleepUtils.sleep(1000);
                synchronized (o2) {
                    System.out.println("Thread1拿到了资源2");
                }
            }
        }
    }

    public static class Thread2 extends Thread {
        @Override
        public void run() {
            super.run();
            synchronized (o2) {
                System.out.println("Thread2 拿到了资源2");
                SleepUtils.sleep(1000);
                synchronized (o1){
                    System.out.println("Thread2拿到了资源1");
                }
            }
        }
    }

    public static class Thread3 extends Thread {
        @Override
        public void run() {
            super.run();
            while (true){
                if (lock1.tryLock()){
                    System.out.println("Thread3获取到了lock1");
                    try{
                        if (lock2.tryLock()) {
                            System.out.println("Thread3获取到了lock2");
                            try{
                                System.out.println("执行Thread3业务");
                                break;
                            }finally {
                                lock2.unlock();
                            }
                        }
                    }finally {
                        lock1.unlock();
                    }
                }
//                SleepUtils.sleep(2);
            }
        }
    }

    public static class Thread4 extends Thread {
        @Override
        public void run() {
            super.run();
            while (true) {
                if (lock2.tryLock()){
                    System.out.println("Thread4获取到了锁2");
                    try{
                        if (lock1.tryLock()) {
                            System.out.println("Thread4获取到了锁1");
                            try{
                                System.out.println("执行Thread4业务");
                                break;
                            }finally {
                                lock1.unlock();
                            }
                        }
                    }finally {
                        lock2.unlock();
                    }
                }
//                SleepUtils.sleep(3);
            }
        }
    }

    public void test() {
//        new Thread1().start();
//        new Thread2().start();
        new Thread3().start();
        new Thread4().start();
    }

}
