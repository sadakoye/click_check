package com.check.bus.pojo.dto;


import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import java.util.Date;

/**
 * 加油站检查新增类
 *
 * @author zzc
 */
@Data
@Api("加油站检查新增类")
public class GasStationExamineAddDto {

    @NotBlank
    @ApiModelProperty(value = "区code", required = true)
    private String districtCode;
    @NotBlank
    @ApiModelProperty(value = "区name", required = true)
    private String districtName;
    @NotBlank
    @ApiModelProperty(value = "加油站名称", required = true)
    private String gasStationName;
    @NotBlank
    @ApiModelProperty(value = "加油站code", required = true)
    private String gasStationCode;
    @ApiModelProperty("检查状况")
    private String examineState;
    @ApiModelProperty("检查结果")
    private String examineResult;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @ApiModelProperty("检查时间")
    private Date examineTime;
    @ApiModelProperty("备注")
    private String remark;
    @ApiModelProperty("检查后处理情况")
    private String examineDispose;
    @ApiModelProperty("加油机的出厂编号")
    private String gasApparatusCode;
    @ApiModelProperty("加油枪自编号")
    private String gasGunCode;
    @ApiModelProperty("检查人员名称")
    private String examineName;
    @ApiModelProperty("工程师名称")
    private String engineerName;

}
