package com.itheima.mapper;

import com.itheima.pojo.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @Author: flyingpig
 * @Date: 2023-04-10-00:11
 * @Description:
 */
@Mapper //在运行时候，会自动生成接口的实现类对象(代理对象)，并且将该对象交给IOC容器管理
public interface UserMapper {
    //	查询全部用户信息
    @Select("select * from user")
    public List<User> list();
}
