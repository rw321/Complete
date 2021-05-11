package com.example.complete.thread;

import com.example.complete.arithmetic.ArithmeticManager;
import com.example.complete.utils.ArrayUtils;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveAction;
import java.util.concurrent.RecursiveTask;

/**
 * 用fork_join实现归并排序
 */
public class ForkJoinHomeWork {

    private static final int MIN = 2;

    class MyTask extends RecursiveAction {


        private List<Integer> mData;
        private int start;
        private int end;

        public MyTask(List<Integer> mData, int start, int end) {
            this.mData = mData;
            this.start = start;
            this.end = end;
        }

        @Override
        protected void compute() {
            if (end - start > MIN) {
                List<Integer> temp = ArrayUtils.subList(mData , start , end + 1);
                ArithmeticManager.sort(temp);
                updateArray(mData , start , end , temp);
            }else {
                int mid = (start + end)/2;
                MyTask task1 = new MyTask(mData , start , mid);
                MyTask task2 = new MyTask(mData , mid + 1 , end);
                invokeAll(task1 , task2);
                merge(mData , start , end);
            }
        }
    }

    /**
     * 归并
     * @param mData
     * @param start
     * @param end
     */
    private void merge(List<Integer> mData, int start, int end){
        if (mData == null || mData.isEmpty() || start < 0 || end > mData.size())
            return;
        List<Integer> temp = new ArrayList<>();
        int i = start;
        int mid = (start + end)/2;
        int j = mid + 1;

        while (i<=mid && j<=end){
            if(mData.get(i)<=mData.get(j)){
                temp.add(mData.get(i));
                i++;
            }else {
                temp.add(mData.get(j));
                j++;
            }
        }
        while(i<=mid){//将左边剩余元素填充进temp中
            temp.add(mData.get(i));
            i++;
        }
        while(j<=end){//将右序列剩余元素填充进temp中
            temp.add(mData.get(j));
            j++;
        }

        for (int k = 0; k < temp.size();k++) {
            mData.set(k+ start , temp.get(k));
        }

    }

    private synchronized List<Integer> updateArray(List<Integer> mData, int start, int end , List<Integer> temp) {
        if (mData == null || mData.isEmpty() || start < 0 || end > mData.size()|| temp == null || temp.size() < (end - start + 1))
            return mData;
        for (int i = 0; i < temp.size();i++) {
            mData.set(i + start , temp.get(i));
        }
        return mData;
    }

    public void test() {
        ForkJoinPool pool = new ForkJoinPool();
        List<Integer> data = ArrayUtils.createRandomData(20);
        MyTask task = new MyTask(data , 0 , data.size() - 1);
        pool.invoke(task);
        ArrayUtils.printArray(data);
    }

}
