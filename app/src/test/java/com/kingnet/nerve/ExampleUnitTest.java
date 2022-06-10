package com.kingnet.nerve;

import org.junit.Test;

import static org.junit.Assert.*;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Iterator;
import java.util.stream.Stream;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest {
    @Test
    public void addition_isCorrect() {


        String temp = "/Users/daniel.sj/Desktop/KY/workspace/DataSDK/app/build/outputs/apk/debug/app-debug.apk";
        try {
            Process process = Runtime.getRuntime().exec("aapt dump badging "+temp);
            BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
            Stream<String> lines = reader.lines();

            Iterator<String> iterator = lines.iterator();
            while (iterator.hasNext()){
                System.out.println(iterator.next());
            }
            process.destroy();
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("结束 ！！！！");
    }

    @Test
    public void test(){
        int my = my(2, 25);
        System.out.println("value == " + my);
    }

    public int my(int a,int b){
        if(b == 0) return 0;
        if(b % 2 == 0) return my(a + a,b/2);
        return my(a + a,b/2) + a;
    }
}

