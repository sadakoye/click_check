package com.clickAndCheck.pojo;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.util.Date;

/**
 * @author zzc
 */
@Data
@TableName("SYS_MENU")
public class SysMenu {

  private Long menuId;
  private Long pid;
  private Long subCount;
  private Long type;
  private String title;
  private String name;
  private String component;
  private Long menuSort;
  private String icon;
  private String path;
  private String iFrame;
  private String cache;
  private String hidden;
  private String permission;
  private String createBy;
  private String updateBy;
  private Date createTime;
  private Date updateTime;

}
