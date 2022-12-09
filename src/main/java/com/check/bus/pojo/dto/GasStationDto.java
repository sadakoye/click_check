package com.check.bus.pojo.dto;


import com.check.common.pojo.dto.BaseDto;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Date;

/**
 * 加油站查询类
 *
 * @author zzc
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Api("加油站查询类")
public class GasStationDto extends BaseDto {

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
    @ApiModelProperty(hidden = true)
    private Date createTime;
    @ApiModelProperty(hidden = true)
    private Date updateTime;

}
