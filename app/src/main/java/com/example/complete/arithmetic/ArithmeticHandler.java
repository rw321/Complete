package com.example.complete.arithmetic;

import com.example.complete.databinding.ActivityArithmeticBinding;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class ArithmeticHandler {

    private List<Integer> mOriginData;

    private ActivityArithmeticBinding mBinding;

    Random random = new Random();

    public ArithmeticHandler(ActivityArithmeticBinding mBinding) {
        this.mBinding = mBinding;
    }

    public void createRandomData() {
        mOriginData = new ArrayList<>();
        while (mOriginData.size() < 10) {
            int value = random.nextInt(100);
            if (mOriginData.contains(value))
                continue;
            mOriginData.add(value);
        }

        StringBuilder stringBuilder = new StringBuilder();

        for (int i = 0; i < mOriginData.size();i++) {
            if (i == mOriginData.size() - 1) {
                stringBuilder.append(mOriginData.get(i));
            }else {
                stringBuilder.append(mOriginData.get(i) + " , ");
            }

        }

        mBinding.tvOriginData.setText(stringBuilder);
    }

    public void createRandomValue(){
        mBinding.tvOriginData.setText(random.nextInt(100) + "");
    }

    public void sort(){
        List<Integer> result = new ArrayList<>();
        sort(mOriginData , 0 , mOriginData.size() - 1 , result);
        StringBuilder stringBuilder = new StringBuilder();

        for (int i = 0; i < result.size();i++) {
            if (i == result.size() - 1) {
                stringBuilder.append(result.get(i));
            }else {
                stringBuilder.append(result.get(i) + " , ");
            }

        }

        mBinding.tvSortData.setText(stringBuilder);
    }

    public void sort(List<Integer> originData , int start , int end , List<Integer> temp){
        if (start < end) {
            sort(originData , start , (start + end)/2 , temp);
            sort(originData , (start + end)/2 + 1 , end , temp);
            merge(originData , start , end , temp);
        }
    }

    private void merge(List<Integer> originData , int start , int end , List<Integer> temp){
        temp.clear();
        int i = start;
        int mid = (start + end)/2;
        int j = mid + 1;

        while (i<=mid && j<=end){
            if(originData.get(i)<=originData.get(j)){
                temp.add(originData.get(i));
                i++;
            }else {
                temp.add(originData.get(j));
                j++;
            }
        }
        while(i<=mid){//将左边剩余元素填充进temp中
            temp.add(originData.get(i));
            i++;
        }
        while(j<=end){//将右序列剩余元素填充进temp中
            temp.add(originData.get(j));
            j++;
        }

        for (int k = 0; k < temp.size();k++) {
            originData.set(k+ start , temp.get(k));
        }


    }

}
