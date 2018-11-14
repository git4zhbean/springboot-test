package com.zhbean.aoplog.aop;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;

/**
 * Created by IntelliJ IDEA.
 *
 * @author: zhbean
 * @Date: 2018/8/28
 */
@Aspect
@Component
@Slf4j
public class CustomsLogAspect {

    @Pointcut("@annotation(com.zhbean.aoplog.aop.CustomsLog)")
    public void customsLog() {
        System.out.println("------------CustomsLogAspect.customsLog()----------");
    }

    @Pointcut("@annotation(com.zhbean.aoplog.aop.LoginLogs)")
    public void loginLog() {
        System.out.println("------------CustomsLogAspect.userLog()----------");
    }

    @Before("customsLog()")
    public void doBefore(JoinPoint joinPoint) {
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();

        log.info("URL : " + request.getRequestURL());
        log.info("HTTP_METHOD: " + request.getMethod());
        log.info("IP : " + request.getRemoteAddr());
        log.info("CLASS_METHOD: " + joinPoint.getSignature().getDeclaringTypeName() + "." + joinPoint.getSignature().getName());
        log.info("ARGS : " + Arrays.toString(joinPoint.getArgs()));
    }

    @AfterReturning(returning = "result", pointcut = "customsLog()")
    public void doAfterReturning(JoinPoint joinPoint, Object result) {
        //操作记录存入数据库操作
        log.info("RESPONSE : " + result);

    }

    @Before("userLog()")
    public void doBeforeUser(JoinPoint joinPoint) {
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();
        String username = (String) joinPoint.getArgs()[0];

        log.info(String.format("user : %s do the operation : %s.%s", username, joinPoint.getSignature().getDeclaringTypeName(), joinPoint.getSignature().getName()));
    }

    @AfterReturning(returning = "result", pointcut = "loginLog()")
    public void doAfterReturningUser(JoinPoint joinPoint, Object result) {
        //登录信息存入数据库操作
        log.info("RESPONSE : " + result);
    }


}
