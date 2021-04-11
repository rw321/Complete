package com.example.complete.thread;

/**
 * 同步锁的对象和调用wait()和notify()的对象必须是同一个
 */
public class QiangTang {

    private static Integer count = new Integer(0);
    private static boolean zhuangtianzhong;
    private static int size;

    public synchronized void add() {

        synchronized (QiangTang.this) {
            while(true) {
                if (count < 20){
                    zhuangtianzhong = true;
                    count++;
                    System.out.println("枪膛里现在有子弹:" + count);
                }else {
                    zhuangtianzhong = false;
                    System.out.println("枪膛子弹装满了");
                    notify();
                    try {
                        wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }

            }
        }
    }

    public void shoot() {

        synchronized (QiangTang.this) {

            while(true) {
                if (!zhuangtianzhong) {

                    if (count == 0) {
                        System.out.println("子弹全部射完了");
                        size++;
                        if (size == 5) {
                            break;
                        }
                        notify();
                        try {
                            wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }

                    }else {
                        count--;
                        System.out.println("枪膛中剩余子弹:" + count);
                    }

                }
            }

        }


    }


}
