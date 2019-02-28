package com.youjia.system.youplus.core.wechat;

import com.youjia.system.youplus.core.wechat.event.BaseEvent;
import com.youjia.system.youplus.core.wechat.event.NewOrderBean;

/**
 * @author wuweifeng wrote on 2019/1/21.
 */
public class NewOrderEvent extends BaseEvent {


    public NewOrderEvent(NewOrderBean source) {
        super(source);
    }
}
