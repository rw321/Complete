package com.example.complete.thread;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Exchanger;

public class ExchangeTest {

    private static Exchanger<List<Integer>> exchanger = new Exchanger<>();

    static class Thread1 extends Thread{
        @Override
        public void run() {
            super.run();
            List<Integer> list = new ArrayList<>();
            for (int i = 0; i  < 50;i++) {
                list.add(i);
            }
            try {
                List<Integer> result = exchanger.exchange(list);
                System.out.println("result==1====" + result.get(0));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    static class Thread2 extends Thread{
        @Override
        public void run() {
            super.run();
            List<Integer> list = new ArrayList<>();
            for (int i = 50; i  < 100;i++) {
                list.add(i);
            }
            try {
                List<Integer> result = exchanger.exchange(list);
                System.out.println("result===2===" + result.get(0));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public void test(){
        new Thread1().start();
        new Thread2().start();
    }

}
