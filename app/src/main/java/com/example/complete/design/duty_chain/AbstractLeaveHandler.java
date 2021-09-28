package com.example.complete.design.duty_chain;

public abstract class AbstractLeaveHandler {

    protected static final int LESS_DAY = 1;
    protected static final int MIDDLE_DAYS = 3;
    protected static final int MANY_DAYS = 10;

    protected String handlerName;

    protected AbstractLeaveHandler next;

    public void setNext(AbstractLeaveHandler next) {
        this.next = next;
    }

    protected abstract void handlerRequest(LeaveRequest request);

}
