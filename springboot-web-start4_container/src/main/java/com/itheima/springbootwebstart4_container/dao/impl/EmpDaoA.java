package com.itheima.springbootwebstart4_container.dao.impl;


import com.itheima.springbootwebstart4_container.dao.EmpDao;
import com.itheima.springbootwebstart4_container.proj.Emp;
import com.itheima.springbootwebstart4_container.utils.XmlParserUtils;
import org.springframework.stereotype.Repository;

import java.util.List;

/*
    数据访问实现类,
    使用Spring提供的注解：@Component ，就可以实现类交给IOC容器管理
    - @Controller    （标注在控制层类上）
    - @Service          （标注在业务层类上）
    - @Repository    （标注在数据访问层类上）
 */
//@Component
@Repository //相当于 @Component
public class EmpDaoA implements EmpDao {
    @Override
    public List<Emp> listEmp() {
        //1. 加载并解析emp.xml
        String file = this.getClass().getClassLoader().getResource("emp.xml").getFile();
        System.out.println(file);
        List<Emp> empList = XmlParserUtils.parse(file, Emp.class);
        return empList;
    }
}
