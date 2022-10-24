package com.clickAndCheck.common.pojo;

import com.alibaba.fastjson.JSONObject;
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

    public static String error(String msg){
        return JSONObject.toJSONString(Result.builder().code(ERROR_CODE).msg(msg).build());
    }

    public static String success(String msg,Object data){
        return JSONObject.toJSONString(Result.builder().code(SUCCESS_CODE).msg(msg).data(data).build());
    }

}