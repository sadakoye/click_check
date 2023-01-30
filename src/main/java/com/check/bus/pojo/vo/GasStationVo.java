package com.check.bus.pojo.vo;


import com.check.common.pojo.vo.BaseVo;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Date;

/**
 * 加油站输出类
 *
 * @author zzc
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Api("加油站输出类")
public class GasStationVo extends BaseVo {

    @ApiModelProperty("ID")
    private Long id;
    @ApiModelProperty("加油站code")
    private String code;
    @ApiModelProperty("区code")
    private String districtCode;
    @ApiModelProperty("区name")
    private String districtName;
    @ApiModelProperty("经营性质")
    private String managementNature;
    @ApiModelProperty("经营状态")
    private String managementState;
    @ApiModelProperty("加油站品牌")
    private String brand;
    @ApiModelProperty("加油站名称")
    private String name;
    @ApiModelProperty("加油站地址")
    private String address;
    @ApiModelProperty("联系人名称")
    private String contactsName;
    @ApiModelProperty("联系人电话")
    private String contactsPhone;
    @ApiModelProperty("联系人固定电话")
    private String contactsLandlineTelephone;
    @ApiModelProperty("备注")
    private String remark;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", locale = "zh", timezone = "GMT+8")
    @ApiModelProperty("创建时间")
    private Date createTime;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", locale = "zh", timezone = "GMT+8")
    @ApiModelProperty("更新时间")
    private Date updateTime;
    @ApiModelProperty("X")
    private Double x;
    @ApiModelProperty("Y")
    private Double y;

}
