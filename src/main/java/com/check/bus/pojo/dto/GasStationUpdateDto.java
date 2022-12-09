package com.check.bus.pojo.dto;


import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * 加油站修改类
 *
 * @author zzc
 */
@Data
@Api("加油站修改类")
public class GasStationUpdateDto {

    @NotNull
    @ApiModelProperty("ID")
    private long id;
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

}
