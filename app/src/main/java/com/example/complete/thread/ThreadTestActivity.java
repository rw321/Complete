package com.example.complete.thread;

import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.view.View;

import androidx.annotation.RequiresApi;

import com.example.common.utils.ToastUtils;
import com.example.complete.R;
import com.example.complete.base.BaseActivity;
import com.example.complete.databinding.ActivityThreadBinding;
import com.example.complete.utils.SleepUtils;

/**
 * java 中的原子性 可见性
 * 原子性:原子性就是不可再分的,不论一核还是多核,同一时间只能有一个线程对其进行操作(在整个操作过程中不会被线程调度器中断)
 * 非原子性:在整个操作过程中,会被线程调度器中断
 * java中的原子性操作
 * ①除double 和 long之外的基本类型的赋值操作
 * ②所有引用reference的赋值操作
 * ③java.concurrent.Atomic.* 包中所有类的一切操作。
 *
 * i++不是原子性操作 因为 取出i的值是一步操作 +1是另一步操作
 *
 *共享变量：如果一个变量在多个线程的工作内存中都存在副本，那么这个变量就是这个几个线程的共享变量
 *
 * 可见性：一个线程对共享变量值的修改，能够及时的被其它线程看到。
 *
 * 在java内存模型中,所有的共享变量都存储在主内存中,每个线程都有自己的独立内存,里面存储该线程用到的共享变量的副本
 * 线程只与工作内存交互,不能访问主内存中的共享变量
 * 当主内存中有一个共享变量 , 子线程如果要操作该共享变量,必须先将改变量拷贝到子线程的工作副本中,操作工作副本中的变量
 * 不能直接操作主内存中的共享变量
 * 在java内存模型中(JMM)中,
 * ①线程对共享变量的操作,只能操作共享变量在工作内存中的副本,不能直接操作主内存中的共享变量
 * ②不同线程之间不能访问其他线程工作内存中的共享变量的副本,线程间共享变量的同步只能通过更新主内存的共享变量完成
 *
 * 共享变量可见性的实现需要两个步骤
 * ①把工作内存中的共享变量改变更新到主内存中
 * ②把主内存中的共享变量的值同步到其他工作内存中
 *
 */
public class ThreadTestActivity extends BaseActivity<ActivityThreadBinding> {

    private static final int THREAD_COUNT = 20;
    private static int count;
    private SynchronizedTest synchronizedTest1;
    private SynchronizedTest synchronizedTest2;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_thread;
    }

    @Override
    protected void initData() {
        super.initData();
        //testConnectionPool();
        //testMethodJoin();
        //priorityTest();
//        syncTest();
//        volatileTest();
//        deadLockTest();
//        threadLocalTest();
//        forkJoinTest();
//        new CountDownLatchTest().test();
//        new CyclicBarrierTest().test();
//        new SemaphoreTest().test();
//        new ExchangeTest().test();
//        new FutureTaskTest().test();

    }

    public void test(View view) {
//        new ForkJoinHomeWork().test();
//        new AtomicTest().test4();
        new LockTest().test();
    }

    public void forkJoinTest() {
        new ForkJoinTest().test();
    }

    public void threadLocalTest(){
//        new ThreadLocalTest().test();
        new ThreadLocalUnSafeTest().test();
    }

    public void deadLockTest() {
        new DeadLockTest().test();
    }

    /**
     * volatile测试
     */
    private void volatileTest() {
        new VolatileTest().test();
    }

    /**
     * 同步测试
     */
    private void syncTest(){
        synchronizedTest1 = new SynchronizedTest();
        System.out.println("=========" + synchronizedTest1.toString() + "======");
        synchronizedTest1.test();
//        synchronizedTest2 = new SynchronizedTest();
//        System.out.println("=========" + synchronizedTest2.toString() + "======");
//        synchronizedTest2.test();

        SleepUtils.sleep(1000);

        System.out.println("count======" + SynchronizedTest.count);

    }

    private void priorityTest() {
        new PriorityThreadTest().test();
    }

    /**
     * 测试thread的join方法
     */
    public void testMethodJoin(){
        new JoinTest().test();
    }

    /**
     * 测试wait()&notify()
     */
    private void testConnectionPool() {
        QiangTang qiangTang = new QiangTang();
        new AddThread(qiangTang).start();
        new ShootThread(qiangTang).start();
    }


    private static void increase() {
        count++;
    }

    public void dataSyncTest(View v) {
        Thread[] threads = new Thread[THREAD_COUNT];
        for (int i = 0; i < THREAD_COUNT; i++) {
            threads[i] = new Thread(new Runnable() {
                @Override
                public void run() {
                    for (int i = 0; i < 1000; i++) {
                        increase();
                        System.out.println("==========" + count);
                    }
                }
            });
            threads[i].start();
        }
        while (Thread.activeCount() > 1) {
            Thread.yield();
            System.out.println("+++++++++++");
        }
        System.out.println(count);

    }
    

    /**
     * Interrupt 是给正在运行的线程加一个阻断标签,并不会真正影响线程的运行
     * 如果想要真正的阻断线程 ,可以在run()中,获取当前线程的中断标签,再做具体操作中断线程(例如return 或者抛出异常)
     * interrupt()给调用该方法所属线程的实例加阻断标签
     * isInterrupted()判断调用该方法的实例线程是否中断,并不会移除中断标签
     * Thread.interrupted() 当前线程是否是中断状态,如果是,移除中断标签
     * @param view
     * @throws InterruptedException
     */
    public void threadInterrupt(View view) throws InterruptedException {
        Thread thread = new Thread(new TestThread() , "My thread");
        System.out.println("thread start......");
        thread.start();
        Thread.sleep(3000);
        System.out.println("thread interrupt...");
        thread.interrupt();
        System.out.println("thread is interrupt..." + Thread.interrupted());
        System.out.println("thread is interrupt..." + thread.isInterrupted());
        Thread.sleep(3000);
        System.out.println("Application end...");
    }

    /**
     * Long和double 赋值操作的非原子性验证
     *  但是java对long和double的赋值操作是非原子操作！！long和double占用的字节数都是8，也就是64bits。
     *  在32位操作系统上对64位的数据的读写要分两步完成，每一步取32位数据。这样对double和long的赋值操作就会有问题：
     *  如果有两个线程同时写一个变量内存，一个进程写低32位，而另一个写高32位，这样将导致获取的64位数据是失效的数据。
     *  因此需要使用volatile关键字来防止此类现象。
     *  volatile本身不保证获取和设置操作的原子性，仅仅保持修改的可见性。
     *  但是java的内存模型保证声明为volatile的long和double变量的get和set操作是原子的。
     * @param view
     */
    public void atomicCheck(View view) {
        Thread thread1 = new Thread(new UnAtomicLongTest(-1));
        Thread thread2 = new Thread(new UnAtomicLongTest(0));
        
        System.out.println(Long.toBinaryString(-1));
        System.out.println(Long.toBinaryString(0));

        thread1.start();
        thread2.start();

        long val;

        while((val = UnAtomicLongTest.test1) == -1 || val == 0) {

        }

        System.out.println(pad(Long.toBinaryString(val),64));
        System.out.println(val);

        thread1.interrupt();
        thread2.interrupt();

    }

    private static String pad(String s, int targetLength) {
        int n = targetLength - s.length();
        for (int x = 0; x < n; x++) {
            s = "0" + s;
        }
        return s;
    }

    public static void startActivity(Context context) {
        context.startActivity(new Intent(context , ThreadTestActivity.class));
    }

}
