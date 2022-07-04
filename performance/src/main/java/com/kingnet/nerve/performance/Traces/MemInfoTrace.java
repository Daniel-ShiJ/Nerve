package com.kingnet.nerve.performance.Traces;

import android.os.HandlerThread;
import android.text.TextUtils;

import com.kingnet.nerve.common.Enum.DataEnum;
import com.kingnet.nerve.performance.Listener.IMemInfoTracer;
import com.kingnet.nerve.performance.abs.BaseTracer;
import com.kingnet.nerve.performance.monitor.MemInfoMonitor;

/**
 * Author:Daniel.ShiJ
 * Date:2022/6/7 14:19
 * Description:
 */
public class MemInfoTrace extends BaseTracer implements IMemInfoTracer {
    private HandlerThread handlerThread;
    public MemInfoTrace(){
        mMonitor = new MemInfoMonitor();
        mMonitor.addObserver(this);

//        handlerThread = new HandlerThread("memory_trace");
//        handlerThread.start();

//        Handler handler = new Handler(handlerThread.getLooper(), new Handler.Callback() {
//            @Override
//            public boolean handleMessage(Message msg) {
//                return false;
//            }
//        });
    }

    @Override
    public void writeData(String json) {
        if (TextUtils.isEmpty(json))
            return;
        saveData(DataEnum.MEMORY, json.getBytes());
    }
}
