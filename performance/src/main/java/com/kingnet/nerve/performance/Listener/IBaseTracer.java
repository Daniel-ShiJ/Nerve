package com.kingnet.nerve.performance.Listener;

/**
 * Author:Daniel.ShiJ
 * Date:2022/6/16 17:27
 * Description:
 */
public interface IBaseTracer<T> {
    default void writeData(String json){};
    default void writeData(T t){};
}
