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
    @ApiModelProperty("字典键")
    private String dictKey;
    @NotBlank
    @ApiModelProperty("字典值")
    private String dictValue;
    @NotBlank
    @ApiModelProperty("字典所属组")
    private String dictGroup;
}
