package com.itheima.mapper;

import com.itheima.pojo.Dept;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface DeptMapper {

    List<Dept> list();

    //    void delete(Integer id);
    @Delete("delete from dept where id = #{id}")
    void delete(Integer id);
}
