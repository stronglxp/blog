package com.codeliu.blog.util;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * Verify the user's identity when accessing the background interface
 */
@Component
@Aspect
public class SecurityAop {
    private Logger logger = LoggerFactory.getLogger(SecurityAop.class);

    //@Pointcut("execution(public * com.codeliu.blog.controller.BackController.*(..))")
    private void pointCut() {}

    /**
     * Intercept the access request before and after execution,
     * and do not execute it by using the default target method of circular notification.
     *
     * Surround notification must have a return value, which is the return value of the target method.
     * @param joinPoint
     * @return
     */
    //@Around("pointCut()")
    public Object beforeUrl(ProceedingJoinPoint joinPoint) {
        ResultUtils<Map<String, String>> res = new ResultUtils<>();
        Object[] args = joinPoint.getArgs();
        String username = args[0].toString();
        logger.debug("args = {}", args);
        logger.debug("username = {}", username);

        // if the user is admin, execute the target method.
        if ("admin".equals(username)) {
            try {
                res = (ResultUtils<Map<String, String>>)joinPoint.proceed();
            } catch (Throwable throwable) {
                throwable.printStackTrace();
            }
        } else {
            res.setCode(MsgEnum.USER_INVALID.getCode());
            res.setMsg(MsgEnum.USER_INVALID.getMsg());
            res.setData(null);
        }
        return res;
    }
}
