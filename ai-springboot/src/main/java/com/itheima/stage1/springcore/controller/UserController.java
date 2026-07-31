package com.itheima.stage1.springcore.controller;

import com.itheima.stage1.javabasis.User;
import com.itheima.stage1.springcore.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * ==========================================
 * Spring核心学习 - 控制层
 * ==========================================
 * 
 * 【@RestController注解】
 * 相当于@Controller + @ResponseBody的组合
 * 标识这是一个Web控制层的Bean，所有方法返回JSON
 * 
 * 【@RequestMapping注解】
 * 定义请求映射路径
 */
@RestController
@RequestMapping("/api/users")
public class UserController {
    
    // 注入业务层
    @Autowired
    private UserService userService;
    
    /**
     * 根据ID查询用户
     * GET请求：/api/users/{id}
     */
    @GetMapping("/{id}")
    public User findById(@PathVariable Integer id) {
        System.out.println("[Controller] 控制层：根据ID查询用户");
        return userService.findById(id);
    }
    
    /**
     * 查询所有用户
     * GET请求：/api/users
     */
    @GetMapping
    public List<User> findAll() {
        System.out.println("[Controller] 控制层：查询所有用户");
        return userService.findAll();
    }
    
    /**
     * 添加用户
     * POST请求：/api/users
     */
    @PostMapping
    public String add(@RequestBody User user) {
        System.out.println("[Controller] 控制层：添加用户");
        userService.add(user);
        return "添加成功";
    }
    
    /**
     * 更新用户
     * PUT请求：/api/users
     */
    @PutMapping
    public String update(@RequestBody User user) {
        System.out.println("[Controller] 控制层：更新用户");
        userService.update(user);
        return "更新成功";
    }
    
    /**
     * 删除用户
     * DELETE请求：/api/users/{id}
     */
    @DeleteMapping("/{id}")
    public String delete(@PathVariable Integer id) {
        System.out.println("[Controller] 控制层：删除用户");
        userService.delete(id);
        return "删除成功";
    }
}
