package com.itheima.stage1;

import com.itheima.stage1.javabasis.*;
import com.itheima.stage1.springcore.service.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * ==========================================
 * 第一阶段学习测试类
 * ==========================================
 * 
 * 【使用说明】
 * 1. 右键点击方法名 → Run
 * 2. 或者在方法名左侧点击绿色运行按钮
 * 3. 按顺序学习，理解每个示例的注释
 */
@SpringBootTest
class Stage1Test {

    // ==================== Java基础部分 ====================

    /**
     * 测试1：类与对象
     */
    @Test
    void testClassAndObject() {
        System.out.println("========== 测试1：类与对象 ==========");

        // 创建对象
        User user = new User();

        // 设置属性
        user.setId(1);
        user.setUsername("张三");
        user.setAge(20);
        user.setEmail("zhangsan@example.com");

        // 调用方法
        user.introduce(); // 调用方法 输出：我是张三，年龄20岁
        System.out.println("是否成年：" + user.isAdult()); // 输出：false
        System.out.println("用户信息：" + user); // 输出：User{id=1, username='张三', age=20, email='zhangsan@example.com'}

        // 使用全参构造
        User user2 = new User(2, "李四", 17, "lisi@example.com");
        user2.introduce(); // 输出：我是李四，年龄17岁
        System.out.println("是否成年：" + user2.isAdult()); // 输出：false
    }

    /**
     * 测试2：集合框架
     */
    @Test
    void testCollection() {
        System.out.println("========== 测试2：集合框架 ==========");

        CollectionDemo.arrayListDemo();
        CollectionDemo.hashSetDemo();
        CollectionDemo.hashMapDemo();
        CollectionDemo.streamDemo();
    }

    /**
     * 测试3：异常处理
     */
    @Test
    void testException() {
        System.out.println("========== 测试3：异常处理 ==========");

        ExceptionDemo.tryCatchDemo();
        ExceptionDemo.multiCatchDemo();
        ExceptionDemo.customExceptionDemo();

        try {
            ExceptionDemo.throwsDemo();
        } catch (Exception e) {
            System.out.println("捕获到throws声明的异常：" + e.getMessage());
        }
    }

    /**
     * 测试4：Lambda表达式
     */
    @Test
    void testLambda() {
        System.out.println("========== 测试4：Lambda表达式 ==========");

        LambdaDemo.lambdaBasicDemo();
        LambdaDemo.lambdaWithParamsDemo();
        LambdaDemo.supplierDemo();
        LambdaDemo.consumerDemo();
        LambdaDemo.functionDemo();
        LambdaDemo.predicateDemo();
        LambdaDemo.methodReferenceDemo();
    }

    // ==================== Spring核心部分 ====================

    // 注入UserService（演示IOC和DI） ，IOC代表控制反转，DI代表依赖注入
    @Autowired
    private UserService userService;

    /**
     * 测试5：IOC容器和DI依赖注入
     * 演示从IOC容器中获取Bean
     * 一： 什么事是IOC容器呢？
     * IOC容器是Spring框架的核心组件，也称为依赖注入容器（DI Container）
     * 它负责管理应用程序中的Bean（对象），并自动处理它们之间的依赖关系
     * 主要功能包括：
     * 1. 实例化Bean：根据配置创建对象实例
     * 2. 依赖注入：自动将依赖的Bean注入到目标Bean中
     * 3. 生命周期管理：管理Bean的创建、初始化、销毁等生命周期
     * 二： 什么是DI依赖注入呢？
     * DI（依赖注入）是IOC的一种实现方式，它通过将依赖的Bean自动注入到目标Bean中，实现了组件之间的解耦
     * 主要功能包括：
     * 1. 构造函数注入：通过构造函数参数传递依赖
     * 2. Setter方法注入：通过Setter方法传递依赖
     * 3. 接口注入：通过接口方法传递依赖
     */
    @Test
    void testIOCAndDI() {
        System.out.println("========== 测试5：IOC容器和DI依赖注入 ==========");

        System.out.println("userService对象：" + userService);
        System.out.println("userService类名：" + userService.getClass().getName());
        System.out.println("说明：这个UserService是Spring自动从IOC容器中注入的！");
    }

    /**
     * 测试6：完整的三层架构调用（包含AOP）
     * 演示Controller → Service → Dao
     * 同时可以看到AOP切面的效果
     */
    @Test
    void testThreeLayers() {
        System.out.println("========== 测试6：三层架构 + AOP ==========");

        // 1. 查询所有用户
        System.out.println("\n--- 1. 查询所有用户 ---");
        userService.findAll();

        // 2. 根据ID查询用户
        System.out.println("\n--- 2. 根据ID查询用户 ---");
        User user = userService.findById(1);
        System.out.println("查询结果：" + user);

        // 3. 添加用户
        System.out.println("\n--- 3. 添加用户 ---");
        User newUser = new User(4, "赵六", 28, "zhaoliu@example.com");
        userService.add(newUser);

        // 4. 更新用户
        System.out.println("\n--- 4. 更新用户 ---");
        newUser.setAge(29);
        userService.update(newUser);

        // 5. 删除用户
        System.out.println("\n--- 5. 删除用户 ---");
        userService.delete(4);

        System.out.println("\n说明：观察控制台输出，可以看到AOP切面的日志！");
    }

    /**
     * 测试7：所有示例一键运行
     */
    @Test
    void testAll() {
        System.out.println("========== 第一阶段学习 - 完整示例 ==========");
        System.out.println("这会依次运行所有示例，请仔细观察控制台输出\n");

        testClassAndObject();
        testCollection();
        testException();
        testLambda();
        testIOCAndDI();
        testThreeLayers();

        System.out.println("\n========== 恭喜！第一阶段学习完成！ ==========");
    }
}
