package com.check.common.config;


import com.check.common.exception.CommonException;
import com.check.common.pojo.bean.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.converter.HttpMessageNotReadableException;
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
     * 捕捉自定义异常
     *
     * @param e 异常
     * @return Result
     * @author zzc
     */
    @ExceptionHandler(HttpMessageNotReadableException.class)
    public Result<Object> handleException(HttpMessageNotReadableException e) {
        log.error("系统异常：未接收到请求时传入的参数" + LINE_SEPARATOR + e.getMessage());
        return Result.error("未接收到请求时传入的参数");
    }

    /**
     * 参数校验，参数为实体类
     *
     * @param e 异常
     * @return Result
     * @author zzc
     */
    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    public Result<Object> handleValidException(MethodArgumentNotValidException e) {
        // 从异常对象中拿到ObjectError对象
        ObjectError objectError = e.getBindingResult().getAllErrors().get(0);
        // 然后提取错误提示信息进行返回
        StringBuilder stringBuilder = new StringBuilder();
        String defaultMessage = "";
        if (objectError != null) {
            if (objectError.getArguments() != null) {
                for (Object argument : objectError.getArguments()) {
                    DefaultMessageSourceResolvable defaultMessageSourceResolvable = (DefaultMessageSourceResolvable) argument;
                    stringBuilder.append(defaultMessageSourceResolvable.getDefaultMessage());
                    stringBuilder.append(",");
                }
            }
            defaultMessage = objectError.getDefaultMessage();
        }
        if (stringBuilder.length() > 0) {
            stringBuilder.deleteCharAt(stringBuilder.length() - 1);
        }
        log.error("参数校验异常:" + stringBuilder + defaultMessage);
        return Result.error(402, stringBuilder + defaultMessage);
    }

    /**
     * 参数校验，参数为单个参数或多个参数
     *
     * @param e 异常
     * @return Result
     * @author zzc
     */
    @ExceptionHandler(value = ConstraintViolationException.class)
    public Result<Object> handleConstraintViolationException(ConstraintViolationException e) {
        String s = e.getConstraintViolations()
                .stream()
                .map(ConstraintViolation::getMessage)
                .collect(Collectors.toList()).get(0);
        // 从异常对象中拿到ObjectError对象
        log.error("多参数校验异常：" + s);
        return Result.error(402, s);
    }

    /**
     * 捕捉自定义异常
     *
     * @param e 异常
     * @return Result
     * @author zzc
     */
    @ExceptionHandler(CommonException.class)
    public Result<Object> handleException(CommonException e) {
        log.error("系统异常：" + e.getMsg());
        return Result.error(e.getMsg());
    }

    /**
     * 捕捉剩余异常
     *
     * @param e 异常
     * @return Result
     * @author zzc
     */
    @ExceptionHandler(Exception.class)
    public Result<Object> handleException(Exception e) {
        StringWriter sw = new StringWriter();
        e.printStackTrace(new PrintWriter(sw, true));
        log.error("系统异常：" + LINE_SEPARATOR + sw);
        return Result.error("系统异常");
    }
}