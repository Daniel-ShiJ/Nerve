package com.kingnet.nerve.base.Utils;

import androidx.annotation.NonNull;
import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleEventObserver;
import androidx.lifecycle.LifecycleOwner;

import java.util.ArrayList;
import java.util.List;

/**
 * Author:Daniel.ShiJ
 * Date:2022/7/4 15:08
 * Description:
 */
public class ApplicationUtils {
    private static boolean isForeground;
    private static List<NerveLifecycleObserver> observers;

    public static class NerveLifecycleEventObserver implements LifecycleEventObserver {
        @Override
        public void onStateChanged(@NonNull LifecycleOwner source, @NonNull Lifecycle.Event event) {
            switch (event){
                case ON_START:
                    isForeground = true;
                    notifyObservers();
                    break;
                case ON_STOP:
                    isForeground = false;
                    notifyObservers();
                    break;
            }
        }
    }

    public static void addObserver(NerveLifecycleObserver observer){
        if(null == observers || observers.isEmpty())
            observers = new ArrayList<>();
        observers.add(observer);
    }

    public static void removeObserver(NerveLifecycleObserver observer) {
        if(null == observers || observers.isEmpty())
            return;
        observers.remove(observer);
    }

    private static void notifyObservers(){
        if(null == observers || observers.isEmpty())
            return;
        for (NerveLifecycleObserver observer : observers) {
            observer.onStateChanged(isForeground);
        }
    }

    public interface NerveLifecycleObserver{
        public void onStateChanged(boolean isForeground);
    }
}

