package com.youjia.system.youplus.core.wechat;

import com.youjia.system.youplus.core.wechat.event.BaseEvent;
import com.youjia.system.youplus.core.wechat.event.ChangeStateBean;

/**
 * @author wuweifeng wrote on 2019/1/21.
 */
public class ChangeStateEvent extends BaseEvent {


    public ChangeStateEvent(ChangeStateBean source) {
        super(source);
    }
}
