package com.kingnet.nerve.analyze;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;

import androidx.annotation.Nullable;

import com.kingnet.nerve.base.BaseContext;
import com.kingnet.nerve.common.LogNerve;

/**
 * Author:Daniel.ShiJ
 * Date:2022/6/10 13:44
 * Description:分析服务
 */
public class AnalyzeService extends Service {
    private static final String TAG = "AnalyzeService";


    @Override
    public void onCreate() {
        super.onCreate();

    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        LogNerve.e(TAG, "onBind");
        if(BaseContext.getCon() == null) {
            //防止多进程，BaseContext未初始化
            BaseContext.setCon(this);
        }
        return new NotifyImpl();
    }
}
