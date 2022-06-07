package com.kingnet.nerve.base.Utils;


import android.icu.util.Calendar;
import android.os.Build;

/**
 * Author:Daniel.ShiJ
 * Date:2022/6/7 13:55
 * Description:日期工具
 */
public class DateUtils {
    public static String getNow(){
        String temp = "";
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            Calendar calendar = Calendar.getInstance();
            temp = calendar.get(Calendar.YEAR)+"-"+(calendar.get(Calendar.MONTH) + 1)+"-"+calendar.get(Calendar.DAY_OF_MONTH)+"_"+calendar.get(Calendar.HOUR_OF_DAY);
        }else{

        }
        return temp;
    }
}
