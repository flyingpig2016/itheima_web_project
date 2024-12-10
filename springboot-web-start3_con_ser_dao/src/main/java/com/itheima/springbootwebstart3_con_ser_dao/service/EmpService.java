package com.itheima.springbootwebstart3_con_ser_dao.service;

import com.itheima.springbootwebstart3_con_ser_dao.proj.Emp;

import java.util.List;

public interface EmpService {
    //获取员工列表

    List<Emp> listEmp();
}
