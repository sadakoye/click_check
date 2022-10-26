package com.check.common.pojo.bean;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * 统一返回对象
 *
 * @author zzc
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Result implements Serializable {

    private static final long serialVersionUID = 4921061998653835814L;

    private static final String ERROR_CODE = "500";

    private static final String SUCCESS_CODE = "200";

    private String code;

    private String msg;

    private Object data;

    public static Result error(String msg){
        return Result.builder().code(ERROR_CODE).msg(msg).build();
    }

    public static Result success(String msg,Object data){
        return Result.builder().code(SUCCESS_CODE).msg(msg).data(data).build();
    }

    public static Result success(String msg){
        return Result.builder().code(SUCCESS_CODE).msg(msg).build();
    }

    public static Result success(Object data){
        return Result.builder().code(SUCCESS_CODE).msg("成功").data(data).build();
    }

    public static Result success(){
        return Result.builder().code(SUCCESS_CODE).msg("成功").build();
    }

}