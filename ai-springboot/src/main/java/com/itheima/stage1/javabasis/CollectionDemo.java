package com.itheima.stage1.javabasis;

import java.util.*;
import java.util.stream.Collectors;

/**
 * ==========================================
 * Java基础学习 - 集合框架示例
 * ==========================================
 * 
 * 【集合框架体系】
 * ├── Collection（单列集合）
 * │ ├── List（有序、可重复）
 * │ │ ├── ArrayList（数组实现，查询快，增删慢）
 * │ │ └── LinkedList（链表实现，查询慢，增删快）
 * │ └── Set（无序、不可重复）
 * │ ├── HashSet（哈希表实现）
 * │ └── TreeSet（红黑树实现，排序）
 * └── Map（双列集合，键值对）
 * ├── HashMap（哈希表实现）
 * └── TreeMap（红黑树实现，排序）
 */
public class CollectionDemo {

    /**
     * ArrayList 示例
     * 特点：有序、可重复、查询快、增删慢
     */
    public static void arrayListDemo() {
        System.out.println("\n========== ArrayList 示例 ==========");

        // 1. 创建ArrayList
        List<String> list = new ArrayList<>();

        // 2. 添加元素
        list.add("Java");
        list.add("Python");
        list.add("Go");
        list.add("Java"); // 可重复
        System.out.println("添加后：" + list);

        // 3. 获取元素
        String first = list.get(0);
        System.out.println("第一个元素：" + first);

        // 4. 修改元素
        // 为何不能 list[1] = "Python3"？ 因为list是一个对象，不能用数组的方式访问元素, 只能用set方法修改元素,
        // 我想知道 什么时候可以用list[0] ， 答案是：当你确定集合中至少有一个元素时，才可以用list[0]访问第一个元素举个例子说明
        // String[] array = {"Java", "Python", "Go"}; 和ArrayList 有什么区别？
        // 区别1：ArrayList是一个类，而array是一个数组，数组的长度是固定的，不能动态改变，而ArrayList的长度是可以动态改变的
        // 区别2：ArrayList可以存储任意类型的对象，而array只能存储相同类型的对象

        list.set(1, "Python3");
        System.out.println("修改后：" + list);

        // 5. 删除元素
        list.remove(2);
        System.out.println("删除索引2后：" + list);

        // 6. 获取大小
        System.out.println("集合大小：" + list.size());

        // 7. 遍历
        System.out.print("遍历：");
        for (String s : list) {
            System.out.print(s + " ");
        }
    }

    /**
     * HashSet 示例
     * 特点：无序、不可重复
     * 一: HashSet和Set的区别：
     * HashSet是Set的一个实现类，Set是一个接口，HashSet基于哈希表实现，Set基于抽象数据类型实现的，Set接口中定义了Set的常用方法，而HashSet类实现了Set接口，并实现了HashSet的常用方法
     * 二: HashSet和TreeSet的区别：
     * 1·HashSet基于哈希表实现，查询和插入操作的时间复杂度为O(1)，而TreeSet基于红黑树实现，查询和插入操作的时间复杂度为O(log
     * n)，因此HashSet适用于数据量较小的场景，而TreeSet适用于数据量较大的场景
     * 2·HashSet不保证元素的顺序，而TreeSet保证元素的有序性（默认自然排序或自定义Comparator）
     * 三: HashSet的实现原理：
     * HashSet基于哈希表实现，哈希表是一个数组，每个数组元素都是一个链表，当向哈希表中添加元素时，会根据元素的哈希值计算出该元素在数组中的位置，然后将该元素添加到对应的链表中
     * 当从哈希表中查询元素时，也会根据元素的哈希值计算出该元素在数组中的位置，然后遍历该位置的链表，直到找到该元素或遍历完整个链表
     * 因此，HashSet的查询和插入操作的时间复杂度为O(1)，这是 HashSet 速度快的原因
     * 四: TreeSet的实现原理：
     * TreeSet基于红黑树实现，红黑树是一种自平衡二叉查找树，它的每个节点都有一个颜色（红色或黑色），以及指向左子树、右子树和父节点的指针
     * 当向TreeSet中添加元素时，会根据元素的自然排序或自定义Comparator(翻译为：比较器)的顺序将元素插入到红黑树的合适位置
     * 当从TreeSet中查询元素时，会根据元素的自然排序或自定义Comparator(翻译为：比较器)的顺序在红黑树中查找元素
     * 因此，TreeSet的查询和插入操作的时间复杂度为O(log n)，这是 TreeSet 速度慢的原因
     */
    public static void hashSetDemo() {
        System.out.println("\n========== HashSet 示例 ==========");

        Set<String> set = new HashSet<>();

        // 添加元素
        set.add("苹果");
        set.add("香蕉");
        set.add("橙子");
        set.add("苹果"); // 重复元素不会被添加

        System.out.println("Set内容（无序）：" + set);
        System.out.println("是否包含香蕉：" + set.contains("香蕉"));

        // 遍历
        System.out.print("遍历：");
        for (String fruit : set) {
            System.out.print(fruit + " ");
        }
    }

    /**
     * HashMap 示例 默认是无序的，但是可以通过LinkedHashMap来实现有序
     * 特点：键值对存储，键不可重复，值可重复
     */
    public static void hashMapDemo() {
        System.out.println("\n========== HashMap 示例 ==========");

        Map<Integer, String> map = new HashMap<>();

        // 1. 添加键值对
        map.put(1, "张三");
        map.put(2, "李四");
        map.put(3, "王五");
        map.put(1, "张三丰"); // 键重复会覆盖原值
        System.out.println("Map内容：" + map);

        // 2. 根据键获取值
        String name = map.get(2);
        System.out.println("键2对应的值：" + name);

        // 3. 判断键是否存在
        System.out.println("是否包含键3：" + map.containsKey(3));

        // 4. 遍历所有键
        System.out.print("所有键：");
        for (Integer key : map.keySet()) {
            System.out.print(key + " ");
        }

        // 5. 遍历所有值
        System.out.print("\n所有值：");
        for (String value : map.values()) {
            System.out.print(value + " ");
        }

        // 6. 遍历键值对（最常用）
        System.out.print("\n键值对：");
        for (Map.Entry<Integer, String> entry : map.entrySet()) {
            System.out.print(entry.getKey() + "=" + entry.getValue() + " ");
        }
    }

    /**
     * Stream API 示例（Java 8+）
     * 用于集合的高效处理（过滤、映射、排序、收集等），用来替代传统的for循环和迭代器，通常用在数据处理场景中，比如：筛选、转换、排序、聚合等，
     * 它的基本思想是将集合中的元素转换为一个流（Stream），对流进行各种操作（过滤、映射、排序等），最后将结果收集到一个新的集合中，比传统的for循环和迭代器更简洁、更易读
     */
    public static void streamDemo() {
        System.out.println("\n========== Stream API 示例 ==========");

        // 创建用户列表
        // asList() 方法返回的是一个固定大小的列表，不能进行添加或删除操作，只能进行查询操作
        // 如果需要对列表进行添加或删除操作，需要先将其转换为可修改的列表，比如：ArrayList, LinkedList等
        List<User> users = Arrays.asList(
                new User(1, "张三", 20, "zhangsan@example.com"),
                new User(2, "李四", 17, "lisi@example.com"),
                new User(3, "王五", 25, "wangwu@example.com"),
                new User(4, "赵六", 30, "zhaoliu@example.com"));

        // 需求：筛选出成年用户，按年龄排序，只取名字
        List<String> adultNames = users.stream()
                .filter(user -> user.getAge() >= 18) // 过滤：只保留成年
                .sorted(Comparator.comparing(User::getAge)) // 排序：按年龄
                .map(User::getUsername) // 映射：只取用户名
                .collect(Collectors.toList()); // 收集：转成List

        System.out.println("成年用户姓名（按年龄排序）：" + adultNames);
    }
}
