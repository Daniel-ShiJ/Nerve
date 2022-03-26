package com.kingnet.nerve.performance.Listener;

/**
 * Author:Daniel.ShiJ
 * Date:2022/3/24 15:28
 * Description:消耗 监听
 */
public interface DoFrameCostListener {
    void doFrameCostListener(long inputCost,long animation,long traversal);
}
