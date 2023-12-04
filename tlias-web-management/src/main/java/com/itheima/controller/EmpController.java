package com.itheima.controller;

import com.itheima.mapper.Result;
import com.itheima.pojo.Emp;
import com.itheima.service.EmpService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@RestController
public class EmpController {

    @Autowired
    private EmpService empService;

    @GetMapping("/emps")
    public Result list() {
        log.info("查询全部部门数据");
        List<Emp> empList = empService.list();
        return Result.success(empList);
    }
}
