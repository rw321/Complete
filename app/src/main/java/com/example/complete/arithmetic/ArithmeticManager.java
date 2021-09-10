package com.example.complete.arithmetic;

import java.util.List;

public class ArithmeticManager {

    /**
     * 冒泡排序 升序排序
     * @param data
     * @return
     */
    public static List<Integer> sort(List<Integer> data) {
        if (data == null || data.size() < 2)
            return data;
        for (int i = 0; i < data.size() - 1;i++) {
            for (int j = 0; j < data.size()-i - 1;j++) {
                if (data.get(j) > data.get(j+ 1)) {
                    int temp = data.get(j);
                    data.set(j , data.get(j+1));
                    data.set(j+1 , temp);
                }
            }
        }
        return data;

    }

}
