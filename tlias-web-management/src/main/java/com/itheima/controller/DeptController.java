package com.itheima.controller;

import com.itheima.mapper.Result;
import com.itheima.pojo.Dept;
import com.itheima.service.DeptService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@RestController
public class DeptController {
//    有更简单写法 SLF4j
//    private static Logger log = LoggerFactory.getLogger(DeptController.class);

    @Autowired
    private DeptService deptService;

    //    @RequestMapping(value = "/depts", method = GET) //有更简单写法
    @GetMapping("/depts")
    public Result list() {
        System.out.println("查询全部部门数据"); //不专业
        log.info("查询全部部门数据");

//      调用service来进行查询的逻辑
        List<Dept> deptList = deptService.list();

        return Result.success(deptList);
    }

    @DeleteMapping("/depts/{id}")
    public Result delete(@PathVariable Integer id) {
        log.info("删除一条部门数据");

        deptService.delete(id);
        return Result.success();
    }
}
