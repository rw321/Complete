package com.example.complete.thread;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinTask;
import java.util.concurrent.RecursiveTask;

public class ForkJoinTest {

    private static final Integer MAX = 100;

    static class MyTask extends RecursiveTask<Integer> {

        private int mStartIndex;
        private int mEndIndex;
        private List<Integer> data;

        public MyTask(List<Integer> data , int mStartIndex, int mEndIndex) {
            this.data = data;
            this.mStartIndex = mStartIndex;
            this.mEndIndex = mEndIndex;
        }


        @Override
        protected Integer compute() {

            if (mEndIndex - mStartIndex < MAX) {
                Integer total = 0;
                for (int i = mStartIndex ; i <= mEndIndex;i++) {
                    total += data.get(i);
                }
                return total;
            }else {
                MyTask firstTask = new MyTask(data , mStartIndex , (mStartIndex + mEndIndex)/2);
                firstTask.fork();
                MyTask secondTask = new MyTask(data , (mStartIndex + mEndIndex)/2 + 1 , mEndIndex);
                secondTask.fork();
                return firstTask.join() + secondTask.join();   //join将结果递归出去
            }

        }
    }

    public void test() {
        ForkJoinPool pool = new ForkJoinPool();
        List<Integer> data = getData();
        //pool.invoke(new MyTask(data , 0 , data.size()- 1));  //invoke同步执行
        //pool.execute();  同样是异步执行,无返回结果
        ForkJoinTask<Integer> taskFuture = pool.submit(new MyTask(data , 0 , data.size() - 1));   //submit异步执行
        try {
            Integer result = taskFuture.get();
            System.out.println("result=========" + result);
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private List<Integer> getData() {
        List<Integer> data = new ArrayList<>();
        for (int i = 0; i < 1000; i++) {
            data.add((int)(100*Math.random()));
        }
        return data;
    }

}
