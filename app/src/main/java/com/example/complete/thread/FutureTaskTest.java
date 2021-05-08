package com.example.complete.thread;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

public class FutureTaskTest {

    static class MyCallable implements Callable<Integer>{

        @Override
        public Integer call() throws Exception {

            int sum = 0;
            for (int i = 0; i < 100;i++) {
                sum += i;
            }

            return sum;
        }
    }

    public void test(){
        FutureTask<Integer> futureTask = new FutureTask<>(new MyCallable());
        new Thread(futureTask).start();
        try {
            Integer result = futureTask.get();
            System.out.println("result ======== " + result);
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
