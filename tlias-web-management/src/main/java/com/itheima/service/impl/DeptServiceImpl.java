package com.itheima.service.impl;

import com.itheima.aop.Mylog;
import com.itheima.mapper.DeptMapper;
import com.itheima.mapper.EmpMapper;
import com.itheima.pojo.Dept;
import com.itheima.pojo.DeptLog;
import com.itheima.service.DeptLogService;
import com.itheima.service.DeptService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class DeptServiceImpl implements DeptService {

    @Autowired
    private DeptMapper deptMapper;

    @Autowired
    private EmpMapper empMapper;

    @Autowired
    private DeptLogService deptLogService;

    @Mylog
    @Override
    public List<Dept> list() {
        return deptMapper.list();
    }

    //    删除部门 并且删除该部门下的员工,并且可以支持回滚

    @Mylog
//    @Transactional(propagation = Propagation.REQUIRED) //默认为REQUIRED
//    @Transactional(rollbackFor = Exception.class) // rollbackFor出现任何异常都回滚事务
    @Transactional(propagation = Propagation.REQUIRES_NEW) //开启新事务 才能记录日志
    @Override
    public void delete(Integer id) throws Exception {
        // ctrl + alt + t 或者 option + command + T
        try {
            deptMapper.delete(id);
//            int i = 1 / 0; //可以回滚
//        if (true) {
//            throw new Exception("`出错了...."); //不能回滚
//        }
            empMapper.deleteByDeptId(id); //根据部门id删除下面的员工数据
        } finally { //块中放无论是否发生异常都会执行的代码
            //记录操作日志
            DeptLog deptLog = new DeptLog();
            deptLog.setCreateTime(LocalDateTime.now());
            deptLog.setDescription("执行了解散部门操作，此次解散的是" + id + "号部门");
            deptLogService.insert(deptLog);
        }
    }

    @Override
    public void addDept(Dept dept) {
        dept.setCreateTime(LocalDateTime.now());
        dept.setUpdateTime(LocalDateTime.now());
        deptMapper.addDept(dept);
    }

    @Override
    public Dept getDept(Integer id) {
        Dept dept = deptMapper.getDept(id);
        return dept;
    }

    @Override
    public void updateDept(Dept dept) {
        deptMapper.updateDept(dept);
    }


}
