package com.check.system.pojo.dto;

import io.swagger.annotations.Api;
import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * @author zzc
 */
@Data
@Api("用户角色关联类")
public class UsersRolesDto {

  @NotNull
  private long userCode;
  @NotNull
  private long roleCode;

}
