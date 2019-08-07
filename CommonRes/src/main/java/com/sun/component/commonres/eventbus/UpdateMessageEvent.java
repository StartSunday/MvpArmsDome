package com.sun.component.commonres.eventbus;

public class UpdateMessageEvent {
    /**
     * type=1   收到通知
     * type=2   下载完成
     * type=3   更新完成
     */
    public int type;
    public String remark;

    public UpdateMessageEvent(int type) {
        this.type = type;
    }

    public UpdateMessageEvent(int type, String remark) {
        this.type = type;
        this.remark = remark;
    }
}
