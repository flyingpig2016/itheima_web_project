package com.itheima.springbootwebstart1.controller;

import com.itheima.springbootwebstart1.proj.User;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

@RestController
public class RequestController {

//    @RequestMapping("/simpleParam")
//    public String simpleParam(HttpServletRequest request){
//        String name = request.getParameter("name");
//        String ageStr = request.getParameter("age");
//        int age = Integer.parseInt(ageStr);
//        System.out.println("simpleParam接口 name="+name+",age="+age);
//        return "success";
//    }

//    @RequestMapping("/simpleParam")
//    public String simpleParam2(@RequestParam(name="name",required = false) String username, Integer age){
//        System.out.println("simpleParam2接口username="+username+",age="+age);
//        return "success";
//    }

    @RequestMapping("/simpleParam")
    public String simpleParam3(User user) {
        System.out.println("simpleParam1接口user=" + user);
        return "success";
    }

    @RequestMapping("/complexPojo")
    public String complexPojo(User user) {
        System.out.println("complexPojo接口user=" + user);
        return "success";
    }

    @RequestMapping("/arrayParam")
    public String arrayParam(String[] hobby) {

        System.out.println("arrayParam接口hobby=" + Arrays.toString(hobby));
        return "success";
    }

    @RequestMapping("/listParam")
    public String listParam(@RequestParam List<String> hobby) {
        System.out.println("listParam接口hobby=" + hobby);
        return "success";
    }

    @RequestMapping("/dateParam")
    public String dateParam(@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss") LocalDateTime updateTime) {
        System.out.println("dateParam接口birthday=" + updateTime);
        return "success";
    }

    @RequestMapping("/jsonParam")
    public String jsonParam(@RequestBody User user) {
        System.out.println("jsonParam接口user=" + user);
        return "success";
    }

    @RequestMapping("/path/{id}/{name}")
    public String path(@PathVariable("id") Integer id, @PathVariable("name") String name) {
        System.out.println("path接口id=" + id + ",name=" + name);
        return "success";
    }


}
