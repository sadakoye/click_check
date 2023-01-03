package com.check.common.pojo.bean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

/**
 * 统一返回对象
 *
 * @author zzc
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Result<T> implements Serializable  {

    private static final long serialVersionUID = 4921061998653835814L;

    private static final int ERROR_CODE = 500;

    private static final int SUCCESS_CODE = 200;

    private static final String SUCCESS = "成功";

    private static final String ERROR = "失败";

    private Integer code;

    private String msg;

    private T data;

    private String time;

    public static <T> Result<T> success(String msg){
        return success(msg, null);
    }

    public static <T> Result<T> success(T data){
        return success(SUCCESS, data);
    }

    public static <T> Result<T> success(){
        return success(SUCCESS, null);
    }

    public static <T> Result<T> success(String msg,T data){
        return new Result<>(SUCCESS_CODE, msg, data, new Date().toString());
    }

    public static <T> Result<T> error(String msg){
        return error(ERROR_CODE, msg, null);
    }

    public static <T> Result<T> error(Integer code, String msg){
        return error(code, msg, null);
    }

    public static <T> Result<T> error(Integer code, String msg, T data){
        HttpServletResponse request = getRequest();
        if (request != null) {
            request.setStatus(code);
        }
        return new Result<>(code, msg, data, new Date().toString());
    }

    private static HttpServletResponse getRequest() {
        ServletRequestAttributes servletRequestAttributes =
                (ServletRequestAttributes)RequestContextHolder.getRequestAttributes();
        if (servletRequestAttributes != null){
            return servletRequestAttributes.getResponse();
        }
        return null;
    }

}