package com.itheima.service;


import com.itheima.pojo.Dept;

import java.util.List;

public interface DeptService {
    public List<Dept> list();

    public void delete(Integer id);

}



