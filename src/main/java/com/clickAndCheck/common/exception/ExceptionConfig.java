package com.clickAndCheck.common.exception;


import com.clickAndCheck.common.pojo.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.io.PrintWriter;
import java.io.StringWriter;

/**
 * 异常捕捉
 *
 * @author zzc
 */
@Slf4j
@RestControllerAdvice
public class ExceptionConfig {

    private final static String LINE_SEPARATOR = System.getProperty("line.separator");

    @ExceptionHandler(Exception.class)
    public String handleException(Exception e) {
        StringWriter sw = new StringWriter();
        e.printStackTrace(new PrintWriter(sw, true));
        log.error("系统异常：" + LINE_SEPARATOR + sw);
        return Result.error("系统异常");
    }
}