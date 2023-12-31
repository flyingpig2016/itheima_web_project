package com.itheima.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.itheima.mapper.EmpMapper;
import com.itheima.pojo.Emp;
import com.itheima.pojo.PageBean;
import com.itheima.service.EmpService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class EmpServiceImpl implements EmpService {
    @Autowired
    private EmpMapper empMapper;

    @Override
    public PageBean page(Integer page, Integer pageSize, String name, Short gender, LocalDate begin, LocalDate end) {
        //以下比较繁琐，使用分页插件PageHelper
//        Integer start = (page - 1) * pageSize;
//
//        List<Emp> list = empMapper.page(start, pageSize);
//        Long total = empMapper.count();
//
//        PageBean pageBean = new PageBean();
//        pageBean.setTotal(total);
//        pageBean.setRows(list);
//        return pageBean;

        //1. 设置分页参数
        PageHelper.startPage(page, pageSize);

        //2. 这个Page类可以 查询总数据条数和 分页查询的list
        List<Emp> empList = empMapper.list(name, gender, begin, end);
        Page<Emp> p = (Page<Emp>) empList;

        //3. 封装PageBean对象
        PageBean pageBean = new PageBean(p.getTotal(), p.getResult());
        return pageBean;
    }

    @Override
    public void delete(List<Integer> ids) {
        empMapper.delete(ids);
    }

    @Override
    public void insert(Emp emp) {
        emp.setCreateTime(LocalDateTime.now());
        emp.setUpdateTime(LocalDateTime.now());
        empMapper.insert(emp);
    }

    @Override
    public Emp getEmpbyId(Integer id) {
        return empMapper.getEmpbyId(id);
    }

    @Override
    public void updateEmp(Emp emp) {
        emp.setCreateTime(LocalDateTime.now());
        emp.setUpdateTime(LocalDateTime.now());
        empMapper.updateEmp(emp);
    }

    @Override
    public Emp login(Emp emp) {
        return empMapper.getByUsernameAndPassword(emp);
    }

    //    根据部门id来删除部门下的员工
    @Override
    public void deleteByDeptId(Integer deptId) {
        empMapper.deleteByDeptId(deptId);
    }

}
