package com.kingnet.nerve.performance.Traces;


import com.kingnet.nerve.common.Enum.DataEnum;
import com.kingnet.nerve.performance.Listener.IFrameTracer;
import com.kingnet.nerve.performance.abs.BaseTracer;
import com.kingnet.nerve.performance.monitor.IMonitor.IMonitor;
import com.kingnet.nerve.performance.monitor.KYFpsMonitor;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Author:Daniel.ShiJ
 * Date:2022/3/25 17:07
 * Description:
 */
public class FrameTrace extends BaseTracer implements IFrameTracer{
    public FrameTrace() {
        mMonitor = KYFpsMonitor.getMonitor();
    }

    private JSONObject jsonObject;

    @Override
    public void writeFPS(long startNs, long endNs, long inputCost, long animationCost, long traversalCost) {
        if(null == jsonObject){
            jsonObject = new JSONObject();
        }
        try {
            jsonObject.putOpt("startNs", startNs);
            jsonObject.putOpt("endNs", endNs);
            jsonObject.putOpt("inputCost", inputCost);
            jsonObject.putOpt("animationCost", animationCost);
            jsonObject.putOpt("traversalCost", traversalCost);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        saveData(DataEnum.FRAME,jsonObject.toString().getBytes());
    }
}
