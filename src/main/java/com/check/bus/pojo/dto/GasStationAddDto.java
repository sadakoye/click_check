package com.check.bus.pojo.dto;


import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;

/**
 * 加油站新增类
 *
 * @author zzc
 */
@Data
@Api("加油站新增类")
public class GasStationAddDto {

    @NotBlank
    @ApiModelProperty(value = "加油站code", required = true)
    private String code;
    @NotBlank
    @ApiModelProperty(value = "区code", required = true)
    private String districtCode;
    @NotBlank
    @ApiModelProperty(value = "区name", required = true)
    private String districtName;
    @NotBlank
    @ApiModelProperty(value = "经营性质", required = true)
    private String managementNature;
    @NotBlank
    @ApiModelProperty(value = "经营状态", required = true)
    private String managementState;
    @NotBlank
    @ApiModelProperty(value = "加油站品牌", required = true)
    private String brand;
    @NotBlank
    @ApiModelProperty(value = "加油站名称", required = true)
    private String name;
    @NotBlank
    @ApiModelProperty(value = "加油站地址", required = true)
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
