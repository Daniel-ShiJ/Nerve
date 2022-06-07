package com.kingnet.nerve.performance.Traces;

import com.kingnet.nerve.performance.abs.BaseTracer;
import com.kingnet.nerve.performance.monitor.IMonitor.IMonitor;
import com.kingnet.nerve.performance.monitor.MemInfoMonitor;

/**
 * Author:Daniel.ShiJ
 * Date:2022/6/7 14:19
 * Description:
 */
public class MemInfoTrace extends BaseTracer {
    private IMonitor memInfoMonitor;
    public MemInfoTrace(){
        memInfoMonitor = new MemInfoMonitor();
    }

    @Override
    public void startTrace() {
        memInfoMonitor.startMonitor();
    }

    @Override
    public void stopTrace() {

    }
}
