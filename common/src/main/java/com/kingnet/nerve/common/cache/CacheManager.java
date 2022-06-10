package com.kingnet.nerve.common.cache;

import com.kingnet.nerve.base.BaseContext;
import com.kingnet.nerve.base.Utils.FileUtils;
import com.kingnet.nerve.common.Enum.DataEnum;
import com.kingnet.nerve.common.LogNerve;

import java.io.File;
import java.io.InputStream;
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
    public void pushData(DataEnum dataEnum, byte[] bytes) {
        File file = new File(BaseContext.getCon().getExternalCacheDir() + File.separator + pathDir + dataEnum.name());
        LogNerve.e(dataEnum.name() + "-> pushData,path = "+file.getAbsolutePath());
        FileUtils.bytesToFile(bytes,file);
    }

    @Override
    public InputStream popData(DataEnum dataEnum) {
        File file = new File(BaseContext.getCon().getExternalCacheDir() + File.separator + pathDir + dataEnum.name());

        LogNerve.e(file.getAbsolutePath());
        if(file.isDirectory()){
            File[] files = file.listFiles();
            for (File file1 : files) {
                LogNerve.e(file1.getName());
            }
        }
        return null;
    }
}

