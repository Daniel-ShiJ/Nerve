package com.kingnet.nerve;

import android.app.Application;

/**
 * Author:Daniel.ShiJ
 * Date:2022/7/4 14:51
 * Description:
 */
public class DemoApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        new Nerve.Builder(this)
                .setUploadDataType(Nerve.MEMORY)
                .create()
                .start();
    }
}
