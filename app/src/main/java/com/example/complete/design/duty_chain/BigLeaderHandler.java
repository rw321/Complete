package com.example.complete.design.duty_chain;

public class BigLeaderHandler extends AbstractLeaveHandler {
    @Override
    protected void handlerRequest(LeaveRequest request) {
        if (request.day <= MANY_DAYS) {
            System.out.println("big leader handle request");
            return;
        }

        if (next != null) {
            next.handlerRequest(request);
        }else {
            System.out.println("nobody handle request");
        }

    }
}
