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
@Api("角色修改类")
public class RoleUpdateDto {

    @NotNull
    @ApiModelProperty(value = "ID", required = true)
    private Long id;
    @NotBlank
    @ApiModelProperty(value = "角色名称", required = true)
    private String name;
    @NotNull
    @ApiModelProperty(value = "角色级别", required = true)
    private Long level;
    @ApiModelProperty("描述")
    private String description;
    @NotBlank
    @ApiModelProperty(value = "CODE", required = true)
    private String code;
    @ApiModelProperty("casId")
    private String casId;

}
