package com.sun.component.commonres.eventbus;

/**
 * Created by ChenYuYun on 2018/9/26.
 * 获取开关机数据事件
 */
public class GetPowerOnOffTimeEvent {
    public boolean isUpdate;

    public GetPowerOnOffTimeEvent(boolean isUpdate) {
        this.isUpdate = isUpdate;
    }
}
