package com.itheima.springbootmybatiscrud;

import com.itheima.springbootmybatiscrud.mapper.EmpMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;

@SpringBootTest
class SpringbootMybatisCrudApplicationTests {

    @Autowired
    public EmpMapper empMapper;

    @Test
    public void testList() {

        System.out.println(empMapper.list("张", (short) 1, LocalDate.of(2000, 1, 1), LocalDate.of(2020, 1, 1)));
//        List<Emp> empList = empMapper.list("张", (short) 1, LocalDate.of(2000, 1, 1), LocalDate.of(2020, 1, 1));
//        System.out.println(empList);
    }
}
