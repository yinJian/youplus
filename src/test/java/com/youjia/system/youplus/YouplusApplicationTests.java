package com.youjia.system.youplus;

import com.youjia.system.youplus.core.person.esign.ESignManager;
import com.youjia.system.youplus.core.person.sign.PtSignManager;
import com.youjia.system.youplus.core.person.sign.SignService;
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
    @Resource
    private ESignManager eSignManager;
    @Resource
    private PtSignManager ptSignManager;
    @Resource
    private SignService signService;

    @Test
    public void contextLoads() {
        //NewOrderEvent newOrderEvent = new NewOrderEvent
        //        (new NewOrderBean("1","o8djk1VhtIEHILmttzYUR0djDMms", "a","a",
        //        "1867"));
        //publisher.publishEvent(newOrderEvent);

        //eSignManager.upload();
        //eSignManager.createSignFlow();
        //eSignManager.sign();
        //eSignManager.guidang();
        eSignManager.download("c49be53c7e2347759eaf5f320a84c82d");
        //BaseData baseData = signService.findSignPath(1L);
        //System.out.println(baseData);
    }

}
