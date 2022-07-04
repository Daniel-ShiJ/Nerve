package com.kingnet.nerve.performance.abs;

import com.kingnet.nerve.common.Enum.DataEnum;
import com.kingnet.nerve.common.cache.CacheManager;
import com.kingnet.nerve.common.cache.ICacheManager;
import com.kingnet.nerve.performance.Listener.ITracer;
import com.kingnet.nerve.performance.monitor.IMonitor.IMonitor;

/**
 * Author:Daniel.ShiJ
 * Date:2022/3/25 17:07
 * Description:所有Tracer的父类
 */
public abstract class BaseTracer implements ITracer {
    protected IMonitor mMonitor;
    private ICacheManager cacheManager = CacheManager.getInstance();

    public void saveData(DataEnum dataEnum, byte[] bytes){
        cacheManager.pushData(dataEnum,bytes);
    }

    public void saveData(DataEnum dataEnum, Throwable t){
        CacheManager.getInstance().pushDataPw(dataEnum,t);
    }

    @Override
    public void startTrace() {
        mMonitor.startMonitor();
    }

    @Override
    public void stopTrace() {
        mMonitor.stopMonitor();
    }

}
