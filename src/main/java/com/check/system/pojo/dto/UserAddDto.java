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
public class UserAddDto {

    @NotNull
    @ApiModelProperty("部门code")
    private Long deptCode;
    @NotBlank
    @ApiModelProperty("用户名")
    private String username;
    @NotBlank
    @ApiModelProperty("昵称")
    private String nickName;
    @NotBlank
    @ApiModelProperty("性别")
    private String gender;
    @NotBlank
    @ApiModelProperty("手机号码")
    private String phone;
    @ApiModelProperty("邮箱")
    private String email;
    @ApiModelProperty("头像地址")
    private String avatarName;
    @ApiModelProperty("头像真实路径")
    private String avatarPath;
    @NotBlank
    @ApiModelProperty("状态：1启用、0禁用")
    private Long enabled;
    @NotBlank
    @ApiModelProperty("code")
    private String code;
    @ApiModelProperty("casId")
    private String casId;
    @ApiModelProperty("身份证号码")
    private String idCard;

}
