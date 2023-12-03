package com.itheima;

import com.itheima.mapper.UserMapper;
import com.itheima.pojo.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

@SpringBootTest
class SpringbootMybatisQuickstarApplicationTests {
    @Autowired
    private UserMapper userMapper;

    //查询用户信息
    @Test
    public void testListUser() {
        List<User> userList = userMapper.list();
        userList.stream().forEach(user -> {
            System.out.println(user);
        });
    }


    //    普通写法
    @Test
    public void testJdbc() throws Exception {
        //	1.注册驱动
        Class.forName("com.mysql.cj.jdbc.Driver");

        // 2.获取连接对象
        String url = "jdbc:mysql://localhost:3306/mybatis";
        String username = "root";
        String password = "hahaha@123";
        Connection connection = DriverManager.getConnection(url, username, password);

        // 3. 获取执行sql的对象statement ，执行sql，返回结果
        String sql = "select * from user";
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery(sql);

        // 4. 封装结果数据
        List<User> userList = new ArrayList<>();
        while (resultSet.next()) {
            int id = resultSet.getInt("id");
            String name = resultSet.getString("name");
            Short age = resultSet.getShort("age");
            Short gender = resultSet.getShort("gender");
            String phone = resultSet.getString("phone");

            User user = new User(id, name, age, gender, phone);
            userList.add(user);
        }
        // 5. 释放资源
        statement.close();
        connection.close();

        System.out.println(userList);
        userList.stream().forEach(user -> {
            System.out.println(user);
        });
    }
}
