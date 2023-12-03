package com.itheima.mystudy.controller;

import com.itheima.mystudy.pojo.Address;
import com.itheima.mystudy.pojo.User;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

// @Controller + @ResponseBody
@RestController
public class RequestController {
//        1. 简单参数
//    @RequestMapping("/simpleParam")
//    public String simpleParam(HttpServletRequest request){
//        String name = request.getParameter("name");
//        int age = Integer.parseInt(request.getParameter("age"));
//        System.out.println(name + ':' + age);
//        return "OK";
//    }

    //参数分开,直接写属性
//    @RequestMapping("/simpleParam")
//    public String simplePojo(Integer age, String name) {
//        //获取请求参数 比较繁琐
//        System.out.println(name + ':' + age);
//        return "OK";
//    }

    //    指定参数名 不区分post get
    @RequestMapping("/simpleParam")
    public String simpleParam(@RequestParam(name = "name") String username, @RequestParam(name = "age") Integer myAge) {
        //获取请求参数 比较繁琐
        System.out.println(username + ':' + myAge);
        return "OK";
    }

    // 实体参数
//    @RequestMapping("/simplePojo")
//    public String simplePojo(User user) {
//        String username = user.getName();
//        Integer myAge = user.getAge();
//        System.out.println(username + ':' + myAge);
//        return "OK";
//    }

    //    复杂实体参数
//    @RequestMapping("/complexPojo")
    @PostMapping("/complexPojo1")
    public String complexPojo(User user) {
        String username = user.getName();
        Integer myAge = user.getAge();
        Address address = user.getAddress();
        String province = address.getProvince();
        String city = address.getCity();
        System.out.println(username + ':' + myAge + ':' + province + ':' + city);
        return "OK";
    }

    // 数组参数
    @RequestMapping("/arrayParam")
    public String arrayParam(String[] hobby) {
        System.out.println(Arrays.toString(hobby));
        return "OK";
    }

    // list参数
    @RequestMapping("/listParam")
    public String arrayList(@RequestParam List<String> hobby) {
        System.out.println(hobby.toString());
        return "OK";
    }

    // date参数
    @RequestMapping("/dateParam")
    public String dateParam(@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss") LocalDateTime updateTime) {
        System.out.println(updateTime);
        return "OK";
    }

    // JSON 参数 需要用到 requestBody
    @RequestMapping("/jsonParam")
    public String jsonParam(@RequestBody User user) {
        System.out.println(user);
        return "OK";
    }

    // 路径参数
    @RequestMapping("/path/{id}")
    public String path(@PathVariable Integer id) {
        System.out.println(id);
        return "OK";
    }

    // 多路径参数
    @RequestMapping("/path/{id}/{name}")
    public String path1(@PathVariable Integer id, @PathVariable String name) {
        System.out.println(id + ":" + name);
        return "OK";
    }


}
