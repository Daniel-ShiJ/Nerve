package com.kingnet.nerve.common.cache;

import android.util.Log;

import com.kingnet.nerve.common.Config;
import com.kingnet.nerve.common.Enum.DataEnum;
import com.kingnet.nerve.common.LogNerve;

import java.io.File;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.List;

/**
 * Author:Daniel.ShiJ
 * Date:2022/4/20 16:47
 * Description:缓存管理
 */
public class CacheManager implements ICacheManager{
    public List cache;
    public List cacheOfApp;
    public List diskCache;

    private static volatile CacheManager cacheManager;
    public static CacheManager getInstance(){
        if(null == cacheManager){
            synchronized (CacheManager.class){
                if(null == cacheManager)
                    cacheManager = new CacheManager();
            }
        }
        return cacheManager;
    }
    CacheManager(){}

    @Override
    public void saveData(DataEnum dataEnum, OutputStream stream) {
        File file = new File(pathDir + dataEnum.name(), System.currentTimeMillis() + ".txt");

    }

    @Override
    public InputStream popData(DataEnum dataEnum) {
        File file = new File(pathDir+dataEnum.name());
        if(file.isDirectory()){
            File[] files = file.listFiles();
            for (File file1 : files) {
                LogNerve.e(file1.getName());
            }
        }
        return null;
    }
}

