package com.youjia.system.youplus.core.wechat;

import com.youjia.system.youplus.core.wechat.event.OrderReceiveBean;
import org.springframework.context.ApplicationEvent;

/**
 * @author wuweifeng wrote on 2019/1/21.
 */
public class OrderReceiveEvent extends ApplicationEvent {


    public OrderReceiveEvent(OrderReceiveBean source) {
        super(source);
    }
}
