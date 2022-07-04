package com.kingnet.nerve.performance.Listener;

/**
 * Author:Daniel.ShiJ
 * Date:2022/3/25 17:01
 * Description:
 */
public interface IFrameTracer extends IBaseTracer {
    void writeFPS(long startNs, long endNs, long inputCost, long animationCost, long traversalCost);
}
