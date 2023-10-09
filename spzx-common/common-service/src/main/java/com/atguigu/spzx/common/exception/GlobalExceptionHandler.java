package com.atguigu.spzx.common.exception;
import com.atguigu.spzx.model.vo.common.Result;
import com.atguigu.spzx.model.vo.common.ResultCodeEnum;
import org.apache.poi.ss.formula.functions.T;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * 统一异常处理类
 */
@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(Exception.class)
    public Result<T> handler(Exception e){
        e.printStackTrace();
        return Result.error(ResultCodeEnum.SYSTEM_ERROR);
    }

    @ExceptionHandler(NullPointerException.class)
    public Result<T> handler(NullPointerException e){
        e.printStackTrace();
        return Result.error(ResultCodeEnum.NULL_ERROR);
    }

    @ExceptionHandler(GuiguException.class)
    public Result<T> handler(GuiguException e){
        e.printStackTrace();
        return Result.error(e.getResultCodeEnum());
    }
}