package com.kingnet.nerve.base.Utils;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.LineNumberReader;
import java.net.InterfaceAddress;
import java.util.LinkedHashMap;

/**
 * Author:Daniel.ShiJ
 * Date:2022/4/25 15:10
 * Description:
 */
public class RuntimeUtils {
    public static InputStream exec(String command){
        try {
            Process exec = Runtime.getRuntime().exec(command);
            return exec.getInputStream();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static LinkedHashMap filterExec(String command,String filterKey){
        InputStream inputStream = exec(command);
        if(null == inputStream) {
            return null;
        }
        LinkedHashMap linkedHashMap = new LinkedHashMap();

        LineNumberReader lineNumberReader = new LineNumberReader(new InputStreamReader(inputStream));
        String line = "";
        try {
            while (null != (line = lineNumberReader.readLine())){
                int splitIndex = line.indexOf("]: [");
                if(splitIndex == -1) continue;
                String key = line.substring(1,splitIndex);
                String value = line.substring(splitIndex + 4,line.length() - 1);
                if(key.contains(filterKey) || value.contains(filterKey)) {
                    linkedHashMap.put(key, value);
                }
            }
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            if(null != inputStream) {
                try {
                    inputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if(null != lineNumberReader) {
                try {
                    lineNumberReader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return linkedHashMap;
    }
}
