package cn.lzx.juc.pmkpi.thread;

import cn.lzx.juc.pmkpi.ShortPeriodTrain;
import cn.lzx.juc.pmkpi.utils.SpringBeanUtil;
import com.alibaba.fastjson.JSONObject;

import java.util.concurrent.Callable;

/**
 * @author lzx
 * @since 2022/9/26
 */
public class ShortPeriodTrainThread implements Callable<JSONObject> {

    private JSONObject payload;

    public ShortPeriodTrainThread(JSONObject payload) {
        this.payload = payload;
    }

    @Override
    public JSONObject call() {
        ShortPeriodTrain shortPeriodTrain = SpringBeanUtil.getBean(ShortPeriodTrain.class);
        return shortPeriodTrain.getKpiResult(payload);
    }
}
