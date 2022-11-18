package com.check.system.pojo.dto;

import com.check.common.pojo.dto.BaseDto;
import com.check.common.pojo.vo.BaseVo;
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
@Api("用户查询类")
public class UserDto extends BaseDto {

    @ApiModelProperty("ID")
    private Long id;
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
    @ApiModelProperty("状态：1启用、0禁用")
    private Long enabled;
    @ApiModelProperty("code")
    private String code;
    @ApiModelProperty(hidden = true)
    private Date createTime;
    @ApiModelProperty(hidden = true)
    private Date updateTime;

}
