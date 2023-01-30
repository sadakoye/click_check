package com.check.bus.pojo.vo;


import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Data;

/**
 * 加油站检查统计输出类
 *
 * @author zzc
 */
@Data
@Api("加油站检查统计输出类")
@Builder
public class GasStationExamineStatisticsVo {

    @ApiModelProperty("是否检查,0未检查，1已检查")
    private String examine;
    @ApiModelProperty("加油站数量")
    private Integer count;

}
