package com.example.complete.ui;

import com.example.complete.R;
import com.example.complete.base.BaseActivity;

public class SyncTestActivity extends BaseActivity {

    static int count;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_sync;
    }

    @Override
    protected void initData() {
        super.initData();
        MyThread thread = new MyThread();
        MyThread thread2 = new MyThread();
        new Thread(thread , "thread1").start();
        new Thread(thread2 , "thread2").start();
    }

    class MyThread implements Runnable{

        @Override
        public void run() {
            synchronized (this){
                for(; count < 1000;count++) {
                    System.out.println("线程名:" + Thread.currentThread().getName() + "   i = " + count);
                }
            }

        }
    }

}
