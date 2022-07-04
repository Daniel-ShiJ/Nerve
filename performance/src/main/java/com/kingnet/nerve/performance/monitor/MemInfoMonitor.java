package com.kingnet.nerve.performance.monitor;

import android.os.Debug;

import com.kingnet.nerve.common.Config;
import com.kingnet.nerve.common.GsonUtils;
import com.kingnet.nerve.performance.Listener.IMemInfoTracer;
import com.kingnet.nerve.performance.monitor.IMonitor.IMonitor;
import com.kingnet.nerve.performance.unity.MemInfoBean;

import java.util.ArrayList;
import java.util.List;
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
    private List<IMemInfoTracer> observers;
    /**
     * 最后一次内存使用率
     */
    private float lastHeapInPercent = -1;
    private int currentTimes = 0;
    @Override
    public void startMonitor() {
        //触及阈值
        float heapInPercent = (Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory())/Runtime.getRuntime().totalMemory();

        if(heapInPercent > Config.getInstance().getThresholdMemInfoMax()){//触及最大阈值
            currentTimes = 0;
            notifyObserver(buildMemInfoJson());
            return;
        }

        if(heapInPercent > Config.getInstance().getThresholdMemInfo()){//触及阈值
            if(lastHeapInPercent == -1 || heapInPercent > lastHeapInPercent){
                currentTimes++;
            }else{
                currentTimes = 0;
            }
        }else{
            currentTimes = 0;
        }

        if(currentTimes >= Config.getInstance().getMemInfoAscendCount()){//连续上升达
            currentTimes = 0;
            notifyObserver(buildMemInfoJson());
            return;
        }
        lastHeapInPercent = heapInPercent;

//
//
//        File file = new File(BaseContext.getCon().getExternalCacheDir() + File.separator + ICacheManager.pathDir + DataEnum.MEMORY);
//
//        if (null != file) {
//            if (!file.exists()) {
//                boolean isSuccess = file.mkdirs();
//                if (!isSuccess) {
//                    throw new IllegalArgumentException("创建" + file.getAbsolutePath() + "失败！！！");
//                }
//            }
//        }

//
//        //下面需要考虑。
//        //因为 dumpHprofData，会暂停整个线程
//        /**
//         * Koom方案：触及阈值，native做fork出子线程，保存、裁剪hprof文件。
//         * 父进程挂起，等dump结束，再恢复。
//         * 1.xHook 替换 so 文件
//         * 2.shark 解析 hprof 文件
//         *
//         * */
//        File sourceFile = new File(file, DateUtils.getNow() +".hprof");
//        try {
//            Debug.dumpHprofData(sourceFile.getAbsolutePath());//这个过程会 “冻结” 整个应用进程，造成数秒甚至数十秒内用户无法操作，
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
    }

    @Override
    public void stopMonitor() {

    }

    public String buildMemInfoJson(){
        Debug.MemoryInfo memoryInfo = new Debug.MemoryInfo();
        Debug.getMemoryInfo(memoryInfo);
        Map map = memoryInfo.getMemoryStats();

        MemInfoBean memInfoBean = new MemInfoBean();
        memInfoBean.setMax(Runtime.getRuntime().maxMemory());
        memInfoBean.setTotal(Runtime.getRuntime().totalMemory());
        memInfoBean.setFree(Runtime.getRuntime().freeMemory());

        map.put("memInfoBean",GsonUtils.getGson().toJson(memInfoBean));

        return GsonUtils.getGson().toJson(map);
    }


    public void notifyObserver(String jsonData){
        if(observers.isEmpty()) return;
        for (IMemInfoTracer observer : observers) {
            observer.writeData(jsonData);
        }
    }

    @Override
    public void addObserver(IMemInfoTracer iMemInfoTracer) {
        if(null == iMemInfoTracer)return;
        if(null == observers)
            observers = new ArrayList<>();
        observers.add(iMemInfoTracer);
    }

}
