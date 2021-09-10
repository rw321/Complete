package com.example.complete.thread;

import com.example.complete.utils.SleepUtils;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

/**
 * 多线程协作中,只有当所有线程都达到同一临界值(即都执行await()后),才能继续往下执行
 */
public class CyclicBarrierTest {

    /**
     * 第二参数表示在所有线程都到达屏障之后,先执行一个任务,再接着执行各线程自己接下来的任务
     */
    private static CyclicBarrier barrier = new CyclicBarrier(5, new Runnable() {
        @Override
        public void run() {
            System.out.println("所有线程都达到了");
        }
    });

    static class WorkThread extends Thread {

        private long time;
        private int id;

        public WorkThread(int id , long time) {
            this.time = time;
            this.id = id;
        }

        @Override
        public void run() {
            super.run();
            System.out.println("线程-" + id + "开始执行");
            SleepUtils.sleep(time);
            try {
                barrier.await();
            } catch (BrokenBarrierException e) {
                e.printStackTrace();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("线程-" + id + "执行中");
        }
    }

    public void test(){
        for (int i = 0;i < 5;i++) {
            new WorkThread(i , 500 * i).start();

        }
    }

}
