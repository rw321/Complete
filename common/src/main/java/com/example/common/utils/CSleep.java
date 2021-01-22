package com.example.common.utils;

public class CSleep {

    private boolean isSleeping = false;

    private volatile static CSleep instance;

    private CSleep() {
    }

    public boolean isRuning() {
        return isSleeping;
    }

    public static CSleep getInstance() {
        if (instance == null) {
            synchronized (CSleep.class) {
                if (instance == null) {
                    instance = new CSleep();
                }
            }
        }
        return instance;
    }

    /**
     * 让线程睡眠一定时间
     * @param defaultSleepTime
     */
    public void runWithTime(final long defaultSleepTime) {
        isSleeping = true;
        new Thread() {

            @Override
            public void run() {
                try {
                    sleep(defaultSleepTime, 0);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                isSleeping = false;
                super.run();
            }
        }.start();
    }

}
