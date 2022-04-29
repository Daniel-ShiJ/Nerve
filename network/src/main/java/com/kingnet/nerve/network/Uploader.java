package com.kingnet.nerve.network;

import android.content.Intent;
import android.os.IBinder;

import androidx.annotation.Nullable;

import com.kingnet.nerve.network.base.BaseUploader;

/**
 * Author:Daniel.ShiJ
 * Date:2022/4/29 17:08
 * Description:上传数据
 */
public final class Uploader extends BaseUploader {

    private static Uploader uploader;

    public static Uploader getInstance(){
        if(null == uploader){
            synchronized (Uploader.class){
                if(null == uploader)
                    uploader = new Uploader();
            }
        }
        return uploader;
    }

    private Uploader() {
        super();
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void setUrl(String url) {

    }

    /**
     * 获取宿主的磁盘数据
     */
    @Override
    public void getDataFromDisk() {

    }

    /**
     * 跨进程获取宿主的数据
     */
    @Override
    public void getDataFromBinder() {
    }

    @Override
    public void setListener() {

    }

    @Override
    public void execute() {

    }
}
