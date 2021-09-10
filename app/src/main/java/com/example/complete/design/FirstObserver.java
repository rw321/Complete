package com.example.complete.design;

public class FirstObserver implements Observer {
    @Override
    public void update() {
        System.out.println("first Observer is notified");
    }
}
