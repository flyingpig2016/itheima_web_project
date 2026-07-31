package com.itheima.stage1.javabasis.annotation;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

/**
 * ==========================================
 * Java基础学习 - 注解使用和反射示例
 * ==========================================
 */

// 在类上使用自定义注解
@MyAnnotation(
    name = "演示类",
    description = "这是一个注解演示类",
    tags = {"demo", "annotation"}
)
public class AnnotationDemo {
    
    // 在字段上使用注解
    @MyAnnotation(name = "用户名字段", description = "存储用户的姓名")
    private String username;
    
    // 在方法上使用注解
    @MyAnnotation(name = "展示方法", description = "展示一些信息")
    public void showInfo() {
        System.out.println("这是showInfo方法");
    }
    
    // 使用value属性，可以省略属性名
    @MyAnnotation("简单注解")
    public void simpleMethod() {
    }
}

/**
 * 反射读取注解示例
 */
class ReflectionAnnotationDemo {
    
    public static void main(String[] args) throws Exception {
        System.out.println("\n========== 反射读取注解 ==========");
        
        // 1. 获取类上的注解
        Class<AnnotationDemo> clazz = AnnotationDemo.class;
        MyAnnotation classAnnotation = clazz.getAnnotation(MyAnnotation.class);
        System.out.println("类注解：");
        System.out.println("  name: " + classAnnotation.name());
        System.out.println("  description: " + classAnnotation.description());
        
        // 2. 获取字段上的注解
        Field field = clazz.getDeclaredField("username");
        MyAnnotation fieldAnnotation = field.getAnnotation(MyAnnotation.class);
        System.out.println("\n字段注解：");
        System.out.println("  name: " + fieldAnnotation.name());
        
        // 3. 获取方法上的注解
        Method method = clazz.getDeclaredMethod("showInfo");
        MyAnnotation methodAnnotation = method.getAnnotation(MyAnnotation.class);
        System.out.println("\n方法注解：");
        System.out.println("  name: " + methodAnnotation.name());
    }
}
