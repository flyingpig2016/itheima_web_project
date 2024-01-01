package com.itheima.aop;

import com.alibaba.fastjson.JSONObject;
import com.itheima.mapper.OperateLogMapper;
import com.itheima.pojo.OperateLog;
import com.itheima.utils.JwtUtils;
import io.jsonwebtoken.Claims;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.Arrays;

@Aspect
@Slf4j
@Component
public class LogAspect {

    @Autowired
    private OperateLogMapper operateLogMapper;

    @Autowired
    private HttpServletRequest request;

    @Around("@annotation(com.itheima.anno.Log)")
    public Object around(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {

//       操作人ID
        // 获取请求头中的jwt令牌， 并解析
        String token = request.getHeader("token");
        Claims claims = JwtUtils.parseJWT(token);
        Integer operateUser = (Integer) claims.get("id");
//      操作时间
        LocalDateTime operateTime = LocalDateTime.now();
//      操作类名
        String className = proceedingJoinPoint.getClass().getName();
//      操作方法名
        String methodName = proceedingJoinPoint.getSignature().getName();
//      操作方法参数
        Object[] args = proceedingJoinPoint.getArgs();
        String methodParams = Arrays.toString(args);
//      操作方法返回值
        long beginTime = System.currentTimeMillis();
        Object result = proceedingJoinPoint.proceed();
        String returnValue = JSONObject.toJSONString(result);

//        private Long costTime; //操作方法耗时
        long endTime = System.currentTimeMillis();
        long costTime = endTime - beginTime;

        OperateLog operateLog = new OperateLog(null, operateUser, operateTime, className, methodName, methodParams, returnValue, costTime);
        operateLogMapper.insert(operateLog);
        log.info("AOP操作日志已记录成功:{}", operateLog);

        return result;
    }
}
