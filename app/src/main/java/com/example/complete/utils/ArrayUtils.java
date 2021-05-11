package com.example.complete.utils;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class ArrayUtils {

    /**
     * 截取数组
     * @param mData
     * @param start
     * @param end
     * @return
     */
    public static List<Integer> subList(List<Integer> mData, int start, int end){
        synchronized (ArrayUtils.class) {
            if (mData == null || mData.isEmpty() || start < 0 || end > mData.size())
                return mData;
            List<Integer> sub = new ArrayList<>();
            for (int i = start; i < end;i++) {
                sub.add(mData.get(i));
            }
            return sub;
        }

    }

    /**
     * 产生一组随机的int型数据
     * @param count
     */
    public static List<Integer> createRandomData(int count) {
        if (count <= 0)
            return null;
        List<Integer> data = new ArrayList<>();
        Random random = new Random();
        while (data.size() < count) {
            int value = random.nextInt(100);
            if (data.contains(value))
                continue;
            data.add(value);
        }
        return data;
    }

    /**
     * 打印Int型集合数据
     * @param data
     */
    public static void printArray(List<Integer> data) {
        if (data == null || data.isEmpty())
            return;
        StringBuilder stringBuilder = new StringBuilder();

        for (int i = 0; i < data.size();i++) {
            if (i == data.size() - 1) {
                stringBuilder.append(data.get(i));
            }else {
                stringBuilder.append(data.get(i) + " , ");
            }
        }

        System.out.println(stringBuilder);

    }

}
