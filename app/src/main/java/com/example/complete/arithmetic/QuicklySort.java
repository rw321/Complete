package com.example.complete.arithmetic;

/**
 * 快排(升序)
 *
 * 算法:遍历数组,将数组中的元素和基准数进行比较,为了满足所有比基准数小的都放到基准数的前面,
 * 所有比基准数大的都放到基准数的后面,我们需要遵循这样的规则:
 * 1,当前元素大于基准数,我们不做任何变化
 * 2,当前元素小于等于基准数时,分割指示器右移一位,当前元素下标小于等于分割指示器时,当前元素保持不变
 *  当前元素下标大于分割指示器时,将当前元素和分割指示器元素交换
 *
 * 因为基准数的选择决定了遍历次数,所以一般取数据的第一个元素 , 最后一个元素 , 元素的中间值作为基准数
 *
 */
public class QuicklySort {

    /**
     *
     * @param start
     * @param end 最后一个元素的索引
     */
    public static void sort(int[] data , int start , int end){
        if (start < 0 || end >= data.length || start > end) {
            return;
        }

        //分割指示器
        int zoneIndex = partition(data , start , end);

        if (zoneIndex > start) {
            sort(data , start , zoneIndex - 1);
        }

        if(zoneIndex < end) {
            sort(data , zoneIndex + 1 , end);
        }

    }

    private static int partition(int[] data , int start, int end) {

        int zoneIndex = start - 1;
        for (int i = start;i <= end;i++) {
            if (data[i] <= data[end]) {
                zoneIndex++;
                if (i > zoneIndex) {
                    swap(data , i , zoneIndex);
                }
            }
        }
        return zoneIndex;
    }

    public static void swap(int[] data , int i , int j) {
        int temp = data[i];
        data[i] = data[j];
        data[j] = temp;
    }


}
