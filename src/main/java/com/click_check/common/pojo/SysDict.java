package com.click_check.common.pojo;


import lombok.Data;

import java.util.Date;

/**
 * @author zzc
 */
@Data
public class SysDict {

  private Long id;
  private String dictKey;
  private String dictValue;
  private String dictGroup;
  private Date createTime;
  private Date updateTime;
  private String isDelete;

}
