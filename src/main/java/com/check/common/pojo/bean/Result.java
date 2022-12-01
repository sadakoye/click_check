package com.check.common.pojo.bean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

/**
 * 统一返回对象
 *
 * @author zzc
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Result<T> implements Serializable {

    private static final long serialVersionUID = 4921061998653835814L;

    private static final int ERROR_CODE = 500;

    private static final int SUCCESS_CODE = 200;

    private static final String SUCCESS = "成功";

    private static final String ERROR = "失败";

    private Integer code;

    private String msg;

    private T data;

    private String time;

    public static <T> Result<T> error(String msg){
        return new Result<>(ERROR_CODE, msg, null, new Date().toString());
    }

    public static <T> Result<T> success(String msg,T data){
        return new Result<>(SUCCESS_CODE, msg, data, new Date().toString());
    }

    public static <T> Result<T> success(String msg){
        return new Result<>(SUCCESS_CODE, msg, null, new Date().toString());
    }

    public static <T> Result<T> success(T data){
        return new Result<>(SUCCESS_CODE, SUCCESS, data, new Date().toString());
    }

    public static <T> Result<T> success(){
        return new Result<>(SUCCESS_CODE, SUCCESS, null, new Date().toString());
    }

    public static <T> Result<T> error(Integer code, String msg){
        return new Result<>(code, msg, null, new Date().toString());
    }

}