package com.itheima.springbootwebstart3_con_ser_dao.dao.impl;

import com.itheima.springbootwebstart3_con_ser_dao.dao.EmpDao;
import com.itheima.springbootwebstart3_con_ser_dao.proj.Emp;
import com.itheima.springbootwebstart3_con_ser_dao.utils.XmlParserUtils;

import java.util.List;

//数据访问实现类
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
