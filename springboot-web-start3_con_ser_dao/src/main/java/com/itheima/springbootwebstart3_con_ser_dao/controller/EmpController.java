package com.itheima.springbootwebstart3_con_ser_dao.controller;

import com.itheima.springbootwebstart3_con_ser_dao.proj.Emp;
import com.itheima.springbootwebstart3_con_ser_dao.proj.Result;
import com.itheima.springbootwebstart3_con_ser_dao.service.EmpService;
import com.itheima.springbootwebstart3_con_ser_dao.service.impl.EmpServiceA;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class EmpController {
    //业务层对象
    private EmpService empService = new EmpServiceA();

    @RequestMapping("/listEmp")
    public Result list() {
        //1. 调用service层, 获取数据
        List<Emp> empList = empService.listEmp();

        //3. 响应数据
        return Result.success(empList);
    }
}
