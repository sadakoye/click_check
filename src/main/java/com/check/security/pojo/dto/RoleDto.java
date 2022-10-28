package com.check.security.pojo.dto;


import lombok.Data;

import java.util.Date;

/**
 * @author zzc
 */
@Data
public class RoleDto {

  private Long roleId;
  private String name;
  private Long level;
  private String description;
  private String code;

}
