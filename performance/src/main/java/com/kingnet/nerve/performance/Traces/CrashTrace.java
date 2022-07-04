package com.kingnet.nerve.performance.Traces;

import com.kingnet.nerve.common.Enum.DataEnum;
import com.kingnet.nerve.performance.Listener.IBaseTracer;
import com.kingnet.nerve.performance.abs.BaseTracer;
import com.kingnet.nerve.performance.monitor.CrashMonitor;

/**
 * Author:Daniel.ShiJ
 * Date:2022/6/16 17:26
 * Description:
 */
public class CrashTrace extends BaseTracer implements IBaseTracer<Throwable> {
    public CrashTrace() {
        mMonitor = new CrashMonitor();
        mMonitor.addObserver(this);
    }

    @Override
    public void writeData(Throwable throwable) {
        saveData(DataEnum.CRASH,throwable);
    }
}
