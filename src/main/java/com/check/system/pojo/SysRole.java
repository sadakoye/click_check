package com.check.system.pojo;


import lombok.Data;

import java.util.Date;

/**
 * @author zzc
 */
@Data
public class SysRole {

  private Long id;
  private String name;
  private Long level;
  private String description;
  private String dataScope;
  private String createBy;
  private String updateBy;
  private Date createTime;
  private Date updateTime;
  private String code;
  private String casId;
  private String isDelete;

}
