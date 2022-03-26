package com.kingnet.nerve.performance.monitor;

import com.kingnet.nerve.performance.monitor.IMonitor.IMonitor;

import java.lang.reflect.Method;

/**
 * Author:Daniel.ShiJ
 * Date:2022/3/25 17:16
 * Description:监听Frame
 * 参考 Tencent matrix 方案
 * idleHandler + Println + Choreographer反射
 */
public class KYFpsMonitor implements Runnable, IMonitor {
    private final static KYFpsMonitor sInstance = new KYFpsMonitor();


    private Method inputCallBackQueue;
    private Method animationCallBackQueue;
    private Method traversalCallBackQueue;

    private int inputCallBack;
    private int animationCallBack;
    private int traversalCallBack;

    private int type;


    public static KYFpsMonitor getMonitor() {
        return sInstance;
    }

    public KYFpsMonitor() {

    }

    private void init(){
        inputCallBackQueue = null;
        animationCallBackQueue = null;
        traversalCallBackQueue = null;
    }

    @Override
    public void run() {

    }

    @Override
    public void startMonitor() {

    }

    @Override
    public void stopMonitor() {

    }
}
