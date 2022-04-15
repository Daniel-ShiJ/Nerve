package com.kingnet.nerve.common.network.Listener;

/**
 * Author:Daniel.ShiJ
 * Date:2022/4/15 15:00
 * Description:上传数据监听
 */
public interface UploadDataListener {
    void uploadSuccess(int code,String msg);
    void uploadFailed(String msg);
}
