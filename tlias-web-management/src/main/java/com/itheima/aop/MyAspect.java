package com.itheima.aop;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Slf4j
//@Aspect
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

//        1. 获取 目标对象的类名
        log.info("目标对象的类名是:{}", proceedingJoinPoint.getTarget().getClass().getName());
//        2. 获取 目标方法的方法名
        log.info("目标方法的方法名是:{}", proceedingJoinPoint.getSignature().getName());
//        3. 获取目标方法运行时传入的参数
        Object[] args = proceedingJoinPoint.getArgs();
        log.info("目标方法运行时传入的参数是:{}", Arrays.toString(args));
//        4. 目标方法执行
        log.info("目标方法运行:{}proceedingJoinPoint.proceed()");
        Object result = proceedingJoinPoint.proceed();
//        5. 获取目标方法运行的返回值
        log.info("目标方法运行的返回值是:{}", result);
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
