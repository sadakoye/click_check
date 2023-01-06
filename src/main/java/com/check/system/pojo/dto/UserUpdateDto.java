package com.check.system.pojo.dto;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * @author zzc
 */
@Data
@Api("用户新增类")
public class UserUpdateDto {

    @NotNull
    @ApiModelProperty(value = "ID", required = true)
    private Long id;
    @NotNull
    @ApiModelProperty(value = "部门code", required = true)
    private Long deptCode;
    @NotBlank
    @ApiModelProperty(value = "用户名", required = true)
    private String username;
    @NotBlank
    @ApiModelProperty(value = "昵称", required = true)
    private String nickName;
    @NotBlank
    @ApiModelProperty(value = "性别", required = true)
    private String gender;
    @NotBlank
    @ApiModelProperty(value = "手机号码", required = true)
    private String phone;
    @ApiModelProperty("邮箱")
    private String email;
    @ApiModelProperty("头像地址")
    private String avatarName;
    @ApiModelProperty("头像真实路径")
    private String avatarPath;
    @NotBlank
    @ApiModelProperty(value = "状态：1启用、0禁用", required = true)
    private Long enabled;
    @NotBlank
    @ApiModelProperty(value = "code", required = true)
    private String code;
    @ApiModelProperty("casId")
    private String casId;
    @ApiModelProperty("身份证号码")
    private String idCard;

}
