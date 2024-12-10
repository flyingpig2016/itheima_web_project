package com.itheima.springbootwebstart3_con_ser_dao.dao;

import com.itheima.springbootwebstart3_con_ser_dao.proj.Emp;

import java.util.List;

//数据访问层接口（制定标准）
public interface EmpDao {
    //获取员工列表数据
    public List<Emp> listEmp();
}
