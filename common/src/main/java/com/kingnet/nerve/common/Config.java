package com.kingnet.nerve.common;

/**
 * Author:Daniel.ShiJ
 * Date:2022/4/15 15:30
 * Description:服务器 动态获取配置内容
 */
public final class Config {
    private static volatile Config config;
    public static synchronized Config getInstance(){
        if(null == config){
            synchronized (Config.class){
                if (null == config)
                    config = new Config();
            }
        }
        return config;
    }
    private Config(){}

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
     * 上传节点
     */
    byte uploadTiming;

    /**
     * 阈值
     * 默认 5M
     */
    int threshold = 1024 * 1024 * 5;

    /**
     * 忽略阈值
     */
    int ignoreThreshold;

    boolean isDebug = true;

    public void setDataType(byte mDataType) {
        this.mDataType = mDataType;
    }

    /**
     * 是否采集内存信息
     * @return
     */
    public boolean isTraceMemInfo(){
        return (mDataType & 0x0F) == 0x01;
    }

    /**
     * 是否采集Frame数据
     * @return
     */
    public boolean isTraceFRAME(){
        return (mDataType & 0x0F) == 0x03;
    }

    /**
     * 是否采集DNS数据
     * @return
     */
    public boolean isTraceDNS(){
        return (mDataType & 0x0F) == 0x05;
    }

    /**
     * 是否采集网络数据
     * @return
     */
    public boolean isTraceNetWork(){
        return (mDataType & 0x0F) == 0x08;
    }

    /**
     * 是否采集Java层 Crash数据
     * @return
     */
    public boolean isTraceCrash(){
        return (mDataType & 0xF0) == 0x10;
    }

    /**
     * 是否采集Native层 Crash数据
     * @return
     */
    public boolean isTraceNativeCrash(){
        return (mDataType & 0xF0) == 0x20;
    }

    /**
     * 是否采集Log数据
     * @return
     */
    public boolean isTraceLog(){
        return (mDataType & 0xF0) == 0x40;
    }

    public byte getDataType() {
        return mDataType;
    }
}
