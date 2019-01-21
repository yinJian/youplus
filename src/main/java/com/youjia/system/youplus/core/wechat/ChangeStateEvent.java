package com.youjia.system.youplus.core.wechat;

import com.youjia.system.youplus.core.wechat.bean.ChangeStateBean;
import com.youjia.system.youplus.core.wechat.bean.NewOrderBean;
import org.springframework.context.ApplicationEvent;

/**
 * @author wuweifeng wrote on 2019/1/21.
 */
public class ChangeStateEvent extends ApplicationEvent {


    public ChangeStateEvent(ChangeStateBean source) {
        super(source);
    }
}
