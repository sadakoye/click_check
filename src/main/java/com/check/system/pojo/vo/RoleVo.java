package com.check.system.pojo.vo;


import lombok.Data;

import java.util.Date;

/**
 * @author zzc
 */
@Data
public class RoleVo {

  private Long roleId;
  private String name;
  private Long level;
  private String description;
  private String code;

}
