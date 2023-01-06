package com.check.system.pojo.dto;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * @author zzc
 */
@Data
@Api("菜单修改类")
public class MenuUpdateDto{

  @NotNull
  @ApiModelProperty(value = "ID", required = true)
  private Long id;
  @NotNull
  @ApiModelProperty(value = "上级菜单ID", required = true)
  private Long pid;
  @ApiModelProperty("子菜单数目")
  private Long subCount;
  @NotNull
  @ApiModelProperty(value = "菜单类型", required = true)
  private Long type;
  @NotBlank
  @ApiModelProperty(value = "菜单标题", required = true)
  private String title;
  @ApiModelProperty("组件名称")
  private String name;
  @ApiModelProperty("组件")
  private String component;
  @ApiModelProperty("排序")
  private Long menuSort;
  @ApiModelProperty("图标")
  private String icon;
  @ApiModelProperty("链接地址")
  private String path;
  @ApiModelProperty("是否外链")
  private String iFrame;
  @ApiModelProperty("隐藏")
  private String hidden;
  @ApiModelProperty("权限")
  private String permission;
  @NotBlank
  @ApiModelProperty(value = "CODE", required = true)
  private String code;
  @ApiModelProperty("casId")
  private String casId;

}
