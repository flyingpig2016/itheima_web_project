package com.itheima.stage1.springcore.dao;

import com.itheima.stage1.javabasis.User;

import java.util.List;

/**
 * ==========================================
 * Spring核心学习 - 数据访问层接口
 * ==========================================
 * 
 * 【分层架构】
 * Controller（控制层）→ Service（业务层）→ Dao（数据层）
 */
public interface UserDao {

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
