package com.kingnet.nerve.base;

import android.app.Application;
import android.content.Context;

import androidx.lifecycle.ProcessLifecycleOwner;

import com.kingnet.nerve.base.Utils.ApplicationUtils;

/**
 * Author:Daniel.ShiJ
 * Date:2022/6/7 11:40
 * Description:
 */
public class BaseContext extends Application {
    private static Context mCon;

    public static Context getCon() {
        return mCon;
    }

    public static void setCon(Context mCon) {
        BaseContext.mCon = mCon;
        ProcessLifecycleOwner.get().getLifecycle().addObserver(new ApplicationUtils.NerveLifecycleEventObserver());
    }
}
