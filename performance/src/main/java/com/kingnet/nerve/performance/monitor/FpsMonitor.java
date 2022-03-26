package com.kingnet.nerve.performance.monitor;

import android.os.Handler;
import android.os.Looper;
import android.util.SparseArray;
import android.view.Choreographer;

import com.kingnet.nerve.performance.monitor.IMonitor.IMonitor;


/**
 * Author:Daniel.ShiJ
 * Date:2022/3/22 13:56
 * Description:参考 滴滴 Dokit方案
 * 自定义Runnable + Choreographer的postFrameCallback
 */
public class FpsMonitor implements IMonitor {
    private final long mIntervalTime = 1000;//间隔时间
    private int pfs;//帧数
    private boolean isFpsOpen;//是否打开fps统计
    private Handler mHandler;//事件处理
    private FPSCallBackAndRunnable mCallBackAndRunnable;//实现Choreographer.frameCallBack
    private SparseArray<FpsListener> fpsListener;//监听


    private long mTime;

    public FpsMonitor() {
        fpsListener = new SparseArray<>();
        mCallBackAndRunnable = new FPSCallBackAndRunnable();
        mHandler = new Handler(Looper.myLooper());
    }

    public void startMonitor(){

    }

    public void startMonitor(FpsListener listener){
        if(isFpsOpen)
            return;
        isFpsOpen = true;
        fpsListener.append(fpsListener.size(), listener);
        mHandler.postDelayed(mCallBackAndRunnable,mIntervalTime);
        mTime = System.nanoTime();
        Choreographer.getInstance().postFrameCallback(mCallBackAndRunnable);

    }

    public void stopMonitor(){
        pfs = 0;
        mHandler.removeCallbacks(mCallBackAndRunnable);
        Choreographer.getInstance().removeFrameCallback(mCallBackAndRunnable);
        isFpsOpen = false;
    }

    long tempTime;

    class FPSCallBackAndRunnable implements Choreographer.FrameCallback,Runnable {
        @Override
        public void doFrame(long frameTimeNanos) {//vsync回调的时间
            System.out.println("System.nanoTime() - frameTimeNanos == " + (frameTimeNanos - tempTime)/1000f/1000f);
            tempTime = frameTimeNanos;
            pfs++;
            Choreographer.getInstance().postFrameCallback(this);
        }

        @Override
        public void run() {
            long test = System.nanoTime() - mTime;
            for (int i = 0; i < fpsListener.size(); i++) {
                FpsListener listener = fpsListener.get(i);
                listener.listener(test/pfs/1000f/1000f);
            }
            pfs = 0;
            mTime = System.nanoTime();
            mHandler.postDelayed(mCallBackAndRunnable,mIntervalTime);
        }
    }


    public interface FpsListener{
        void listener(float count);
    }
}
