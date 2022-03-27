package com.kingnet.nerve.performance.unity;

/**
 * Author:Daniel.ShiJ
 * Date:2022/3/25 16:56
 * Description:frame 数据
 */
public class FrameBean {
    private String targetName;
    private int status;
    private long cost;

    public String getTargetName() {
        return targetName;
    }

    public void setTargetName(String targetName) {
        this.targetName = targetName;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public long getCost() {
        return cost;
    }

    public void setCost(long cost) {
        this.cost = cost;
    }
}
