package com.kingnet.nerve.base;

import android.content.Context;

/**
 * Author:Daniel.ShiJ
 * Date:2022/6/7 11:40
 * Description:
 */
public class BaseContext {
    private static Context mCon;

    public static Context getCon() {
        return mCon;
    }

    public static void setCon(Context mCon) {
        BaseContext.mCon = mCon;
    }
}
