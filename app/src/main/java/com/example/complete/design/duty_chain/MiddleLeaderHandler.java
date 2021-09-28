package com.example.complete.design.duty_chain;

public class MiddleLeaderHandler extends AbstractLeaveHandler {
    @Override
    protected void handlerRequest(LeaveRequest request) {
        if (request.day <= MIDDLE_DAYS) {
            System.out.println("middle leader handle request");
            return;
        }

        if (next != null) {
            next.handlerRequest(request);
        }else {
            System.out.println("nobody handle request");
        }

    }
}
