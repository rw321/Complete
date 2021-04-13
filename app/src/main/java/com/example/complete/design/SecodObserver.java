package com.example.complete.design;

public class SecodObserver implements Observer {
    @Override
    public void update() {
        System.out.println("Second Observer is notified");
    }
}
