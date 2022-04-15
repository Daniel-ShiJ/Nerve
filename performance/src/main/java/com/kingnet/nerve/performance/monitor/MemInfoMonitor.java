package com.kingnet.nerve.performance.monitor;

import android.os.Debug;
import android.util.DebugUtils;

import com.kingnet.nerve.performance.monitor.IMonitor.IMonitor;

/**
 * Author:Daniel.ShiJ
 * Date:2022/4/11 16:46
 * Description:内存监听
 * VSS/RSS/PSS/USS
 * /proc/meminfo
 * https://blog.csdn.net/weixin_35858450/article/details/117710051
 */
public class MemInfoMonitor implements IMonitor {
    @Override
    public void startMonitor() {
        Debug.MemoryInfo memoryInfo = new Debug.MemoryInfo();
        Debug.getMemoryInfo(memoryInfo);
        System.out.println("memoryInfo == " + memoryInfo.toString());
    }

    @Override
    public void stopMonitor() {

    }
}
