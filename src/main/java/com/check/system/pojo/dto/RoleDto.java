package com.check.system.pojo.dto;


import com.check.common.pojo.dto.BaseDto;
import com.check.common.pojo.vo.BaseVo;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Date;

/**
 * @author zzc
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Api("角色查询类")
public class RoleDto extends BaseDto {

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
  @ApiModelProperty(hidden = true)
  private Date createTime;
  @ApiModelProperty(hidden = true)
  private Date updateTime;
  @ApiModelProperty("casId")
  private String casId;

}
