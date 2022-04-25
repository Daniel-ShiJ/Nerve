package com.kingnet.nerve.performance.monitor;

import android.os.Looper;
import android.os.MessageQueue;
import android.util.Printer;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Author:Daniel.ShiJ
 * Date:2022/3/30 14:38
 * Description:
 * 利用IdleHandler机制以及Println
 */
public class LooperMonitor implements MessageQueue.IdleHandler {
    private static final String TAG = "Nerve.LooperMonitor";
    private Looper mLooper;
    private IdlePrintln mPrintln;
    private List listeners;

    private LooperMonitor(Looper looper) {
        this.mLooper = looper;
        mPrintln = new IdlePrintln();
        mLooper.setMessageLogging(mPrintln);
        mLooper.getQueue().addIdleHandler(this);
    }

    private static class LooperMonitorInstance{
        public static LooperMonitor looperMonitor = new LooperMonitor(Looper.getMainLooper());
    }

    public static LooperMonitor getInstance(){
        return LooperMonitorInstance.looperMonitor;
    }

    @Override
    public boolean queueIdle() {
        System.out.println("我是 queueIdle ");
        return false;
    }

    public void addObserver(LooperMonitorListener listener){
        if(null == listeners)
            listeners = new ArrayList();
        listeners.add(listener);
    }




    interface LooperMonitorListener{
        void dispatchStart(String message);
        void dispatchEnd();
    }


    class IdlePrintln implements Printer{

        @Override
        public void println(String x) {
            System.out.println("x == " + x);
            for (int i = 0; i < listeners.size(); i++) {
                LooperMonitorListener listener = (LooperMonitorListener) listeners.get(i);

                listener.dispatchStart(x);
            }
        }
    }
}
