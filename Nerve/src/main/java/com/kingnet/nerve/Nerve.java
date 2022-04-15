package com.kingnet.nerve;

import android.content.Context;

import com.kingnet.nerve.common.network.INetWorkEngine;
import com.kingnet.nerve.common.network.Listener.UploadDataListener;

/**
 * Author:Daniel.ShiJ
 * Date:2022/4/15 15:33
 * Description:
 */
public final class Nerve {
    private static volatile Nerve mNerve;
    private UploadDataListener uploadDataListener;
    private INetWorkEngine netWorkEngine;
    /**
     * 配置文件，从网络获取
     */
    private Config config;
    private Nerve(Builder builder){
        this.uploadDataListener = builder.uploadDataListener;
        this.netWorkEngine = builder.netWorkEngine;
        this.config = builder.config;
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
    }
}
