package com.kingnet.nerve;

import android.util.LogPrinter;

/**
 * Author:Daniel.ShiJ
 * Date:2022/4/15 15:30
 * Description:服务器 动态获取配置内容
 */
public final class Config {
    /**
     * 需要上传的数据种类
     * 0:不需要
     * -1:所有
     * 0000 0001 MEMORY
     * 0000 0010 FRAME
     * 0000 0100 DNS
     * 0000 1000 NETWORK
     * 0001 0000 CRASH
     * 0010 0000 NATIVE_CRASH
     * 0100 0000 LOG
     * ...
     */
    byte mDataType;
    /**
     * 上传时机
     */
    byte uploadTiming;
}
