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
@Api("角色输出类")
public class RoleVo extends BaseVo {

    @ApiModelProperty("ID")
    private Long id;
    @ApiModelProperty("角色名称")
    private String name;
    @ApiModelProperty("角色级别")
    private Long level;
    @ApiModelProperty("描述")
    private String description;
    @ApiModelProperty("CODE")
    private String code;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @ApiModelProperty("创建时间")
    private Date createTime;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @ApiModelProperty("修改时间")
    private Date updateTime;
    @ApiModelProperty("casId")
    private String casId;

}
