package com.example.complete.arithmetic;

import java.util.List;

/**
 * 归并排序
 */
public class MargeSort {

    public static void sort(List<Integer> originData , int start , int end , List<Integer> temp){
        if (start < end) {
            sort(originData , start , (start + end)/2 , temp);
            sort(originData , (start + end)/2 + 1 , end , temp);
            merge(originData , start , end , temp);
        }
    }

    private static void merge(List<Integer> originData , int start , int end , List<Integer> temp){
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
