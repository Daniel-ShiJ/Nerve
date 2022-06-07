package com.kingnet.nerve.performance.monitor.IMonitor;

/**
 * Author:Daniel.ShiJ
 * Date:2022/3/25 17:47
 * Description:
 */
public interface IMonitor<T> {
    void startMonitor();

    void stopMonitor();

    void addObserver(T t);
}
