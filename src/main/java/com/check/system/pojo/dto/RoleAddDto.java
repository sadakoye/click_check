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
@Api("角色新增类")
public class RoleAddDto {

    @NotBlank
    @ApiModelProperty("角色名称")
    private String name;
    @NotNull
    @ApiModelProperty("角色级别")
    private Long level;
    @ApiModelProperty("描述")
    private String description;
    @NotBlank
    @ApiModelProperty("CODE")
    private String code;
    @ApiModelProperty("casId")
    private String casId;

}
