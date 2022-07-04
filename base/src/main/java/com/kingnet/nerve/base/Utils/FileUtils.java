package com.kingnet.nerve.base.Utils;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * Author:Daniel.ShiJ
 * Date:2022/6/7 11:08
 * Description:文件工具
 */
public class FileUtils {
    public static void bytesToFile(byte[] bytes, File file){
        BufferedInputStream bufferedInputStream = null;
        FileOutputStream fileOutputStream = null;
        BufferedOutputStream bufferedOutputStream = null;

        try {
            ByteArrayInputStream byteArrayInputStream =  new ByteArrayInputStream(bytes);
            bufferedInputStream = new BufferedInputStream(byteArrayInputStream);

            File sourceFile = checkAndCreateFile(file);

            if(sourceFile.exists()){//文件存在
                fileOutputStream = new FileOutputStream(sourceFile,true);
            }else {
                fileOutputStream = new FileOutputStream(sourceFile);
            }

            bufferedOutputStream = new BufferedOutputStream(fileOutputStream);

            byte[] buffer = new byte[1024];
            int length;
            while ((length = bufferedInputStream.read(buffer)) != -1){
                bufferedOutputStream.write(buffer,0,length);
            }
            bufferedOutputStream.flush();
            byteArrayInputStream.close();
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            try {
                if (null != bufferedInputStream)
                    bufferedInputStream.close();
                if (null != fileOutputStream)
                    fileOutputStream.close();
                if (null != bufferedOutputStream)
                    bufferedOutputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static File checkAndCreateFile(File dir){
        if (null != dir) {
            if (!dir.exists()) {
                boolean isSuccess = dir.mkdirs();
                if (!isSuccess) {
                    throw new IllegalArgumentException("create " + dir.getAbsolutePath() + " fail！！！");
                }
            }
        }
        return new File(dir, DateUtils.getNow() +".txt");
    }

    public static void checkAndCreateDir(File dir){
        if (null != dir) {
            if(!dir.exists())
                dir.mkdirs();
        }
    }


}
