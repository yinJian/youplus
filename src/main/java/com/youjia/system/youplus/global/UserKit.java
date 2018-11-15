package com.youjia.system.youplus.global;

import com.youjia.system.youplus.core.user.user.PtUserManager;
import com.youjia.system.youplus.global.util.Constant;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

/**
 * @author wuweifeng wrote on 2018/11/6.
 */
@Component
public class UserKit {
    @Resource
    private PtUserManager ptUserManager;

    public Long gertCurrentUserId() {
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();
        return Long.valueOf(request.getHeader(Constant.USERID));
    }

}
