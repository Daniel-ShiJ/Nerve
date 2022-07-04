package com.kingnet.nerve.performance.unity;

/**
 * Author:Daniel.ShiJ
 * Date:2022/6/14 17:57
 * Description:内存信息
 */
public class MemInfoBean {
    private long max;
    private long total;
    private long free;

    public void setMax(long max) {
        this.max = max;
    }

    public void setTotal(long total) {
        this.total = total;
    }

    public void setFree(long free) {
        this.free = free;
    }
}
