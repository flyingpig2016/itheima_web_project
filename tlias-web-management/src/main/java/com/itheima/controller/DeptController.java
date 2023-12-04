package com.itheima.controller;

import com.itheima.mapper.Result;
import com.itheima.pojo.Dept;
import com.itheima.service.DeptService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
public class DeptController {
//    有更简单写法 SLF4j
//    private static Logger log = LoggerFactory.getLogger(DeptController.class);

    @Autowired
    private DeptService deptService;

    //    @RequestMapping(value = "/depts", method = GET) //有更简单写法
//    查询部门 *********
    @GetMapping("/depts")
    public Result list() {
//        System.out.println("查询全部部门数据"); //不专业
        log.info("查询全部部门数据");

//      调用service来进行查询的逻辑
        List<Dept> deptList = deptService.list();

        return Result.success(deptList);
    }

    //    删除部门*********
    @DeleteMapping("/depts/{id}")
    public Result delete(@PathVariable Integer id) {
        log.info("根据id删除部门:{}", id);

        deptService.delete(id);
        return Result.success();
    }

    //    添加部门
    @PostMapping("/depts")
    public Result addDept(@RequestBody Dept dept) {
        log.info("新增部门: {}", dept);

        deptService.addDept(dept);
        return Result.success();
    }

    //根据部门id查询
    @RequestMapping("/depts/{id}")
    public Result getDept(@PathVariable Integer id) {
        Dept dept = deptService.getDept(id);
        return Result.success(dept);
    }

    @PutMapping("/depts")
    public Result updateDept(@RequestBody Dept dept) {
        System.out.println(dept.toString());

        deptService.updateDept(dept);
        return Result.success();
    }

}
