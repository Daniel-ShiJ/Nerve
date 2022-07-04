package com.kingnet.nerve.analyze;

import com.kingnet.nerve.analyze.aidl.INotify;
import com.kingnet.nerve.common.Enum.DataEnum;
import com.kingnet.nerve.common.LogNerve;
import com.kingnet.nerve.common.cache.CacheManager;

import java.io.File;

/**
 * Author:Daniel.ShiJ
 * Date:2022/6/10 14:32
 * Description:
 */
public class NotifyImpl extends INotify.Stub {
    private static final String TAG = "NotifyImpl";
    @Override
    public boolean move(boolean isMove,int dataType){
        LogNerve.e(TAG,"move = " + isMove);
        boolean result = false;
        if(isMove){//移动，直接将待分析文件，一次clone所有数据
            for (DataEnum value : DataEnum.values()) {
                if(dataType == value.value()) {
                    result = CacheManager.getInstance().popData(value);
                }
            }
        }else {//禁止移动
//            for (File file : CacheManager.getInstance().clone) {
//                LogNerve.e(file.length()+" ,,,,");
//            }
        }
        return result;
    }
}
