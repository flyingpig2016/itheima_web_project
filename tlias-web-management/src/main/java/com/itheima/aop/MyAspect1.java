package com.itheima.aop;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Slf4j
//@Aspect
@Component
public class MyAspect1 {

    //    声明成public，其他地方可以使用pt()
    @Pointcut("execution(* com.itheima.service.impl.DeptServiceImpl.*(..))")
    public void pt() {
    }

    @Before("pt()")
    public void before() {
        log.info("before...1");
    }

    @After("pt()")
    public void after() {
        log.info("after...1");
    }

}
