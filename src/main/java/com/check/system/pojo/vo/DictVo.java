package com.check.system.pojo.vo;


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
@Api("字典输出类")
public class DictVo extends BaseVo {

  @ApiModelProperty("ID")
  private Long id;
  @ApiModelProperty("字典键")
  private String dictKey;
  @ApiModelProperty("字典值")
  private String dictValue;
  @ApiModelProperty("字典所属组")
  private String dictGroup;
  @JsonFormat( pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
  @ApiModelProperty("创建时间")
  private Date createTime;
  @JsonFormat( pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
  @ApiModelProperty("修改时间")
  private Date updateTime;


}
