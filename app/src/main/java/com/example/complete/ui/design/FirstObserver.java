package com.example.complete.ui.design;

public class FirstObserver implements Observer {
    @Override
    public void update() {
        System.out.println("first Observer is notified");
    }
}
