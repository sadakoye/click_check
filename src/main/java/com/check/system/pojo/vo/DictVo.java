package com.check.system.pojo.vo;


import com.check.common.pojo.vo.BaseVo;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Date;

/**
 * @author zzc
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class DictVo extends BaseVo {

  private Long id;
  private String dictKey;
  private String dictValue;
  private String dictGroup;
  private Date createTime;
  private Date updateTime;
  private String isDelete;

}
