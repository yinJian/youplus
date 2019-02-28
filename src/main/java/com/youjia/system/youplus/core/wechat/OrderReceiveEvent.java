package com.youjia.system.youplus.core.wechat;

import com.youjia.system.youplus.core.wechat.event.BaseEvent;
import com.youjia.system.youplus.core.wechat.event.OrderReceiveBean;

/**
 * @author wuweifeng wrote on 2019/1/21.
 */
public class OrderReceiveEvent extends BaseEvent {


    public OrderReceiveEvent(OrderReceiveBean source) {
        super(source);
    }
}
