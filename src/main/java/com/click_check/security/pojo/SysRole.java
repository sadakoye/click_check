package com.click_check.security.pojo;


import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.util.Date;

/**
 * @author zzc
 */
@Data
@TableName("SYS_ROLE")
public class SysRole {

  private Long roleId;
  private String name;
  private Long level;
  private String description;
  private String dataScope;
  private String createBy;
  private String updateBy;
  private Date createTime;
  private Date updateTime;
  private String code;

}
