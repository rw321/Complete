package com.example.complete.thread;

import com.example.complete.utils.SleepUtils;

/**
 * 线程优先级测试
 */
public class PriorityThreadTest {

    public static class PriorityTask implements Runnable {
        @Override
        public void run() {
            System.out.println(Thread.currentThread().getName() +  " 运行了");
            SleepUtils.sleep(500);
            System.out.println(Thread.currentThread().getName() +  "结束了");
        }
    }

    public void test(){
        Thread thread1 = new Thread(new PriorityTask());
        Thread thread2 = new Thread(new PriorityTask());
        Thread thread3 = new Thread(new PriorityTask());
        thread1.setName("thread1");
        thread2.setName("thread2");
        thread3.setName("thread3");

        thread3.setPriority(Thread.MAX_PRIORITY);
        thread2.setPriority(Thread.NORM_PRIORITY);
        thread1.setPriority(Thread.MIN_PRIORITY);

        thread1.start();
        thread2.start();
        thread3.start();

    }

}
