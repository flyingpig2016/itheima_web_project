package com.itheima.springbootmybatiscrud.mapper;

import com.itheima.springbootmybatiscrud.pojo.Emp;
import org.apache.ibatis.annotations.Mapper;

import java.time.LocalDate;
import java.util.List;

@Mapper
public interface EmpMapper {

    // 删除 #为预编译的预计，$符号为拼接语句，存在sql注入问题
    // @Delete("delete from emp where id= ${id}")
//    @Delete("delete from emp where id= #{id}")
//    public int delete(Integer id);

    // 新增员工对象
//    @Options(keyProperty = "id", useGeneratedKeys = true) // 会自动将生成的主键值，赋值给emp对象的id属性
//    @Insert("insert into emp (username, name, gender, image, job, entrydate, dept_id, create_time, update_time) VALUES (#{username},#{name},#{gender}, #{image}, #{job}, #{entrydate}, #{deptId}, #{createTime},#{updateTime})")
//    public void insert(Emp emp);

    // 更新套餐对象
//    @Update("update emp set username=#{username}, name=#{name},gender=#{gender}, image=#{image}, job=#{job}, entrydate=#{entrydate},dept_id=#{deptId},update_time=#{updateTime} where id=#{id};")
//    public void update(Emp emp);

    // 查询id=15
    // @Select("select * from emp where id = #{id}")
    // public Emp getById(Integer id);
    // 问题：如果实体类和数据库里面的字段名不一致该怎么办？？？？
    // 方案1给字段起别名，让别名与实体类属性一直
    // @Select("select id, username, password, name, gender, image, job, entrydate, dept_id deptId, create_time
    // createTime, update_time updateTime from emp where id = #{id}")
    // public Emp getById(Integer id);
    // 方案2 手动映射 一般不怎么用
    // @Results({
    // @Result(column = "dept_id",property = "deptId"),
    // @Result(column = "create_time",property = "createTime"),
    // @Result(column = "update_time",property = "updateTime")
    // })
    // @Select("select * from emp where id = #{id}")
    // public Emp getById(Integer id);
    // 方案3 方案三：开启mybatis的驼峰命名自动映射开关
//    @Select("select * from emp where id = #{id}")
//    public Emp getById(Integer id);

    // 条件查询 方法一： springboot2.0版本
    // @Select("select * from emp where name like '${name}%' and gender = #{gender} and entrydate between #{begin} and
    // #{end} order by update_time desc ")
    // @Select("select * from emp where name like concat('%',#{name},'%') and gender = #{gender} and entrydate between
    // #{begin} and #{end} order by update_time desc ")
    // public List<Emp> list(String name, Short gender, LocalDate begin, LocalDate end);

    // springboot 1.0版本 单独使用mybatis
    // @Select("select * from emp where name like concat('%',#{name},'%') and gender = #{gender} and entrydate between
    // #{begin} and #{end} order by update_time desc ")
    // public List<Emp> list(@Param("name")String name, @Param("gender")Short gender, @Param("begin")LocalDate begin,
    // @Param("end")LocalDate end);

    // 条件查询 方法二： 使用XML文件定义sql语句
//    public List<Emp> list(String name, Short gender, LocalDate begin, LocalDate end);
    public List<Emp> list(String name, Short gender, LocalDate begin, LocalDate end);

    // 动态更新： 更新套餐对象
//    public void update2(Emp emp);

    // 批量删除员工
//    public void deleteByIds(List<Integer> ids);
}
