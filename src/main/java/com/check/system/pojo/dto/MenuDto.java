package com.check.system.pojo.dto;

import com.check.common.pojo.dto.BaseDto;
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
@Api("菜单查询类")
public class MenuDto extends BaseDto {

    @ApiModelProperty("ID")
    private Long id;
    @ApiModelProperty("上级菜单CODE")
    private Long pCode;
    @ApiModelProperty("菜单类型")
    private Long type;
    @ApiModelProperty("菜单标题")
    private String title;
    @ApiModelProperty("隐藏")
    private String hidden;
    @ApiModelProperty("CODE")
    private String code;
    @ApiModelProperty(hidden = true)
    private Date createTime;
    @ApiModelProperty(hidden = true)
    private Date updateTime;
    @ApiModelProperty("casId")
    private String casId;

}
