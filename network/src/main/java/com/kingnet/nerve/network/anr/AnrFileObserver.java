package com.kingnet.nerve.network.anr;

import android.os.FileObserver;

import androidx.annotation.Nullable;

/**
 * Author:Daniel.ShiJ
 * Date:2022/4/29 16:31
 * Description:监听anr文件
 */
public class AnrFileObserver extends FileObserver {

    public AnrFileObserver() {
        super("/data/anr/");
    }

    @Override
    public void onEvent(int event, @Nullable String path) {
        switch (event){
            case ACCESS:
                System.out.println("AnrFileObserver -- ACCESS");
                break;
            case MODIFY:
                System.out.println("AnrFileObserver -- MODIFY");
                break;
            default:
                System.out.println("AnrFileObserver -- default,event = " + event);
                break;
        }
    }
}
