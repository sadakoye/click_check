package com.check.system.pojo.dto;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * @author zzc
 */
@Data
@Api("用户新增类")
public class UserUpdateDto {

    @NotNull
    @ApiModelProperty("ID")
    private Long id;
    @ApiModelProperty("部门code")
    private Long deptCode;
    @ApiModelProperty("用户名")
    private String username;
    @ApiModelProperty("昵称")
    private String nickName;
    @ApiModelProperty("性别")
    private String gender;
    @ApiModelProperty("手机号码")
    private String phone;
    @ApiModelProperty("邮箱")
    private String email;
    @ApiModelProperty("头像地址")
    private String avatarName;
    @ApiModelProperty("头像真实路径")
    private String avatarPath;
    @ApiModelProperty("状态：1启用、0禁用")
    private Long enabled;
    @ApiModelProperty("code")
    private String code;
    @ApiModelProperty("casId")
    private String casId;
    @ApiModelProperty("身份证号码")
    private String idCard;

}
