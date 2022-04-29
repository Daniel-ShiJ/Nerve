package com.kingnet.nerve;

import android.content.Context;

import com.kingnet.nerve.base.IAction;
import com.kingnet.nerve.common.Config;
import com.kingnet.nerve.common.LogNerve;
import com.kingnet.nerve.network.Uploader;
import com.kingnet.nerve.network.base.BaseUploader;

/**
 * Author:Daniel.ShiJ
 * Date:2022/4/15 15:33
 * Description:
 */
public final class Nerve implements IAction {
    private static volatile Nerve mNerve;

    private BaseUploader mUploader;
    /**
     * 配置文件，从网络获取
     */
    private Config config;
    private Nerve(Builder builder){
        this.mUploader = builder.getUploader();
        this.config = builder.getConfig();
    }

    @Override
    public void start() {
        LogNerve.e("config.getDataType() = "+ config.getDataType());
        if(config.isTraceMemInfo()){
            //采集内存信息
            LogNerve.e("config.isTraceMemInfo() = "+ config.isTraceMemInfo());
        }

        if(config.isTraceFRAME()){
            //采集Frame信息
            LogNerve.e("config.isTraceFRAME() = "+ config.isTraceFRAME());
        }
    }

    @Override
    public void update() {

    }

    @Override
    public void end() {

    }

    public static class Builder{
        private BaseUploader mUploader;
        private Config config;
        public Builder setUploader(BaseUploader uploader) {
            this.mUploader = uploader;
            return this;
        }

        public BaseUploader getUploader() {
            if(mUploader == null)
                mUploader = Uploader.getInstance();
            return mUploader;
        }

        public Builder setUploadDataType(int dataType) {
            if(null == config){
                config = Config.getInstance();
            }
            config.setDataType((byte) (dataType & 0xFF));
            return this;
        }


        public Nerve create(Context context){
            if(null == config)
                throw new IllegalArgumentException("config not init!!!");
            return new Nerve(this);
        }
        public Config getConfig() {
            return config;
        }
    }



}
