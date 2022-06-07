package com.kingnet.nerve.performance.monitor;

import android.os.Debug;

import com.kingnet.nerve.common.GsonUtils;
import com.kingnet.nerve.common.LogNerve;
import com.kingnet.nerve.performance.Listener.IMemInfoTracer;
import com.kingnet.nerve.performance.monitor.IMonitor.IMonitor;

import java.util.HashSet;
import java.util.Map;

/**
 * Author:Daniel.ShiJ
 * Date:2022/4/11 16:46
 * Description:内存监听
 * VSS/RSS/PSS/USS
 * /proc/meminfo
 * VSS – Virtual Set Size 虚拟耗用内存(包含共享库占用的内存)
 *
 * RSS – Resident Set Size 实际使用物理内存(包含共享库占用的内存)
 *
 * PSS – Proportional Set Size 实际使用的物理内存(比例分配共享库占用的内存)
 *
 * USS – Unique Set Size 进程独自占用的物理内存(不包含共享库占用的内存)
 *
 * USS 是针对某个进程开始有可疑内存泄露的情况， 是一个程序启动了会产生的虚拟内存，一旦这个程序进程杀掉就会释放！
 *
 * Item全称含义等价
 *
 * USSUnique Set Size物理内存进程独占的内存
 *
 * PSSProportional Set Size物理内存PSS= USS+ 按比例包含共享库
 *
 * RSSResident Set Size物理内存RSS= USS+ 包含共享库
 *
 * VSSVirtual Set Size虚拟内存VSS= RSS+ 未分配实际物理内存
 */
public class MemInfoMonitor implements IMonitor<IMemInfoTracer> {
    private HashSet<IMemInfoTracer> hashSet = new HashSet();
    @Override
    public void startMonitor() {
        Debug.MemoryInfo memoryInfo = new Debug.MemoryInfo();
        Debug.getMemoryInfo(memoryInfo);
        Map map = memoryInfo.getMemoryStats();
        String memoryDetails = GsonUtils.getGson().toJson(memoryInfo);
        map.put("details",memoryDetails);
        LogNerve.e("memoryInfo == " + GsonUtils.getGson().toJson(map));
    }

    @Override
    public void stopMonitor() {

    }

    @Override
    public void addObserver(IMemInfoTracer iMemInfoTracer) {
        hashSet.add(iMemInfoTracer);
    }


}
