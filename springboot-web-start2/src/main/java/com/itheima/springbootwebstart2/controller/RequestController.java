package com.itheima.springbootwebstart2.controller;


import com.itheima.springbootwebstart2.proj.Result;
import com.itheima.springbootwebstart2.proj.User;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

@RestController
public class RequestController {
    @RequestMapping("/simpleParam")
    public Result simpleParam(User user) {
        System.out.println("simpleParam3接口user=" + user);
        return Result.success(user);
    }

    @RequestMapping("/complexPojo")
    public Result complexPojo(User user) {
        System.out.println("complexPojo接口user=" + user);
        return Result.success(user);
    }

    @RequestMapping("/arrayParam")
    public Result arrayParam(String[] hobby) {

        System.out.println("arrayParam接口hobby=" + Arrays.toString(hobby));
        return Result.success(hobby);
    }

    @RequestMapping("/listParam")
    public Result listParam(@RequestParam List<String> hobby) {
        System.out.println("listParam接口hobby=" + hobby);
        return Result.success(hobby);
    }

    @RequestMapping("/dateParam")
    public Result dateParam(@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss") LocalDateTime updateTime) {
        System.out.println("dateParam接口birthday=" + updateTime);
        return Result.success(updateTime);
    }

    @RequestMapping("/jsonParam")
    public Result jsonParam(@RequestBody User user) {
        System.out.println("jsonParam接口user=" + user);
        return Result.success(user);
    }

    @RequestMapping("/path/{id}/{name}")
    public Result path(@PathVariable("id") Integer id, @PathVariable("name") String name) {
        System.out.println("path接口id=" + id + ",name=" + name);
        HashMap res = new HashMap<String, String>();
        res.put("id", id);
        res.put("name", name);

        return Result.success(res);
    }

}
