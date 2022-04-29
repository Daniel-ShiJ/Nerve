package com.kingnet.nerve.network.base;

import android.app.Service;
import android.os.Build;
import android.os.FileObserver;

import com.kingnet.nerve.network.Interface.IUpload;
import com.kingnet.nerve.network.anr.AnrFileObserver;

/**
 * Author:Daniel.ShiJ
 * Date:2022/4/29 16:25
 * Description:上传者
 */
public abstract class BaseUploader extends Service implements IUpload {
    /**
     * anr监听
     */
    private FileObserver anrFileObserver;

     public BaseUploader(){
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
            anrFileObserver = new AnrFileObserver();
        }
    }

}
