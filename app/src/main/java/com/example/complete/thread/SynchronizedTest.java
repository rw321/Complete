package com.example.complete.thread;

import com.example.complete.utils.SleepUtils;

public class SynchronizedTest {

    public static Integer count = 0;
    private static Object o = new Object();

    /**
     * 不加锁
     */
    public static class Thread1 extends Thread {
        @Override
        public void run() {
            super.run();
            for (int i = 0; i< 100;i++){
                count++;
            }

        }
    }

    /**
     * 不能成功
     * 因为count++ 之后count指向的对象在内存中变化了,就锁不住了
     */
    public static class Thread2 extends Thread {
        @Override
        public void run() {
            super.run();
            synchronized (count) {
                for (int i = 0; i< 100;i++){
                    count++;
                }
            }
        }
    }

    /**
     * 因为o是静态的,在内存中有唯一性,所以成功加锁
     */
    public static class Thread3 extends Thread {
        @Override
        public void run() {
            super.run();
            synchronized (o) {
                for (int i = 0; i< 100;i++){
                    count++;
                }
            }
        }
    }

    /**
     * 不能锁住,因为锁对象在内存中不唯一
     */
    public static class Thread4 extends Thread {
        Object o = new Object();
        @Override
        public void run() {
            super.run();
            synchronized (o) {
                for (int i = 0; i< 100;i++){
                    count++;
                }
            }
        }
    }

    public class Thread5 extends Thread {

        private SynchronizedTest test;

        public Thread5(SynchronizedTest test) {
            this.test = test;
        }

        @Override
        public void run() {
            super.run();
//            test.add();
            add();
            System.out.println("");
        }
    }

    /**
     * 给方法加锁,加的是当前类的实例
     *
     * 方法锁和this都是以当前类的对象为锁,但并不是说内存中的所有当前类的对象,都是锁
     * 只是调用该方法的内存实例才是锁,如果多个线程同时调用该方法,并且调用该方法的实例是同一个,那么就能锁住
     * 如果调用该方法的实例不同,那么不能锁住
     *
     */
    private synchronized void add(){

//        synchronized (SynchronizedTest.this) {
            System.out.println("==========addr==========" + SynchronizedTest.this);
            for (int i = 0; i< 1000;i++){
                count++;
            }
//        }
    }

    public class Thread6 extends Thread {
        @Override
        public void run() {
            super.run();
            increate();
        }
    }

    /**
     * 类锁
     */
    private void increate(){
        synchronized (SynchronizedTest.class) {
            for (int i = 0; i< 1000;i++){
                count++;
            }
        }
    }


    public void test(){


        for (int i = 0; i < 3;i++) {
//            new Thread1().start();
//            new Thread2().start();
//            new Thread3().start();
//            new Thread4().start();

//            new Thread5(this).start();
            new Thread6().start();
        }

    }


}
