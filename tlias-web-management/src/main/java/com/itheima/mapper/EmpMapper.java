package com.itheima.mapper;

import com.itheima.pojo.Emp;
import org.apache.ibatis.annotations.Mapper;

import java.time.LocalDate;
import java.util.List;

@Mapper
public interface EmpMapper {

//    @Select("select count(*) from emp")
//    public Long count();

//    List<Emp> page(Integer start, Integer pageSize);

    public List<Emp> list(String name, Short gender, LocalDate begin, LocalDate end);
}
