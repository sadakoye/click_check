package com.check.bus.pojo.dto;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;

/**
 * @author zzc
 */
@Data
@Api("地区类")
public class DistrictDto {

    @NotBlank
    @ApiModelProperty(value = "地区code, 传00查全部", required = true)
    private String code;
}
