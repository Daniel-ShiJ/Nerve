package com.kingnet.nerve.network.Interface;

/**
 * Author:Daniel.ShiJ
 * Date:2022/4/29 16:25
 * Description:上传协议
 */
public interface IUpload {
    void setUrl(String url);
    void getDataFromDisk();
    void getDataFromBinder();
    void setListener();
    void execute();
}
