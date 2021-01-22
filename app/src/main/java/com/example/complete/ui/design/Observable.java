package com.example.complete.ui.design;

import java.util.ArrayList;
import java.util.List;

public class Observable {

    List<Observer> mObservers = new ArrayList<>();

    protected void registerObserver(Observer observer) {
        if (observer == null) {
            throw new NullPointerException("Observer can not be null");
        }
        synchronized (mObservers) {
            if (mObservers.contains(observer)) {
                throw new IllegalArgumentException("Observer has registered");
            }
            mObservers.add(observer);
        }
    }

    protected void unRegisterObserver(Observer observer) {
        if (observer == null) {
            throw new NullPointerException("Observer can not be null");
        }
        synchronized (mObservers) {
            int index = mObservers.indexOf(observer);
            if (index != -1) {
                mObservers.remove(index);
            }
        }
    }

    protected void clearAllObserver() {
        synchronized (mObservers) {
            mObservers.clear();
        }
    }

    public void update() {
        for (Observer observer:mObservers) {
            observer.update();
        }
    }

}
