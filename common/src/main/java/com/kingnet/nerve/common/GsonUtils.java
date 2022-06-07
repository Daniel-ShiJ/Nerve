package com.kingnet.nerve.common;

import com.google.gson.Gson;

/**
 * Author:Daniel.ShiJ
 * Date:2022/6/7 15:09
 * Description:
 */
public class GsonUtils {
    private static Gson gson;

    public static Gson getGson(){
        if(null == gson)
            gson = new Gson();
        return gson;
    }

}
