package com.itheima.stage1.javabasis;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * ==========================================
 * Java基础学习 - 类与对象示例
 * ==========================================
 * 
 * 【知识点】
 * 1. 类：是对一类事物的抽象描述
 * 2. 对象：是类的具体实例
 * 3. 属性：描述对象的特征
 * 4. 方法：描述对象的行为
 * 
 * 【Lombok注解说明】
 * 
 * @Data：自动生成getter、setter、toString、equals、hashCode
 * @NoArgsConstructor：生成无参构造方法
 *                             如：public User() {
 *                             }
 * @AllArgsConstructor：生成全参构造方法如
 *                               public User(Integer id, String username,
 *                               Integer age, String email) {
 *                               this.id = id;
 *                               this.username = username;
 *                               this.age = age;
 *                               this.email = email;
 *                               }
 *                               1.为什么要使用构造方法？，每个类都需要有一个构造方法，用于创建对象。
 *                               无参构造方法：默认提供，用于创建对象时不传递任何参数。
 *                               全参构造方法：根据类的属性，手动编写，用于创建对象时传递所有参数。
 *                               2. 全参构造方法什么时候使用？：
 *                               2.1. 当类的属性比较多，需要在创建对象时传递所有参数时。
 *                               2.2. 当类的属性有默认值，需要在创建对象时传递部分参数时。
 *                               3. 构造方法必须吗？
 *                               3.1. 是，每个类都需要有一个构造方法，用于创建对象。
 *                               3.2.
 *                               可以有多个构造方法，用于不同的场景。例如：无参构造方法、全参构造方法、部分参数构造方法。
 *                               4. 构造方法的参数可以有默认值吗？
 *                               4.1. 是，可以为构造方法的参数设置默认值。例如：
 *                               public User(Integer id, String username,
 *                               Integer age, String email)
 *                               {
 *                               this.id = id;
 *                               this.username = username;
 *                               this.age = age != null ? age : 0; //
 *                               如果年龄为null，默认设为0
 *                               this.email = email;
 *                               }
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {

    // 用户ID（属性）
    private Integer id;

    // 用户名（属性）
    private String username;

    // 年龄（属性）
    private Integer age;

    // 邮箱（属性）
    private String email;

    /**
     * 用户自我介绍的方法（行为）
     */
    public void introduce() {
        System.out.println("大家好，我是" + username + "，今年" + age + "岁");
    }

    /**
     * 判断用户是否成年
     * 
     * @return 成年返回true，否则返回false
     */
    public boolean isAdult() {
        return age != null && age >= 18;
    }
}
