package com.kingnet.nerve.common;

import android.util.Log;

/**
 * Author:Daniel.ShiJ
 * Date:2022/4/20 17:25
 * Description:
 */
public class LogNerve {
    private static final String TAG = "Nerve";

    public static void e(String msg) {
        if (Config.getInstance().isDebug)
            Log.e(TAG, msg);
    }

    public static void e(String tag, String msg) {
        if (Config.getInstance().isDebug)
            Log.e(TAG + " " + tag, msg);
    }
}
