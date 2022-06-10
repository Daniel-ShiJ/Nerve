package com.kingnet.nerve.analyze;

import com.kingnet.nerve.analyze.aidl.INotify;
import com.kingnet.nerve.common.Enum.DataEnum;
import com.kingnet.nerve.common.LogNerve;
import com.kingnet.nerve.common.cache.CacheManager;

/**
 * Author:Daniel.ShiJ
 * Date:2022/6/10 14:32
 * Description:
 */
public class NotifyImpl extends INotify.Stub {
    private static final String TAG = "NotifyImpl";
    @Override
    public void move(boolean isMove){
        LogNerve.e(TAG,"move = " + isMove);
        if(isMove){//移动，直接将待分析文件，一次性拿到该线程
            CacheManager.getInstance().popData(DataEnum.MEMORY);
        }else {

        }
    }
}
