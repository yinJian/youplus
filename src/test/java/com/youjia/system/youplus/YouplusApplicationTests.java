package com.youjia.system.youplus;

import com.youjia.system.youplus.core.wechat.WeChatUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

@RunWith(SpringRunner.class)
@SpringBootTest
public class YouplusApplicationTests {
    @Resource
    private WeChatUtils weChatUtils;
    @Resource
    private ApplicationEventPublisher publisher;

    @Test
    public void contextLoads() {
        //NewOrderEvent newOrderEvent = new NewOrderEvent
        //        (new NewOrderBean("1","o8djk1VhtIEHILmttzYUR0djDMms", "a","a",
        //        "1867"));
        //publisher.publishEvent(newOrderEvent);
        
    }

}
