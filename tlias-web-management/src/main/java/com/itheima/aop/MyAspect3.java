package com.itheima.aop;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Slf4j
//@Aspect
@Component
@Order(1)
public class MyAspect3 {

    //    声明成public，其他地方可以使用pt()
//    @Pointcut("execution(public void com.itheima.service.impl.DeptServiceImpl.delete(Integer))")
//    @Pointcut("execution(public void delete(java.lang.Integer))") //不建议省略
//    @Pointcut("execution(* com.*.service.DeptService.*(*))")
//    @Pointcut("execution(* com.itheima.service.*Service.delete*(*))")
//    @Pointcut("execution(* com.itheima.service.impl.DeptServiceImpl.*(..))") //任意个任意类型的参数
//    @Pointcut("execution(* com..impl.DeptServiceImpl.*(..))") //任意层级的包
    @Pointcut("execution(* com.itheima.service.DeptService.list()) || execution(* com.itheima.service.DeptService.delete(java.lang.Integer))")
    //任意层级的包

    public void pt() {
    }

    @Before("pt()")
    public void before() {
        log.info("before...3");
    }

    @After("pt()")
    public void after() {
        log.info("after...3");
    }

}
