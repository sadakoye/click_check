package com.check.common.config;


import com.check.common.pojo.bean.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.stream.Collectors;

/**
 * 异常捕捉
 *
 * @author zzc
 */
@Slf4j
@RestControllerAdvice
public class ExceptionConfig {

    private final static String LINE_SEPARATOR = System.getProperty("line.separator");

    /**
     * 参数校验，参数为实体类
     *
     * @param e 异常
     * @return Result
     * @author zzc
     */
    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    public Result handleValidException(MethodArgumentNotValidException e) {
        // 从异常对象中拿到ObjectError对象
        ObjectError objectError = e.getBindingResult().getAllErrors().get(0);
        // 然后提取错误提示信息进行返回
        String defaultMessage = objectError.getDefaultMessage();
        return Result.error(defaultMessage);
    }

    /**
     * 参数校验，参数为单个参数或多个参数
     *
     * @param e 异常
     * @return Result
     * @author zzc
     */
    @ExceptionHandler(value = ConstraintViolationException.class)
    public Result handleConstraintViolationException(ConstraintViolationException e) {
        String s = e.getConstraintViolations()
                .stream()
                .map(ConstraintViolation::getMessage)
                .collect(Collectors.toList()).get(0);
        // 从异常对象中拿到ObjectError对象
        return Result.error(s);
    }

    /**
     * 捕捉剩余异常
     *
     * @param e 异常
     * @return Result
     * @author zzc
     */
    @ExceptionHandler(Exception.class)
    public Result handleException(Exception e) {
        StringWriter sw = new StringWriter();
        e.printStackTrace(new PrintWriter(sw, true));
        log.error("系统异常：" + LINE_SEPARATOR + sw);
        return Result.error("系统异常");
    }
}