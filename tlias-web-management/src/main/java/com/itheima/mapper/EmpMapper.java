package com.itheima.mapper;

import com.itheima.pojo.Emp;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface EmpMapper {

//    @Select("select count(*) from emp")
//    public Long count();

//    List<Emp> page(Integer start, Integer pageSize);

    public List<Emp> list();
}
