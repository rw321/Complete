package com.example.complete.thread;

import com.example.complete.utils.SleepUtils;

/**
 * 多线程协作 join()测试
 */
public class JoinTest {

    public static class T1 extends Thread {
        @Override
        public void run() {
            super.run();
            System.out.println("线程 T1 开始跑了...");
            T2 t2 = new T2();
            t2.start();  //线程T2进入就绪状态,未获取CPU执行权
            try {
                t2.join();//线程T2获取T1的CPU执行权
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("线程 T1 结束了...");
        }
    }

    public static class T2 extends Thread {
        @Override
        public void run() {
            super.run();
            System.out.println("线程 T2 开始跑了...");
            SleepUtils.sleep(500);
            System.out.println("线程 T2 结束了...");
        }
    }

    public void test(){
        T1 t1 = new T1();
        t1.start();

    }

}
