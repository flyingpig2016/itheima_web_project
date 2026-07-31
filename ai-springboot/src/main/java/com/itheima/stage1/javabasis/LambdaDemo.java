package com.itheima.stage1.javabasis;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;

/**
 * ==========================================
 * Java基础学习 - Lambda表达式示例
 * ==========================================
 * 
 * 【什么是Lambda】
 * Lambda是Java 8引入的语法糖，用于简化函数式接口的实现
 * 格式：(参数列表) -> { 方法体 }
 * 
 * 【函数式接口】
 * 只有一个抽象方法的接口，可以用@FunctionalInterface注解标识
 * 
 * 【常用内置函数式接口】
 * 1. Supplier<T>：供给型接口，无参数，返回T
 * 2. Consumer<T>：消费型接口，接收T，无返回
 * 3. Function<T, R>：函数型接口，接收T，返回R
 * 4. Predicate<T>：断言型接口，接收T，返回boolean
 */
public class LambdaDemo {

    /**
     * Lambda基本语法
     */
    public static void lambdaBasicDemo() {
        System.out.println("\n========== Lambda基本语法 ==========");

        // 传统方式：实现Runnable接口，Runable接口是什么？
        // Runnable接口是Java提供的一个函数式接口，用于表示一个没有参数没有返回值的操作
        // 它只有一个抽象方法run()，用于定义要执行的代码
        // 通常，我们会实现Runnable接口来创建一个线程，并重写run()方法来定义线程要执行的任务
        Runnable runnable1 = new Runnable() {
            @Override
            public void run() {
                System.out.println("传统方式实现");
            }
        };

        // Lambda方式1：完整写法
        Runnable runnable2 = () -> {
            System.out.println("Lambda完整写法");
        };

        // Lambda方式2：简化写法（只有一条语句时可省略大括号）
        Runnable runnable3 = () -> System.out.println("Lambda简化写法");

        runnable1.run();
        runnable2.run();
        runnable3.run();
    }

    /**
     * 带参数的Lambda
     */
    public static void lambdaWithParamsDemo() {
        System.out.println("\n========== 带参数的Lambda ==========");

        // 传统方式
        Comparator<Integer> comparator1 = new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return o1 - o2;
            }
        };

        // Lambda方式1：完整写法
        Comparator<Integer> comparator2 = (Integer o1, Integer o2) -> {
            return o1 - o2;
        };

        // Lambda方式2：简化写法（类型可推断，单条语句可省略return）
        Comparator<Integer> comparator3 = (o1, o2) -> o1 - o2;

        List<Integer> list = new ArrayList<>();
        list.add(5);
        list.add(2);
        list.add(8);
        Collections.sort(list, comparator3);
        System.out.println("排序后：" + list);
    }

    /**
     * Supplier供给型接口：无参数，返回值
     */
    public static void supplierDemo() {
        System.out.println("\n========== Supplier供给型接口 ==========");

        // 供给型接口：获取一个随机数
        Supplier<Double> randomSupplier = () -> Math.random();
        System.out.println("随机数：" + randomSupplier.get());

        // 供给型接口：获取当前时间
        Supplier<Long> timeSupplier = System::currentTimeMillis;
        System.out.println("当前时间戳：" + timeSupplier.get());
    }

    /**
     * Consumer消费型接口：有参数，无返回值
     */
    public static void consumerDemo() {
        System.out.println("\n========== Consumer消费型接口 ==========");

        // 消费型接口：打印字符串
        Consumer<String> printer = s -> System.out.println("打印：" + s);
        printer.accept("Hello Lambda");

        // 消费型接口：遍历集合
        List<String> list = new ArrayList<>();
        list.add("Java");
        list.add("Python");
        list.add("Go");
        list.forEach(s -> System.out.println("遍历：" + s));
    }

    /**
     * Function函数型接口：有参数，有返回值
     */
    public static void functionDemo() {
        System.out.println("\n========== Function函数型接口 ==========");

        // 函数型接口：字符串转大写
        Function<String, String> toUpper = s -> s.toUpperCase();
        System.out.println("转大写：" + toUpper.apply("hello"));

        // 函数型接口：字符串长度
        Function<String, Integer> lengthFunc = String::length; // 相当于 lambda表达式 (s) -> s.length()
        System.out.println("字符串长度：" + lengthFunc.apply("Java"));
    }

    /**
     * Predicate断言型接口：有参数，返回boolean
     */
    public static void predicateDemo() {
        System.out.println("\n========== Predicate断言型接口 ==========");

        // 断言型接口：判断是否大于10
        Predicate<Integer> isGreaterThan10 = n -> n > 10;
        System.out.println("5 > 10？" + isGreaterThan10.test(5));
        System.out.println("15 > 10？" + isGreaterThan10.test(15));

        // 断言型接口：判断字符串是否为空
        Predicate<String> isNotEmpty = s -> s != null && !s.isEmpty();
        System.out.println("\"\"不为空？" + isNotEmpty.test(""));
        System.out.println("\"Hello\"不为空？" + isNotEmpty.test("Hello"));
    }

    /**
     * 方法引用（进一步简化Lambda）
     */
    public static void methodReferenceDemo() {
        System.out.println("\n========== 方法引用 ==========");

        List<String> list = new ArrayList<>();
        list.add("apple");
        list.add("banana");
        list.add("cherry");

        // 1. 对象::实例方法
        list.forEach(System.out::println); // 相当于 (s) -> System.out.println(s)

        // 2. 类::静态方法
        list.stream().map(Integer::parseInt); // 虽然这里类型不匹配，但演示语法 相当于 (s) -> Integer.parseInt(s)

        // 3. 类::实例方法
        list.stream().map(String::toUpperCase); // 相当于 (s) -> s.toUpperCase()

        // 4. 类::new（构造方法引用）
        Supplier<List<String>> listSupplier = ArrayList::new; // 相当于 () -> new ArrayList<>()
        list = listSupplier.get();
    }
}
