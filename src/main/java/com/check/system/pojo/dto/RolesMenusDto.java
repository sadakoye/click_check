package com.check.system.pojo.dto;

import io.swagger.annotations.Api;
import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * @author zzc
 */
@Data
@Api("菜单角色关联类")
public class RolesMenusDto {

  @NotNull
  private long menuCode;
  @NotNull
  private long roleCode;

}
