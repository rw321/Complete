package com.example.complete.generic;

/**
 * 泛型方法
 */
public class GenericMethod {

    public <T>T function(T t) {
        return t;
    }

    /**
     * 限定类型作用在方法上
     * @param a
     * @param b
     * @param <T>
     * @return
     */
    public <T extends Comparable>T compare(T a , T b) {
        return a.compareTo(b) > 0 ? a : b;
    }

}
