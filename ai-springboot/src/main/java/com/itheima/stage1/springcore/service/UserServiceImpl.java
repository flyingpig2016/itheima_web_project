package com.itheima.stage1.springcore.service;

import com.itheima.stage1.javabasis.User;
import com.itheima.stage1.springcore.dao.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * ==========================================
 * Spring核心学习 - 业务层实现
 * ==========================================
 * 
 * 【@Service注解】
 * 标识这是一个业务层的Bean
 * Spring会自动扫描并注册到IOC容器中
 * 
 * 【DI依赖注入】
 * @Autowired：自动装配，按类型自动从IOC容器中找到对应的Bean注入
 */
@Service
public class UserServiceImpl implements UserService {
    
    // 使用@Autowired自动注入UserDao
    // Spring会自动从IOC容器中找到UserDao的实现类注入
    @Autowired
    private UserDao userDao;

    @Override
    public User findById(Integer id) {
        System.out.println("[Service] 业务层：根据ID查询用户");
        // 业务逻辑可以在这里添加
        return userDao.findById(id);
    }

    @Override
    public List<User> findAll() {
        System.out.println("[Service] 业务层：查询所有用户");
        return userDao.findAll();
    }

    @Override
    public void add(User user) {
        System.out.println("[Service] 业务层：添加用户");
        // 可以在这里做参数校验、业务逻辑判断
        if (user.getAge() < 0) {
            throw new RuntimeException("年龄不能为负数");
        }
        userDao.add(user);
    }

    @Override
    public void update(User user) {
        System.out.println("[Service] 业务层：更新用户");
        userDao.update(user);
    }

    @Override
    public void delete(Integer id) {
        System.out.println("[Service] 业务层：删除用户");
        userDao.delete(id);
    }
}
