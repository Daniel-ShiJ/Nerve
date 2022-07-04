package com.kingnet.nerve.performance.monitor;

import com.kingnet.nerve.performance.Listener.IBaseTracer;
import com.kingnet.nerve.performance.Listener.IMemInfoTracer;
import com.kingnet.nerve.performance.monitor.IMonitor.IMonitor;

import java.util.ArrayList;
import java.util.List;

/**
 * Author:Daniel.ShiJ
 * Date:2022/6/16 17:14
 * Description:
 */
public class CrashMonitor implements IMonitor<IBaseTracer> {
    private List<IBaseTracer<Throwable>> observers;
    @Override
    public void startMonitor() {
        CrashHandler crashHandler = new CrashHandler();
        Thread.setDefaultUncaughtExceptionHandler(crashHandler);
    }

    @Override
    public void stopMonitor() {

    }

    @Override
    public void addObserver(IBaseTracer tracer) {
        if(null == observers)
            observers = new ArrayList();
        observers.add(tracer);
    }


    public void notifyObserver(Throwable e){
        if(observers.isEmpty()) return;
        for (IBaseTracer observer : observers) {
            observer.writeData(e);
        }
    }


    class CrashHandler implements Thread.UncaughtExceptionHandler{

        protected CrashHandler(){
            Thread.setDefaultUncaughtExceptionHandler(this);
        }


        @Override
        public void uncaughtException(Thread t, Throwable e) {



            notifyObserver(e);
        }
    }
}


