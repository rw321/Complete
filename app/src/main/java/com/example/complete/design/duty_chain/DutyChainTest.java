package com.example.complete.design.duty_chain;

/**
 * 责任链模式测试
 */
public class DutyChainTest {

    public static void main(String[] args) {
        LeaveRequest request = new LeaveRequest(15 , "Alex");
        SmallLeaderHandler small = new SmallLeaderHandler();
        MiddleLeaderHandler middle = new MiddleLeaderHandler();
        BigLeaderHandler big = new BigLeaderHandler();
        small.setNext(middle);
        middle.setNext(big);
        small.handlerRequest(request);
    }

}
