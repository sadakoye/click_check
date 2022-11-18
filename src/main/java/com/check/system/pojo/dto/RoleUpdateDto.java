package com.check.system.pojo.dto;


import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * @author zzc
 */
@Data
@Api("角色修改类")
public class RoleUpdateDto {

    @NotNull
    @ApiModelProperty("ID")
    private Long id;
    @ApiModelProperty("角色名称")
    private String name;
    @ApiModelProperty("角色级别")
    private Long level;
    @ApiModelProperty("描述")
    private String description;
    @ApiModelProperty("CODE")
    private String code;
    @ApiModelProperty("casId")
    private String casId;

}
