package com.kingnet.nerve.common.Enum;

/**
 * Author:Daniel.ShiJ
 * Date:2022/4/15 14:49
 * Description:
 */
public enum DataEnum {
    MEMORY(0x01),
    FRAME(0x03),
    DNS(0x05),
    NETWORK(0x08),
    CRASH(0x10),
    NATIVE_CRASH(0x20),
    LOG(0x40);

    private int mValue;

    public int value() {
        return mValue;
    }

    DataEnum(int value) {
        this.mValue = value;
    }
}
