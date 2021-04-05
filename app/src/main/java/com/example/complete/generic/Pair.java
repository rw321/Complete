package com.example.complete.generic;

public class Pair<T> {

    private T one;
    private T two;

    public T getOne() {
        return one;
    }

    public void setOne(T one) {
        this.one = one;
    }

    public T getTwo() {
        return two;
    }

    public void setTwo(T two) {
        this.two = two;
    }
}
