package com.check.bus.pojo.dto;


import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;

/**
 * 加油站投票统计查询类
 *
 * @author zzc
 */
@Data
@Api("加油站投票统计查询类")
public class GasStationVoteCountDto {

    @ApiModelProperty("区code")
    private String districtCode = "";
    @ApiModelProperty("加油站名称")
    private String name = "";
    @ApiModelProperty("加油站地址")
    private String address = "";
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @ApiModelProperty("开始时间")
    private Date startTime = new Date(System.currentTimeMillis() - (30 * 24 * 60 * 60 * 1000L));
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @ApiModelProperty("结束时间")
    private Date endTime = new Date();
    @ApiModelProperty("页码")
    private Integer pageNum = 1;
    @ApiModelProperty("页大小")
    private Integer pageSize = 10;

}
