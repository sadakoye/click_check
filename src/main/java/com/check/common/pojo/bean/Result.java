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

    private String date;

    public static <T> Result<T> error(String msg){
        Result<T> result = new Result<>();
        result.setCode(ERROR_CODE);
        result.setMsg(msg);
        result.setDate(new Date().toString());
        return result;
    }

    public static <T> Result<T> success(String msg,T data){
        Result<T> result = new Result<>();
        result.setData(data);
        result.setCode(SUCCESS_CODE);
        result.setMsg(msg);
        result.setDate(new Date().toString());
        return result;
    }

    public static <T> Result<T> success(String msg){
        Result<T> result = new Result<>();
        result.setCode(SUCCESS_CODE);
        result.setMsg(msg);
        result.setDate(new Date().toString());
        return result;
    }

    public static <T> Result<T> success(T data){
        Result<T> result = new Result<>();
        result.setData(data);
        result.setCode(SUCCESS_CODE);
        result.setMsg(SUCCESS);
        result.setDate(new Date().toString());
        return result;
    }

    public static <T> Result<T> success(){
        Result<T> result = new Result<>();
        result.setCode(SUCCESS_CODE);
        result.setMsg(SUCCESS);
        result.setDate(new Date().toString());
        return result;
    }

    public static <T> Result<T> error(Integer code, String msg){
        Result<T> result = new Result<>();
        result.setCode(code);
        result.setMsg(msg);
        result.setDate(new Date().toString());
        return result;
    }

}