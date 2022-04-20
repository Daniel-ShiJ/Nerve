package com.kingnet.nerve;

import android.util.Log;

import com.kingnet.nerve.common.network.Listener.UploadDataListener;

/**
 * Author:Daniel.ShiJ
 * Date:2022/4/20 16:40
 * Description:默认上传回调
 */
public class DefaultUploadDataListener implements UploadDataListener {
    @Override
    public void uploadSuccess(int code, String msg) {
        Log.e("Nerve","uploadSuccess code = " + code + ",msg = " + msg);
    }

    @Override
    public void uploadFailed(String msg) {
        Log.e("Nerve","uploadFailed = " + msg);
    }
}
