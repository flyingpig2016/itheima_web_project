package com.itheima.stage1.springcore.dao;

import com.itheima.stage1.javabasis.User;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * ==========================================
 * Spring核心学习 - 数据访问层实现
 * ==========================================
 * 
 * 【@Repository注解】
 * 标识这是一个数据访问层的Bean
 * Spring会自动扫描并注册到IOC容器中
 */
@Repository
public class UserDaoImpl implements UserDao {
    
    // 模拟数据库（用Map存储）
    private final Map<Integer, User> userMap = new HashMap<>();
    
    // 初始化一些数据
    public UserDaoImpl() {
        userMap.put(1, new User(1, "张三", 20, "zhangsan@example.com"));
        userMap.put(2, new User(2, "李四", 25, "lisi@example.com"));
        userMap.put(3, new User(3, "王五", 30, "wangwu@example.com"));
    }

    @Override
    public User findById(Integer id) {
        System.out.println("[Dao] 根据ID查询用户：" + id);
        return userMap.get(id);
    }

    @Override
    public List<User> findAll() {
        System.out.println("[Dao] 查询所有用户");
        return new ArrayList<>(userMap.values());
    }

    @Override
    public void add(User user) {
        System.out.println("[Dao] 添加用户：" + user);
        userMap.put(user.getId(), user);
    }

    @Override
    public void update(User user) {
        System.out.println("[Dao] 更新用户：" + user);
        userMap.put(user.getId(), user);
    }

    @Override
    public void delete(Integer id) {
        System.out.println("[Dao] 删除用户：" + id);
        userMap.remove(id);
    }
}
