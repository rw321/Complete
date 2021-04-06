package com.example.complete.design;

public class MessageProxy implements Message{

    MessageImpl mMessageImpl;

    public MessageProxy(MessageImpl mMessageImpl) {
        this.mMessageImpl = mMessageImpl;
    }

    @Override
    public void send() {
        System.out.println("发消息前.......");
        mMessageImpl.send();
        System.out.println("发消息后......");
    }
}
