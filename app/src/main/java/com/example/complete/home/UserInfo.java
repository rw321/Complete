package com.example.complete.home;

import androidx.databinding.BaseObservable;
import androidx.databinding.Bindable;

public class UserInfo extends BaseObservable {

    @Bindable
    private String userName;

    @Bindable
    private int age;

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public UserInfo(String userName) {
        this.userName = userName;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
        notify();
    }
}
