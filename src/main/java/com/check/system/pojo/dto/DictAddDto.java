package com.check.system.pojo.dto;


import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;

/**
 * @author zzc
 */
@Data
@Api("字典新增类")
public class DictAddDto {

    @NotBlank
    @ApiModelProperty(value = "字典键", required = true)
    private String dictKey;
    @NotBlank
    @ApiModelProperty(value = "字典值", required = true)
    private String dictValue;
    @NotBlank
    @ApiModelProperty(value = "字典所属组", required = true)
    private String dictGroup;
}
