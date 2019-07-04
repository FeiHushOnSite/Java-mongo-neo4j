package com.feiyu.controller;

import com.feiyu.dto.BasicResponse;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StopWatch;
import rx.Observable;


import javax.servlet.http.HttpServletRequest;

@Aspect
@Component
@Slf4j
public class ControllerAopLogger {
    @Autowired
    private HttpServletRequest httpServletRequest;

    /**
     * Log all controller and service functions.
     */

    @AfterThrowing(pointcut = "@within(org.springframework.web.bind.annotation.RestController)", throwing = "e")
    public void afterThrowing(JoinPoint point, Throwable e) {
     //   LOG.error("exception happened when handling request: {}, stack: ", getRequestInfo(point), e);
    }

    @Around("@within(org.springframework.web.bind.annotation.RestController)")
    public Object log(ProceedingJoinPoint point) throws Throwable {

        String requestInfo = getRequestInfo(point);
   //     LOG.info("access controller: {}" ,requestInfo);

        StopWatch stopWatch = new StopWatch();
        stopWatch.start();
        Object retVal = point.proceed();
        if (retVal instanceof Observable<?>) {
//            return ((Observable<?>) retVal).doOnNext(basicResponse -> logEnd(requestInfo, stopWatch, basicResponse)).
//                    doOnError(throwable -> LOG.error("exception happened when handling request: {}, stack: ", requestInfo, throwable));
        } else if (retVal instanceof BasicResponse<?>) {
            logEnd(requestInfo, stopWatch, retVal);
            return retVal;
        }
        return retVal;
    }

    private void logEnd(String requestInfo, StopWatch stopWatch, Object retVal) {
        stopWatch.stop();
     //   LOG.info("return controller: {}, response: {} in {} ms", requestInfo, retVal, stopWatch.getTotalTimeMillis());
    }


    private String getRequestInfo(JoinPoint point) {
        String classDotMethod = point.getTarget().getClass().getSimpleName() + "." + point.getSignature().getName();
        MethodSignature methodSignature = (MethodSignature) point.getSignature();
        String[] parameterNames = methodSignature.getParameterNames();
        StringBuilder args = new StringBuilder();
        String deviceId = httpServletRequest.getHeader("deviceId");
        args.append(classDotMethod).append("(");
        for (int i = 0; i < point.getArgs().length; i++) {
            Object[] arg = point.getArgs();
            if (i > 0) {
                args.append(",");
            }
            args.append(parameterNames[i] + ":" + arg[i]);
        }
        args.append(")");
        args.append(", deviceId: ").append(deviceId);
        return args.toString();
    }

}
