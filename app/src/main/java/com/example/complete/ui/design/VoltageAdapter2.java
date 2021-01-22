package com.example.complete.ui.design;

/**
 * 对象适配器
 */
public class VoltageAdapter2 implements DestinationVoltage {

    private SrcVoltage mSrcVoltage;

    public VoltageAdapter2(SrcVoltage mSrcVoltage) {
        this.mSrcVoltage = mSrcVoltage;
    }

    @Override
    public int getDestinationVoltage() {
        System.out.println("Object adapter start working");
        int voltage = mSrcVoltage.getVoltage()/44;
        System.out.println("Adapter has finished");
        return voltage;
    }
}
