package com.youjia.system.youplus.global.aop;

import com.xiaoleilu.hutool.json.JSONUtil;
import com.youjia.system.youplus.core.base.BaseEntity;
import com.youjia.system.youplus.core.operation.OperationLogEntity;
import com.youjia.system.youplus.core.operation.OperationLogManager;
import com.youjia.system.youplus.global.util.Constant;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.Date;

/**
 * @author wuweifeng wrote on 2017/10/23.
 */
@Aspect
@Component
@Order(2)
public class SavePointAspect {
    @Resource
    private OperationLogManager operationLogManager;

    @Around("@within(service) && !within(com.youjia.system.youplus.core.operation.OperationLogManager)")
    public Object around(ProceedingJoinPoint joinPoint, Service service) throws Throwable {
        String className = joinPoint.getSignature().getDeclaringTypeName();
        // 接收到请求，记录请求内容
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();
        String userId = request.getHeader(Constant.USERID);
        String method = joinPoint.getSignature().getName();
        Object[] obj = joinPoint.getArgs();
        Date date = new Date();
        for (Object o : obj) {
            if (!(o instanceof BaseEntity)) {
                continue;
            }
            BaseEntity baseEntity = (BaseEntity) o;

            //添加系统日志
            OperationLogEntity operationLogEntity = new OperationLogEntity();
            if (!method.contains("add") && !method.contains("update") && !method.contains("delete")) {
                continue;
            }

            if (method.contains("add")) {
                operationLogEntity.setOperationType("add");
            } else if (method.contains("update")) {
                operationLogEntity.setOperationType("update");
            } else if (method.contains("delete")) {
                operationLogEntity.setOperationType("delete");
            }

            baseEntity.setUserId(userId);
            baseEntity.setCreateTime(date);
            baseEntity.setUpdateTime(date);

            String jsonContent = JSONUtil.toJsonStr(o);
            operationLogEntity.setContent(jsonContent);
            operationLogEntity.setSucceed(true);
            operationLogEntity.setTitle(className.replace("com.youjia.system.youplus.core.", "") + "." + method);
            operationLogEntity.setUserId(userId + "");
            operationLogEntity.setCreateTime(date);
            operationLogEntity.setUpdateTime(date);

            try {
                Object o1 = joinPoint.proceed();
                
                operationLogEntity.setKeyword("Id:" + baseEntity.getId());
                operationLogManager.operationLog(operationLogEntity);
                return o1;
            } catch (Throwable throwable) {
                operationLogEntity.setSucceed(false);
                operationLogManager.operationLog(operationLogEntity);
                throw throwable;
            }
        }
        return null;
    }
}
