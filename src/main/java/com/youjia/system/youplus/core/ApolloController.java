package com.youjia.system.youplus.core;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author wuweifeng wrote on 2018/12/20.
 */
@RestController
@RequestMapping("")
public class ApolloController {
    @Value("${config_info:default-value}")
    private String apolloConfigInfo;

    @RequestMapping
    public Object apollo() {
        return apolloConfigInfo;
    }
}
