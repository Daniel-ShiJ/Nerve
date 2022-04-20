package com.kingnet.nerve;

import android.content.Context;

import com.kingnet.nerve.common.Config;
import com.kingnet.nerve.common.network.INetWorkEngine;
import com.kingnet.nerve.common.network.Listener.UploadDataListener;

/**
 * Author:Daniel.ShiJ
 * Date:2022/4/15 15:33
 * Description:
 */
public final class Nerve {
    private static volatile Nerve mNerve;
    /**
     * 上传回调
     */
    private UploadDataListener uploadDataListener;
    /**
     * 网络引擎
     */
    private INetWorkEngine netWorkEngine;
    /**
     * 配置文件，从网络获取
     */
    private Config config;
    private Nerve(Builder builder){
        this.uploadDataListener = builder.getUploadDataListener();
        this.netWorkEngine = builder.getNetWorkEngine();
        this.config = builder.getConfig();
    }

    public static class Builder{
        private UploadDataListener uploadDataListener;
        private INetWorkEngine netWorkEngine;
        private Config config;
        public Builder setUploadDataListener(UploadDataListener uploadDataListener) {
            this.uploadDataListener = uploadDataListener;
            return this;
        }

        public Builder setNetWorkEngine(INetWorkEngine netWorkEngine) {
            this.netWorkEngine = netWorkEngine;
            return this;
        }

        public Builder setConfig(Config config) {
            this.config = config;
            return this;
        }

        public Nerve create(Context context){
            if(null == config)
                throw new IllegalArgumentException("config not init!!!");
            return new Nerve(this);
        }

        public UploadDataListener getUploadDataListener() {
            if(uploadDataListener == null)
                uploadDataListener = new DefaultUploadDataListener();
            return uploadDataListener;
        }

        public INetWorkEngine getNetWorkEngine() {
            if(null == netWorkEngine)
                netWorkEngine = new DefaultNetWorkEngine();
            return netWorkEngine;
        }

        public Config getConfig() {
            return config;
        }
    }


}
