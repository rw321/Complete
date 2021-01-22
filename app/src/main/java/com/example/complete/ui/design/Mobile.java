package com.example.complete.ui.design;

/**
 * 客户端类
 */
public class Mobile {

    public void work(DestinationVoltage voltage) {
        if (voltage.getDestinationVoltage() == 5) {
            System.out.println("the mobile is working under five voltage");
        }else {
            System.out.println("Voltage is not suited");
        }
    }
}
