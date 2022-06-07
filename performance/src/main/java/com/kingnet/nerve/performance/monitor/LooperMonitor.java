package com.kingnet.nerve.performance.monitor;

import android.os.Looper;
import android.os.MessageQueue;
import android.text.TextUtils;
import android.util.Printer;

import com.kingnet.nerve.common.LogNerve;

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
        LogNerve.e("我是 queueIdle ");
        return true;
    }

    public void addObserver(LooperMonitorListener listener){
        if(null == listeners)
            listeners = new ArrayList();
        listeners.add(listener);
    }

    public void cleanObserver(){
//        if(null != listeners)
//            listeners.clear();
//        mLooper.setMessageLogging(null);
    }

    interface LooperMonitorListener{
        void dispatchStart(String message);
        void dispatchEnd();
    }


    class IdlePrintln implements Printer{
        boolean isHasChecked;
        boolean isValid;

        @Override
        public void println(String msg) {

            if(!isHasChecked){
                isValid = (!TextUtils.isEmpty(msg) && (msg.charAt(0) == '<' || msg.charAt(0) == '>'));
                isHasChecked = true;
            }

            if(!isValid) {
                LogNerve.e("无效的Printer信息:" + msg);
                return;
            }
            dispatch(msg.charAt(0) == '<',msg);
        }
    }


    private void dispatch(boolean isBegin, String msg) {
        for (int i = 0; i < listeners.size(); i++) {
            LooperMonitorListener listener = (LooperMonitorListener) listeners.get(i);
            if (isBegin) {
                listener.dispatchStart(msg);
            }else{
                listener.dispatchEnd();
            }

        }

    }
}
