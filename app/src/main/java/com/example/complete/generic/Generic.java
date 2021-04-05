package com.example.complete.generic;

/**
 * 泛型类
 * @param <T>
 */
public class Generic<T> {

    private T data;

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
