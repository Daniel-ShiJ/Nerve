package com.kingnet.nerve.performance.monitor;

import android.os.SystemClock;
import android.view.Choreographer;

import com.kingnet.nerve.base.Utils.ReflectUtils;
import com.kingnet.nerve.performance.monitor.IMonitor.IMonitor;
import com.kingnet.nerve.performance.unity.FrameBean;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * Author:Daniel.ShiJ
 * Date:2022/3/25 17:16
 * Description:监听Frame
 * 参考 Tencent matrix 方案
 * idleHandler + Println + Choreographer反射
 */
public class KYFpsMonitor implements Runnable, IMonitor {
    private final static KYFpsMonitor sInstance = new KYFpsMonitor();


    private Method inputCallBackQueue;
    private Method animationCallBackQueue;
    private Method traversalCallBackQueue;
    private Choreographer mChoreographer;

    /**
     * 是否初始化
     */
    private boolean mInit;

    private static final String REFLECT_METHOD_NAME = "addCallbackLocked";
    private final int INPUT_CALLBACK = 0;
    private final int ANIMATION_CALLBACK = INPUT_CALLBACK + 1;
    private final int TRAVERSAL_CALLBACK = ANIMATION_CALLBACK + 1;

    private Object[] callbackQueues;
    private final int DO_QUEUE_DEFAULT = 0;
    private final int DO_QUEUE_BEGIN = 1;
    private final int DO_QUEUE_END = 2;
    private FrameBean[] mItemFrame = new FrameBean[TRAVERSAL_CALLBACK + 1];

    public static KYFpsMonitor getMonitor() {
        return sInstance;
    }

    public KYFpsMonitor() {
        for (int i = 0; i < mItemFrame.length; i++) {
            mItemFrame[i] = new FrameBean();
        }
    }

    public void init(){
        mInit = true;
        mChoreographer = Choreographer.getInstance();
        callbackQueues = ReflectUtils.reflectObject(mChoreographer, "mCallbackQueues", null);
        if(null != callbackQueues) {
            inputCallBackQueue = ReflectUtils.reflectMethod(callbackQueues[INPUT_CALLBACK], REFLECT_METHOD_NAME, long.class, Object.class, Object.class);
            animationCallBackQueue = ReflectUtils.reflectMethod(callbackQueues[ANIMATION_CALLBACK], REFLECT_METHOD_NAME, long.class, Object.class, Object.class);
            traversalCallBackQueue = ReflectUtils.reflectMethod(callbackQueues[TRAVERSAL_CALLBACK], REFLECT_METHOD_NAME, long.class, Object.class, Object.class);
        }

        LooperMonitor.getInstance().addObserver(new LooperMonitor.LooperMonitorListener() {
            @Override
            public void dispatchStart(String message) {
                System.out.println("过来了 == " + message);
            }

            @Override
            public void dispatchEnd() {
                doFrameEnd();
            }
        });
    }

    @Override
    public void run() {
        System.out.println("我收到frame，回来了！！！");
        long startTime =  System.nanoTime();//开始时间

        //在Choreographer，执行doFrame函数时，会顺序执行三个type的doCallbacks、
        //所以，这里，每执行完一次，就添加callBack到下一个type的头部
        //通过计算时间差，就可以得到每一次花费的时间
        doCallBackQueueBegin(INPUT_CALLBACK);//记录input开始时间
        addFrameCallBack(ANIMATION_CALLBACK, new Runnable() {
            @Override
            public void run() {
                doCallBackQueueEnd(INPUT_CALLBACK);
                doCallBackQueueBegin(ANIMATION_CALLBACK);
            }
        },true);
        addFrameCallBack(TRAVERSAL_CALLBACK, new Runnable() {
            @Override
            public void run() {
                doCallBackQueueEnd(ANIMATION_CALLBACK);
                doCallBackQueueBegin(TRAVERSAL_CALLBACK);
            }
        },true);
    }

    private void doCallBackQueueBegin(int callBackType) {
        mItemFrame[callBackType].setStatus(DO_QUEUE_BEGIN);
        mItemFrame[callBackType].setCost(System.nanoTime());
    }

    private void doCallBackQueueEnd(int callBackType){
        mItemFrame[callBackType].setStatus(DO_QUEUE_END);
        mItemFrame[callBackType].setCost(System.nanoTime() - mItemFrame[callBackType].getCost());
    }

    /**
     * 帧开始
     */
    private void doFrameBegin() {

    }

    /**
     * 帧结束
     */
    private void doFrameEnd(){
        doCallBackQueueEnd(TRAVERSAL_CALLBACK);


        for (int i = 0; i < mItemFrame.length; i++) {
            System.out.println("frameBean["+i+"]耗时 = "+mItemFrame[i].getCost());
        }


        addFrameCallBack(INPUT_CALLBACK,this,true);//再次添加callBack
    }


    @Override
    public void startMonitor() {
        addFrameCallBack(INPUT_CALLBACK,this,true);
    }

    @Override
    public void stopMonitor() {

    }

    private void addFrameCallBack(int type,Runnable callBack,boolean isAddHeader) {
        Method method = null;
        switch (type){
            case INPUT_CALLBACK:
                method = inputCallBackQueue;
                break;
            case ANIMATION_CALLBACK:
                method = animationCallBackQueue;
                break;
            case TRAVERSAL_CALLBACK:
                method = traversalCallBackQueue;
                break;
        }
        if(null == method)
            return;
        //Choreographer.addCallbackLocked(long dueTime, Object action, Object token) {
        try {
            method.invoke(callbackQueues[type],isAddHeader?-1: SystemClock.uptimeMillis(),callBack,null);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }

    }

}
