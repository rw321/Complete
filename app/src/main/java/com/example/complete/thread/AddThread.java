package com.example.complete.thread;

public class AddThread extends Thread {

    private QiangTang  qiangTang;

    public AddThread(QiangTang qiangTang) {
        this.qiangTang = qiangTang;
    }

    @Override
    public void run() {
        super.run();
        qiangTang.add();
    }
}
