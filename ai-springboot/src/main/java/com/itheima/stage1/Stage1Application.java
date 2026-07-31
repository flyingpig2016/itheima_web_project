package com.itheima.stage1;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * ==========================================
 * SpringBoot第一阶段学习项目入口类
 * ==========================================
 * 
 * 【学习目标】
 * 1. Java基础复习（类与对象、集合、异常、Lambda、注解、反射）
 * 2. Spring核心概念（IOC容器、Bean管理、DI依赖注入）
 * 3. AOP切面编程核心思想
 * 4. 注解式开发思维
 * 
 * 【学习建议】
 * 按顺序运行各个学习模块，理解每个示例的注释
 */
@SpringBootApplication
public class Stage1Application {

    public static void main(String[] args) {
        // 启动SpringBoot应用
        SpringApplication.run(Stage1Application.class, args);
        
        System.out.println("\n========================================");
        System.out.println("  SpringBoot第一阶段学习项目启动成功！");
        System.out.println("  请在src/test/java目录下运行测试类学习");
        System.out.println("========================================\n");
    }
}
