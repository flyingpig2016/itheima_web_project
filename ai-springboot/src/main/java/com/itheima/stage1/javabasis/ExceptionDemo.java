package com.itheima.stage1.javabasis;

/**
 * ==========================================
 * Java基础学习 - 异常处理示例
 * ==========================================
 * 
 * 【异常体系】
 * Throwable
 * ├── Error（错误，无法处理，如内存溢出）
 * └── Exception（异常）
 * ├── 编译时异常（受检异常，必须处理）
 * └── 运行时异常（非受检异常，如NullPointerException）
 * 
 * 【异常处理方式】
 * 1. try-catch：捕获处理
 * 2. throws：声明抛出，交给调用者处理
 * 3. throw：手动抛出异常
 */
public class ExceptionDemo {

    /**
     * 自定义业务异常
     */
    public static class BusinessException extends RuntimeException {
        public BusinessException(String message) {
            super(message);
        }
    }

    /**
     * try-catch 基本用法
     */
    public static void tryCatchDemo() {
        System.out.println("\n========== try-catch 示例 ==========");

        try {
            // 可能出现异常的代码
            int[] arr = { 1, 2, 3 };
            System.out.println(arr[5]); // 数组越界异常
        } catch (ArrayIndexOutOfBoundsException e) {
            // 捕获并处理异常
            System.out.println("捕获到数组越界异常：" + e.getMessage());
            e.printStackTrace(); // 打印异常堆栈（调试用）
        } finally {
            // finally块：无论是否发生异常都会执行（用于释放资源）
            System.out.println("finally块执行了");
        }
    }

    /**
     * 多个 catch 块
     */
    public static void multiCatchDemo() {
        System.out.println("\n========== 多个catch块 示例 ==========");

        try {
            String str = null;
            System.out.println(str.length()); // 空指针异常
        } catch (NullPointerException e) {
            System.out.println("捕获到空指针异常：" + e.getMessage());
        } catch (Exception e) {
            // 捕获其他异常（父类异常要放在后面）
            System.out.println("捕获到其他异常：" + e.getMessage());
        }
    }

    /**
     * 自定义异常的使用
     */
    public static void customExceptionDemo() {
        System.out.println("\n========== 自定义异常 示例 ==========");

        try {
            login("admin", "123456");
            login("admin", "wrong"); // 密码错误，抛出异常
        } catch (BusinessException e) {
            System.out.println("业务异常：" + e.getMessage());
        }
    }

    /**
     * 模拟登录方法
     */
    private static void login(String username, String password) {
        if (!"123456".equals(password)) {
            // 手动抛出业务异常
            throw new BusinessException("密码错误");
        }
        System.out.println(username + " 登录成功");
    }

    /**
     * throws 声明抛出异常
     */
    public static void throwsDemo() throws Exception {
        System.out.println("\n========== throws 示例 ==========");
        // 这里不处理异常，声明抛出，让调用者处理
        throw new Exception("这是一个声明抛出的异常");
    }
}
