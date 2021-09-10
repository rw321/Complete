package com.example.complete.thread;

import com.example.complete.utils.SleepUtils;

import java.sql.Connection;
import java.util.LinkedList;
import java.util.concurrent.Semaphore;

/**
 * 流控 信号链练习
 */
public class SemaphoreTest {

    private static final int MAX_SIZE = 10;

    private final Semaphore useful , useless;

    private static LinkedList<Connection> pool = new LinkedList<>();

    static {
        for (int i = 0; i < MAX_SIZE;i++) {
            pool.addLast(SqlConnectImpl.fetchConnection());
        }
    }

    public SemaphoreTest() {
        this.useful = new Semaphore(MAX_SIZE);
        this.useless = new Semaphore(0);
    }

    public Connection getConnect() throws InterruptedException {
        useful.acquire();
        Connection connection;
        synchronized (pool) {
            connection = pool.removeFirst();
        }
        useless.release();
        return connection;
    }

    public void releaseConnection(Connection connection) throws InterruptedException {
        if (connection == null)
            return;
        System.out.println("当前有"+useful.getQueueLength()+"个线程等待数据库连接!!"
                +"可用连接数："+useful.availablePermits());
        useless.acquire();
        synchronized (pool){
            pool.addLast(connection);
        }
        useful.release();
    }

    class BussThread extends Thread{

        @Override
        public void run() {
            super.run();
            long start = System.currentTimeMillis();
            try {
                Connection connect = getConnect();
                System.out.println("Thread_"+Thread.currentThread().getId()
                        +"_获取数据库连接共耗时【"+(System.currentTimeMillis()-start)+"】ms.");
                SleepUtils.sleep((long) (Math.random() * 100));
                releaseConnection(connect);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public void test() {
        for (int i = 0; i < 50; i++) {
            new BussThread().start();
        }
    }

}
