package com.itheima.aop;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

@Slf4j
@Aspect
@Component
public class MyAspect {

    //    声明成public，其他地方可以使用pt()
//    @Pointcut("execution(* com.itheima.service.impl.DeptServiceImpl.*(..))")
    // 我要匹配的方法上面加有@Mylog的注解
    @Pointcut("@annotation(com.itheima.aop.Mylog)")
    public void pt() {
    }

    @Before("pt()")
    public void before() {
        log.info("before...");
    }

    @Around("pt()")
    public Object around(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        log.info("around before...");
//        调用目标对象的原始方法
        Object result = proceedingJoinPoint.proceed();

        log.info("around after...");

        return result;
    }

    @After("pt()")
    public void after() {
        log.info("after...");
    }

    @AfterReturning("pt()")
    public void afterReturning() {
        log.info("afterReturning...");
    }

    @AfterThrowing("pt()")
    public void afterThrowing() {
        log.info("afterThrowing...");
    }
}
