package com.itheima.stage1.springcore.service;

import com.itheima.stage1.javabasis.User;

import java.util.List;

/**
 * ==========================================
 * Spring核心学习 - 业务层接口
 * ==========================================
 */
public interface UserService {
    
    /**
     * 根据ID查询用户
     */
    User findById(Integer id);
    
    /**
     * 查询所有用户
     */
    List<User> findAll();
    
    /**
     * 添加用户
     */
    void add(User user);
    
    /**
     * 更新用户
     */
    void update(User user);
    
    /**
     * 删除用户
     */
    void delete(Integer id);
}
