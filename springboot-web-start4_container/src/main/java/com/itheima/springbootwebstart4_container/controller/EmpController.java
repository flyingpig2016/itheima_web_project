package com.itheima.springbootwebstart4_container.controller;

import com.itheima.springbootwebstart4_container.proj.Emp;
import com.itheima.springbootwebstart4_container.proj.Result;
import com.itheima.springbootwebstart4_container.service.EmpService;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

//@RestController = @Controller + @ResponseBody
@RestController
public class EmpController {
    //业务层对象
//    private EmpService empService = new EmpServiceA();
    //Qualifier注解不能单独使用，必须配合@Autowired使用
//    @Qualifier("empServiceA") //在@Qualifier的value属性中，指定注入的bean的名称。
//    @Autowired ////运行时,从IOC容器中获取该类型对象,赋值给该变量,使用Spring提供的注解：@Autowired ，就可以实现程序运行时IOC容器自动注入需要的依赖对象
    @Resource(name = "empServiceB") // 是按照bean的名称进行注入。通过name属性指定要注入的bean的名称。
    private EmpService empService;

    @RequestMapping("/listEmp")
    public Result list() {
        //1. 调用service层, 获取数据
        List<Emp> empList = empService.listEmp();

        //3. 响应数据
        return Result.success(empList);
    }
}
