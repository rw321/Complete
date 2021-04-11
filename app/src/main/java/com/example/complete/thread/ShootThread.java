package com.example.complete.thread;

public class ShootThread extends Thread {

    private QiangTang qiangTang;

    public ShootThread(QiangTang qiangTang) {
        this.qiangTang = qiangTang;
    }

    @Override
    public void run() {
        super.run();
        qiangTang.shoot();
    }
}
