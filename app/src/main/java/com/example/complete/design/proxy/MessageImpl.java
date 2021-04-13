package com.example.complete.design.proxy;

import com.example.complete.design.proxy.Message;

public class MessageImpl implements Message {
    @Override
    public void send() {
        System.out.println("发消息了.......");
    }
}
