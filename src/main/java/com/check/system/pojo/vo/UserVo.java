package com.check.system.pojo.vo;

import com.check.common.pojo.vo.BaseVo;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Date;

/**
 * @author zzc
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Api("用户输出类")
public class UserVo extends BaseVo {

  @ApiModelProperty("ID")
  private Long userId;
  @ApiModelProperty("部门code")
  private Long deptCode;
  @ApiModelProperty("用户名")
  private String username;
  @ApiModelProperty("昵称")
  private String nickName;
  @ApiModelProperty("性别")
  private String gender;
  @ApiModelProperty("手机号码")
  private String phone;
  @ApiModelProperty("邮箱")
  private String email;
  @ApiModelProperty("头像地址")
  private String avatarName;
  @ApiModelProperty("头像真实路径")
  private String avatarPath;
  @ApiModelProperty("是否为admin账号")
  private String isAdmin;
  @ApiModelProperty("状态：1启用、0禁用")
  private Long enabled;
  @ApiModelProperty("创建者")
  private String createBy;
  @ApiModelProperty("更新者")
  private String updateBy;
  @ApiModelProperty("code")
  private String code;
  @ApiModelProperty("casId")
  private String casId;
  @JsonFormat( pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
  @ApiModelProperty("创建时间")
  private Date createTime;
  @JsonFormat( pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
  @ApiModelProperty("修改时间")
  private Date updateTime;

}
