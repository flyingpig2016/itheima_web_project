package com.itheima.service;

import com.itheima.pojo.PageBean;

public interface EmpService {

    PageBean page(Integer page, Integer pageSize);
}
