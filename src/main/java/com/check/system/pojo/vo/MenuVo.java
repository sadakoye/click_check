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
@Api("菜单输出类")
public class MenuVo extends BaseVo {

    @ApiModelProperty("ID")
    private Long id;
    @ApiModelProperty("上级菜单ID")
    private Long pid;
    @ApiModelProperty("子菜单数目")
    private Long subCount;
    @ApiModelProperty("菜单类型")
    private Long type;
    @ApiModelProperty("菜单标题")
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
    @ApiModelProperty("缓存")
    private String cache;
    @ApiModelProperty("隐藏")
    private String hidden;
    @ApiModelProperty("权限")
    private String permission;
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
