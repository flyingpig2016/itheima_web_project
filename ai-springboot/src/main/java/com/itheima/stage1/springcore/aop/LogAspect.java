package com.itheima.stage1.springcore.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

/**
 * ==========================================
 * Spring核心学习 - AOP切面编程
 * ==========================================
 * 
 * 【什么是AOP】
 * AOP（Aspect Oriented Programming）面向切面编程
 * 在不修改原有代码的情况下，对方法进行增强
 * 
 * 【AOP核心概念】
 * 1. 切面（Aspect）：通知 + 切点
 * 2. 通知（Advice）：要做的增强工作
 *    - @Before：前置通知，方法执行前
 *    - @After：后置通知，方法执行后（无论是否异常）
 *    - @AfterReturning：返回通知，方法正常返回后
 *    - @AfterThrowing：异常通知，方法抛出异常后
 *    - @Around：环绕通知，方法执行前后
 * 3. 切点（Pointcut）：哪些方法需要被增强
 * 4. 连接点（JoinPoint）：可以被增强的方法
 * 
 * 【@Aspect注解】
 * 标识这是一个切面类
 * 
 * 【@Component注解】
 * 将切面类交给Spring管理
 */
@Aspect
@Component
public class LogAspect {
    
    /**
     * 前置通知：方法执行前执行
     */
    @Before("execution(* com.itheima.stage1.springcore.service.*.*(..))")
    public void beforeAdvice() {
        System.out.println("[AOP-前置通知] 方法即将执行...");
    }
    
    /**
     * 后置通知：方法执行后执行（无论是否异常）
     */
    @After("execution(* com.itheima.stage1.springcore.service.*.*(..))")
    public void afterAdvice() {
        System.out.println("[AOP-后置通知] 方法执行完成...");
    }
    
    /**
     * 返回通知：方法正常返回后执行
     */
    @AfterReturning(pointcut = "execution(* com.itheima.stage1.springcore.service.*.*(..))", returning = "result")
    public void afterReturningAdvice(Object result) {
        System.out.println("[AOP-返回通知] 方法返回结果：" + result);
    }
    
    /**
     * 异常通知：方法抛出异常后执行
     */
    @AfterThrowing(pointcut = "execution(* com.itheima.stage1.springcore.service.*.*(..))", throwing = "e")
    public void afterThrowingAdvice(Exception e) {
        System.out.println("[AOP-异常通知] 方法抛出异常：" + e.getMessage());
    }
    
    /**
     * 环绕通知：方法执行前后都可以增强
     * 这是最强大的通知，可以控制方法是否执行
     */
    @Around("execution(* com.itheima.stage1.springcore.service.*.*(..))")
    public Object aroundAdvice(ProceedingJoinPoint joinPoint) throws Throwable {
        System.out.println("[AOP-环绕通知-前] 方法：" + joinPoint.getSignature().getName());
        
        // 记录开始时间
        long startTime = System.currentTimeMillis();
        
        // 执行目标方法
        Object result = joinPoint.proceed();
        
        // 记录结束时间
        long endTime = System.currentTimeMillis();
        long costTime = endTime - startTime;
        
        System.out.println("[AOP-环绕通知-后] 方法执行耗时：" + costTime + "ms");
        
        return result;
    }
}
