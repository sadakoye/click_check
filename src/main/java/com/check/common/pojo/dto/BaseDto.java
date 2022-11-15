package com.check.common.pojo.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;

/**
 * @author zzc
 */
@Data
public class BaseDto {

    @ApiModelProperty("页码")
    private Integer pageNum = 1;
    @ApiModelProperty("页大小")
    private Integer pageSize = 10;
    @ApiModelProperty("倒序排序字段")
    private String desc;
    @ApiModelProperty("顺序排序字段")
    private String asc;
    @ApiModelProperty("如果keyword不为空，所有时间外的查询条件值替换为keyword，并为or连接")
    private String keyword;
    @ApiModelProperty("开始时间")
    @JsonFormat( pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date baseStartTime;
    @ApiModelProperty("结束时间")
    @JsonFormat( pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date baseEndTime;
    @ApiModelProperty("时间字段名")
    private String baseTime;
}
