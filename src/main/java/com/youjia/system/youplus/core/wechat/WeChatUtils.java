package com.youjia.system.youplus.core.wechat;

import com.youjia.system.youplus.core.wechat.bean.ChangeStateBean;
import com.youjia.system.youplus.core.wechat.bean.NewOrderBean;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

/**
 * 微信发送模板消息
 * @author wuweifeng wrote on 2019/1/21.
 */
@Component
public class WeChatUtils {
    @Resource
    private HttpUtil httpUtil;

    @Async
    @EventListener(NewOrderEvent.class)
    public void newOrder(NewOrderEvent newOrderEvent) {
        NewOrderBean newOrderBean = (NewOrderBean) newOrderEvent.getSource();
        Map<String, Object> map = new HashMap<>();
        map.put("orderNumber", newOrderBean.getOrderNumber());
        map.put("openId", newOrderBean.getOpenId());
        map.put("userName", newOrderBean.getUserName());
        map.put("serviceName", newOrderBean.getServiceName());
        map.put("userPhone", newOrderBean.getUserPhone());
        httpUtil.build(TemplateUrl.NEW_ORDER, map);
    }

    @Async
    @EventListener(ChangeStateEvent.class)
    public void newOrder(ChangeStateEvent changeStateEvent) {
        ChangeStateBean changeStateBean = (ChangeStateBean) changeStateEvent.getSource();
        Map<String, Object> map = new HashMap<>();
        map.put("openId", changeStateBean.getOpenId());
        map.put("statesMsg", changeStateBean.getStatesMsg());
        map.put("orderStatus", changeStateBean.getOrderStatus());
        httpUtil.build(TemplateUrl.CHANGE_STATE, map);
    }
}
