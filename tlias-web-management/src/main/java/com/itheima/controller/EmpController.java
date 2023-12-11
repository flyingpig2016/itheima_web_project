package com.itheima.controller;

import com.itheima.pojo.Emp;
import com.itheima.pojo.PageBean;
import com.itheima.pojo.Result;
import com.itheima.service.EmpService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@Slf4j
@RestController
public class EmpController {

    @Autowired
    private EmpService empService;

    @GetMapping("/emps")
    public Result list(@RequestParam(defaultValue = "1") Integer page,
                       @RequestParam(defaultValue = "10") Integer pageSize,
                       String name,
                       Short gender,
                       @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate begin,
                       @DateTimeFormat(pattern = "yyy-MM-dd") LocalDate end) {
        log.info("分页查询，参数{},{}", page, pageSize);

        PageBean pageBean = empService.page(page, pageSize, name, gender, begin, end);
        return Result.success(pageBean);
    }

    @DeleteMapping("/emps/{ids}")
    public Result delete(@PathVariable List<Integer> ids) {
        log.info("批量删除，ids参数{}", ids);
        empService.delete(ids);
        return Result.success();
    }

    @PostMapping("/emps")
    public Result insert(@RequestBody Emp emp) {
        log.info("新增员工, 参数{}", emp);
        empService.insert(emp);
        return Result.success();
    }

    @GetMapping("/emps/{id}")
    public Result getEmpbyId(@PathVariable Integer id) {
        log.info("根据ID查询员工, 参数{}", id);

        Emp emp = empService.getEmpbyId(id);
        return Result.success(emp);
    }
}
