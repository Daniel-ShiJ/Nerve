package com.kingnet.nerve.network.Interface;

/**
 * Author:Daniel.ShiJ
 * Date:2022/4/29 17:02
 * Description:
 */
public interface IUploadListener {
    void uploadSuccess(int code,String msg);
    void uploadFailed(String msg);
}
