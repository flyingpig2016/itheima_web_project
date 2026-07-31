package com.itheima.stage1.javabasis.annotation;

import java.lang.annotation.*;

/**
 * ==========================================
 * Java基础学习 - 自定义注解示例
 * ==========================================
 * 
 * 【元注解】
 * 用于修饰注解的注解
 * 
 * 1. @Target：指定注解可以使用的位置
 *    - TYPE：类、接口、枚举
 *    - FIELD：字段
 *    - METHOD：方法
 *    - PARAMETER：参数
 *    - CONSTRUCTOR：构造方法
 *    - LOCAL_VARIABLE：局部变量
 * 
 * 2. @Retention：指定注解的生命周期
 *    - SOURCE：源码阶段，编译后丢弃
 *    - CLASS：编译阶段，保留到class文件，运行时丢弃
 *    - RUNTIME：运行时保留，可通过反射读取
 * 
 * 3. @Documented：注解是否会被包含在JavaDoc中
 * 
 * 4. @Inherited：注解是否可以被子类继承
 */
@Target({ElementType.TYPE, ElementType.METHOD, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface MyAnnotation {
    
    /**
     * 注解属性：名称
     * 格式：属性类型 属性名() [default 默认值];
     */
    String name() default "默认名称";
    
    /**
     * 注解属性：描述
     */
    String description() default "";
    
    /**
     * 注解属性：年龄
     */
    int age() default 0;
    
    /**
     * 注解属性：数组
     */
    String[] tags() default {};
    
    /**
     * 如果只有一个属性且名为value，可以省略属性名
     */
    String value() default "";
}
