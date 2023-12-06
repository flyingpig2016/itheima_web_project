package com.itheima.service;

import com.itheima.pojo.PageBean;

import java.time.LocalDate;

public interface EmpService {

    PageBean page(Integer page, Integer pageSize, String name, Short gender, LocalDate begin, LocalDate end);
}
