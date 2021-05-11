package com.example.complete.thread;

import android.os.Build;
import android.service.autofill.AutofillService;

import androidx.annotation.RequiresApi;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicIntegerArray;
import java.util.concurrent.atomic.AtomicReference;
import java.util.concurrent.atomic.AtomicStampedReference;
import java.util.function.IntBinaryOperator;

public class AtomicTest {

    private static AtomicInteger atomicInteger = new AtomicInteger(10);
    private static AtomicStampedReference<String> atomicStampedReference = new AtomicStampedReference<>("张三" , 0);
    private int oldStamped;

    static class UserInfo {
        private String name;
        private int age;

        public UserInfo(String name, int age) {
            this.name = name;
            this.age = age;
        }

        @Override
        public String toString() {
            return "UserInfo{" +
                    "name='" + name + '\'' +
                    ", age=" + age +
                    '}';
        }
    }

//    @RequiresApi(api = Build.VERSION_CODES.N)
    public void test(){
//        System.out.println("==========" + atomicInteger.get());
//        System.out.println("==========" + atomicInteger.getAndDecrement());  //i--
//        System.out.println("==========" + atomicInteger.getAndIncrement());  //i++
//        System.out.println("==========" + atomicInteger.incrementAndGet());  //++i
//        System.out.println("==========" + atomicInteger.decrementAndGet());  //--i
//        System.out.println("==========" + atomicInteger.getAndAdd(10));  // i 先赋值 ,然后做 +=delta操作
//        System.out.println("==========" + atomicInteger.getAndSet(10));  // i 先赋值 ,然后做赋值操作
//        System.out.println("==========" + atomicInteger.addAndGet(10));  // += 然后赋值
//        System.out.println("==========" + atomicInteger.compareAndSet(11 , 20));  // expect和旧值比较,如果相同,赋值,如果不相同,不赋值
//        System.out.println("==========" + atomicInteger.accumulateAndGet());  // expect和旧值比较,如果相同,赋值,如果不相同,不赋值
        //lambda表达式中参数operand表示AtomicInteger的当前值
//        System.out.println("==========" + atomicInteger.getAndUpdate(operand -> ++operand));  //先获取当前值,再传入当前值,计算,将计算结果赋值给atomic
//        System.out.println("==========" + atomicInteger.updateAndGet(operand -> ++operand));  //先计算,再赋值
        //lambda表达式中参数left表示AtomicInteger的当前值、right表示前面那个参数5
//        System.out.println("===========" + atomicInteger.getAndAccumulate(5, (left, right) -> left + right));
//        System.out.println("===========" + atomicInteger.getAndAccumulate(5, new IntBinaryOperator() {
//
//            /**
//             *
//             * @param left 表示AtomicInteger的当前值
//             * @param right 表示前面那个参数5
//             * @return 将结果赋值给atomic
//             */
//            @Override
//            public int applyAsInt(int left, int right) {
//                return 0;
//            }
//        }));
//        System.out.println("=========" + atomicInteger.accumulateAndGet(5, new IntBinaryOperator() {   //先计算,后赋值
//            @Override
//            public int applyAsInt(int left, int right) {
//                return 0;
//            }
//        }));
//
//
//        System.out.println("==========" + atomicInteger.get());

    }


    public void test2() {
        int[] initArray = new int[] {1 , 2};
        AtomicIntegerArray atomicIntegerArray = new AtomicIntegerArray(initArray);
        atomicIntegerArray.set(0 , 3);
        System.out.println("=========" + atomicIntegerArray.get(0));
        System.out.println("=========" + initArray[0]);
    }

    public void test3() {
        AtomicReference<UserInfo> userAtomicReference = new AtomicReference<>();
        userAtomicReference.set(new UserInfo("张三" , 2));
        userAtomicReference.set(new UserInfo("李四" , 3));
        System.out.println("=======" + userAtomicReference.get());
    }

    class Thread1 extends Thread {
        @Override
        public void run() {
            super.run();
            String reference = atomicStampedReference.getReference();
            oldStamped = atomicStampedReference.getStamp();
            System.out.println("====" + atomicStampedReference.compareAndSet(reference , reference + "爱洗脚" , oldStamped , oldStamped + 1));
        }
    }


    class Thread2 extends Thread {
        @Override
        public void run() {
            super.run();
            String reference = atomicStampedReference.getReference();
            System.out.println("====" + atomicStampedReference.compareAndSet(reference , reference + "爱洗脚" , oldStamped , oldStamped + 1));
        }
    }

    public void test4() {
        Thread1 thread1 = new Thread1();
        Thread2 thread2 = new Thread2();
        thread1.start();
        try {
            thread1.join();
            thread2.start();
            thread2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }


}
