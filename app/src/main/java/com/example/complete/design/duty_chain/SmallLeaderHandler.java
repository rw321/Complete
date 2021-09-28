package com.example.complete.design.duty_chain;

public class SmallLeaderHandler extends AbstractLeaveHandler {
    @Override
    protected void handlerRequest(LeaveRequest request) {
        if (request.day <= LESS_DAY) {
            System.out.println("small leader handle request");
            return;
        }

        if (next != null) {
            next.handlerRequest(request);
        }else {
            System.out.println("nobody handle request");
        }

    }
}
