package com.kingnet.nerve.common.cache;

import android.os.Build;

import com.kingnet.nerve.base.BaseContext;
import com.kingnet.nerve.base.Utils.FileUtils;
import com.kingnet.nerve.common.Enum.DataEnum;
import com.kingnet.nerve.common.LogNerve;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;

/**
 * Author:Daniel.ShiJ
 * Date:2022/4/20 16:47
 * Description:缓存管理
 */
public class CacheManager implements ICacheManager<byte[]>{
    public List cache = new ArrayList();
    public List cacheOfApp;
    public List diskCache;
    private int version;

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

    public void pushDataPw(DataEnum dataEnum, Throwable throwable) {
        File fileDir = new File(BaseContext.getCon().getExternalCacheDir() + File.separator + pathDir + dataEnum.name());

        File file = FileUtils.checkAndCreateFile(fileDir);


        LogNerve.e(dataEnum.name() + "-> pushDataPw,path = "+file.getAbsolutePath());

        PrintWriter pw = null;
        try {
            pw = new PrintWriter(new BufferedWriter(new FileWriter(file,true)));
        } catch (IOException e) {
            e.printStackTrace();
        }
        pw.println();
        throwable.printStackTrace(pw);
        pw.println();
        pw.close();
    }

    @Override
    public boolean popData(DataEnum dataEnum) {
        File sourceDir = new File(BaseContext.getCon().getExternalCacheDir() + File.separator + pathDir + dataEnum.name());
        File targetDir = new File(BaseContext.getCon().getExternalCacheDir() + File.separator + pathAnalyzeDir + dataEnum.name());
        FileUtils.checkAndCreateDir(targetDir);
        if (sourceDir.isDirectory()) {
            File[] sourceFiles = sourceDir.listFiles();
            long startTime = System.currentTimeMillis();
            if (null != sourceFiles && sourceFiles.length != 0) {
                for (File sourceFile : sourceFiles) {
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                        try {
                            File targetFile = new File(targetDir,sourceFile.getName());
                            Files.copy(sourceFile.toPath(),targetFile.toPath());
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
            return (null != targetDir && targetDir.listFiles().length != 0);
        }
        return false;
    }

    public boolean deleteData(DataEnum dataEnum){
        File sourceDir = new File(BaseContext.getCon().getExternalCacheDir() + File.separator + pathDir + dataEnum.name());
        if(sourceDir.isDirectory()){
            File[] files = sourceDir.listFiles();
            for (File file : files) {
                boolean delete = file.delete();
                if(!delete){
                    return false;
                }
            }
        }else{
            return false;
        }
        return true;
    }
}

