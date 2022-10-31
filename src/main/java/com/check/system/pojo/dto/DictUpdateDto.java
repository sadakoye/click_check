package com.check.system.pojo.dto;


import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * @author zzc
 */
@Data
@Api("字典修改类")
public class DictUpdateDto {

  @NotNull
  private Long id;
  @ApiModelProperty("字典键")
  private String dictKey;
  @ApiModelProperty("字典值")
  private String dictValue;
  @ApiModelProperty("字典所属组")
  private String dictGroup;
}
