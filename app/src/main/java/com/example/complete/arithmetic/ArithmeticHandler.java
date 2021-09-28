package com.example.complete.arithmetic;

import com.example.complete.databinding.ActivityArithmeticBinding;

import java.util.Arrays;
import java.util.Random;

public class ArithmeticHandler {

    private int mDataSize = 10;
    private int[] data;

    private ActivityArithmeticBinding mBinding;

    Random random = new Random();

    public ArithmeticHandler(ActivityArithmeticBinding mBinding) {
        this.mBinding = mBinding;
    }

    public void createRandomData() {
        data = new int[mDataSize];

        int index = 0;

        while(index < mDataSize) {
            int value = random.nextInt(100);
            if (Arrays.asList(data).contains(value))
                continue;
            data[index] = value;
            index++;

        }
        printResult();
    }

    public void quicklySort(){
        QuicklySort.sort(data , 0 , data.length - 1);
        printResult();
    }

    public void printResult() {
        StringBuilder stringBuilder = new StringBuilder();

        for (int i = 0; i < data.length;i++) {
            stringBuilder.append(data[i] + " , ");
        }

        mBinding.tvSortData.setText(stringBuilder);
    }

}
