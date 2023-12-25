package com.itheima.service;

import com.itheima.pojo.Emp;
import com.itheima.pojo.PageBean;

import java.time.LocalDate;
import java.util.List;

public interface EmpService {

    PageBean page(Integer page, Integer pageSize, String name, Short gender, LocalDate begin, LocalDate end);

    void delete(List<Integer> ids);

    void insert(Emp emp);

    Emp getEmpbyId(Integer id);

    void updateEmp(Emp emp);

    Emp login(Emp emp);

    void deleteByDeptId(Integer deptId);

}
