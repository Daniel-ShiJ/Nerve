package com.kingnet.nerve;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.os.RemoteException;

import androidx.annotation.IntDef;

import com.kingnet.nerve.analyze.AnalyzeService;
import com.kingnet.nerve.analyze.aidl.INotify;
import com.kingnet.nerve.annotation.DataEnumDef;
import com.kingnet.nerve.base.BaseContext;
import com.kingnet.nerve.common.Config;
import com.kingnet.nerve.common.Enum.DataEnum;
import com.kingnet.nerve.common.LogNerve;
import com.kingnet.nerve.network.Uploader;
import com.kingnet.nerve.network.base.BaseUploader;
import com.kingnet.nerve.performance.Listener.ITracer;
import com.kingnet.nerve.performance.Traces.FrameTrace;
import com.kingnet.nerve.performance.Traces.MemInfoTrace;

/**
 * Author:Daniel.ShiJ
 * Date:2022/4/15 15:33
 * Description:
 */
public final class Nerve{
    private BaseUploader mUploader;
    public static final byte MEMORY = 0x01;
    public static final int FRAME = 0x03;
    public static final int DNS = 0x05;
    public static final int NETWORK = 0x08;
    public static final int CRASH = 0x10;
    public static final int NATIVE_CRASH = 0x20;
    public static final int LOG = 0x40;


    private INotify notify;

    /**
     * 配置文件，从网络获取
     */
    private Config config;
    private Nerve(Builder builder){
        this.mUploader = builder.getUploader();
        this.config = builder.getConfig();
        BaseContext.setCon(builder.context);
    }

    public static Context getContext(){
        if(null == BaseContext.getCon()){
            throw new RuntimeException("context much init!!!");
        }
        return BaseContext.getCon();
    }


    /**
     * 开始采集
     */
    public void start() {
        LogNerve.e("Nerve start ..........");
        boolean isAll = (config.getDataType() == -1);//全采集
        if(isAll || config.isTraceFRAME()){
            //采集Frame信息
            LogNerve.e("开始采集Frame信息.....");
            ITracer tracer = new FrameTrace();
            tracer.startTrace();
        }

        if(isAll || config.isTraceMemInfo()){
            //采集内存信息
            LogNerve.e("开始采集内存信息.....");
            ITracer tracer = new MemInfoTrace();
            tracer.startTrace();
        }


        //这里启动本地分析、裁剪和上传的服务
        Intent intent = new Intent(BaseContext.getCon(), AnalyzeService.class);

        getContext().bindService(intent, new ServiceConnection() {
            @Override
            public void onServiceConnected(ComponentName name, IBinder service) {
                notify = INotify.Stub.asInterface(service);
                LogNerve.e("onServiceConnected.....");
            }

            @Override
            public void onServiceDisconnected(ComponentName name) {
                LogNerve.e("onServiceDisconnected.....");
            }
        },Context.BIND_AUTO_CREATE);

    }

    /**
     * 停止采集
     */
    public void stop(){

        try {
            notify.move(true);
        } catch (RemoteException e) {
            e.printStackTrace();
        }

//
//        LogNerve.e("Nerve stop ..........");
//        boolean isAll = (config.getDataType() == -1);//全停止
//
//        if(isAll || config.isTraceFRAME()){
//            //采集Frame信息
//            LogNerve.e("停止采集Frame信息.....");
//        }
//
//        if(isAll || config.isTraceMemInfo()){
//            //采集内存信息
//            LogNerve.e("停止采集内存信息.....");
//        }

    }


    public static class Builder{
        private BaseUploader mUploader;
        private Config config;
        private Context context;
        public Builder(Context context){
            this.context = context;
        }
        public Builder setUploader(BaseUploader uploader) {
            this.mUploader = uploader;
            return this;
        }

        public BaseUploader getUploader() {
            if(mUploader == null)
                mUploader = Uploader.getInstance();
            return mUploader;
        }

        /**
         * 需要上传的数据种类
         * 0:不需要,
         * -1:所有,
         * 0000 0001 MEMORY,
         * 0000 0010 FRAME,
         * 0000 0100 DNS,
         * 0000 1000 NETWORK,
         * 0001 0000 CRASH,
         * 0010 0000 NATIVE_CRASH,
         * 0100 0000 LOG,
         * ...
         */
        public Builder setUploadDataType(@DataEnumDef int dataType) {
            if(null == config){
                config = Config.getInstance();
            }
            config.setDataType((byte) (dataType & 0xFF));
            return this;
        }


        public Nerve create(){
            if(null == config)
                throw new IllegalArgumentException("config not init!!!");
            return new Nerve(this);
        }
        public Config getConfig() {
            return config;
        }
    }



}
