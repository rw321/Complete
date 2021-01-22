package com.example.complete.utils;

import com.example.complete.bean.BaseEventInfo;

import org.greenrobot.eventbus.EventBus;

public class EventBusUtils {

    public static void register(Object subcriber) {
        EventBus.getDefault().register(subcriber);
    }

    public static void unregister(Object subcriber) {
        EventBus.getDefault().unregister(subcriber);
    }

    /**
     * 发送事件
     * @param info
     */
    public static void sendEvent(BaseEventInfo info) { EventBus.getDefault().post(info); }

    /**
     * 发送粘性事件
     * @param info
     */
    public static void sendStrickyEvent(BaseEventInfo info) { EventBus.getDefault().postSticky(info); }

    /**
     * 终止事件的分发
     * @param event
     */
    public static void cancelEventIntercept(Object event) { EventBus.getDefault().cancelEventDelivery(event); }

}
