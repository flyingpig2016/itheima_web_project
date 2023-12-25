package com.itheima.exception;

import com.itheima.pojo.Result;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * 全局异常处理器
 */
@RestControllerAdvice
public class GlobalExceptionHandler {

    // 处理自定义异常
//    @org.springframework.web.bind.annotation.ExceptionHandler(value = MyException.class)
//    public Result handleMyException(MyException e){
//        return new Result(e.getCode(),e.getMsg());
//    }

    // 处理其他异常
    @ExceptionHandler(Exception.class)
    public Result handleException(Exception e) {
        e.printStackTrace();
        return Result.error("对不起，操作失败，请联系管理员");
    }
}
