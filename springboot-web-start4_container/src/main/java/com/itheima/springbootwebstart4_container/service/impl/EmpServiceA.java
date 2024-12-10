package com.itheima.springbootwebstart4_container.service.impl;


import com.itheima.springbootwebstart4_container.dao.EmpDao;
import com.itheima.springbootwebstart4_container.proj.Emp;
import com.itheima.springbootwebstart4_container.service.EmpService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

//使用Spring提供的注解：@Component ，就可以实现类交给IOC容器管理
//@Component
//@Primary //当有多个实现类时，优先使用该实现类
@Service //相当于@Component
public class EmpServiceA implements EmpService {
    //dao层对象
//    private EmpDao empDao = new EmpDaoA();
    //使用Spring提供的注解：@Autowired ，就可以实现程序运行时IOC容器自动注入需要的依赖对象
    //运行时,从IOC容器中获取该类型对象,赋值给该变量
    @Autowired
    private EmpDao empDao;

    @Override
    public List<Emp> listEmp() {
        //1. 调用dao, 获取数据
        List<Emp> empList = empDao.listEmp();

        //2. 对数据进行转换处理 - gender, job
        empList.stream().forEach(emp -> {
            //处理 gender 1: 男, 2: 女
            String gender = emp.getGender();
            if ("1".equals(gender)) {
                emp.setGender("男");
            } else if ("2".equals(gender)) {
                emp.setGender("女");
            }

            //处理job - 1: 讲师, 2: 班主任 , 3: 就业指导
            String job = emp.getJob();
            if ("1".equals(job)) {
                emp.setJob("讲师");
            } else if ("2".equals(job)) {
                emp.setJob("班主任");
            } else if ("3".equals(job)) {
                emp.setJob("就业指导");
            }
        });
        return empList;
    }
}
