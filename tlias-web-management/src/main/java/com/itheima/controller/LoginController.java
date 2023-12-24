package com.itheima.controller;

import com.itheima.pojo.Emp;
import com.itheima.pojo.Result;
import com.itheima.service.EmpService;
import com.itheima.utils.JwtUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@Slf4j
@RestController
public class LoginController {

    @Autowired
    private EmpService empService;

    @PostMapping("/login")
    public Result login(@RequestBody Emp emp) {
        log.info("员工正在登录: {}", emp);
        Emp res = empService.login(emp);
        log.info("登录返回信息：{} ", res);
//        登录成功，生成令牌，下发令牌
        if (res != null) {
            Map<String, Object> claims = new HashMap<>();
            claims.put("id", res.getId());
            claims.put("name", res.getName());
            claims.put("username", res.getUsername());

            String jwt = JwtUtils.generateJwt(claims);
            log.info("登录成功，下发jwt：{}", jwt);
            return Result.success(jwt);
        }
//        登录失败，返回错误信息
        return Result.error("用户名或密码错误");
    }
}
