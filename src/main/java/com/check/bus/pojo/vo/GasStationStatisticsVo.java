package com.check.bus.pojo.vo;


import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 加油站统计输出类
 *
 * @author zzc
 */
@Data
@Api("加油站统计输出类")
public class GasStationStatisticsVo {

    @ApiModelProperty("加油站品牌")
    private String brand;
    @ApiModelProperty("加油站数量")
    private Integer count;

}
