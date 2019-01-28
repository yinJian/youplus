package com.youjia.system.youplus.core.wechat;

import com.youjia.system.youplus.core.wechat.event.NewOrderBean;
import org.springframework.context.ApplicationEvent;

/**
 * @author wuweifeng wrote on 2019/1/21.
 */
public class NewOrderEvent extends ApplicationEvent {


    public NewOrderEvent(NewOrderBean source) {
        super(source);
    }
}
