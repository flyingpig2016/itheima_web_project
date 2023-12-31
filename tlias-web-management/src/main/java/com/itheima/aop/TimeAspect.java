package com.itheima.aop;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.springframework.stereotype.Component;

@Slf4j
@Component
//@Aspect //AOP类
public class TimeAspect {

    @Around("execution(* com.itheima.service.*.*(..))") //切入点表达式
    public Object rocordTime(ProceedingJoinPoint joinPoint) throws Throwable {
        // 1. 记录开始时间
        long start = System.currentTimeMillis();

        // 2. 调用原始方法运行
        Object result = joinPoint.proceed();

        // 3. 记录结束时间
        long end = System.currentTimeMillis();
        // 4.  计算运行时间

        long duration = end - start;
        log.info(joinPoint.getSignature() + "方法执行耗时: {}ms", duration);

        return result;
    }
}
