package com.example.complete.ui.design;

/**
 * 适配器类
 */
public class VoltageAdapter extends SrcVoltage implements DestinationVoltage {

    @Override
    public int getDestinationVoltage() {
        System.out.println("适配器开始工作");
        int voltage = getVoltage()/44;
        System.out.println("获取5V电压");
        return voltage;
    }
}
