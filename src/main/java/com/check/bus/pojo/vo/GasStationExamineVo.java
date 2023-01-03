package com.check.bus.pojo.vo;


import com.check.common.pojo.vo.BaseVo;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Date;

/**
 * 加油站检查输出类
 *
 * @author zzc
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Api("加油站检查输出类")
public class GasStationExamineVo extends BaseVo {

    @ApiModelProperty("ID")
    private Long id;
    @ApiModelProperty("区code")
    private String districtCode;
    @ApiModelProperty("区name")
    private String districtName;
    @ApiModelProperty("加油站名称")
    private String gasStationName;
    @ApiModelProperty("加油站code")
    private String gasStationCode;
    @ApiModelProperty("检查状况")
    private String examineState;
    @ApiModelProperty("检查结果")
    private String examineResult;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", locale = "zh", timezone = "GMT+8")
    @ApiModelProperty("检查时间")
    private Date examineTime;
    @ApiModelProperty("备注")
    private String remark;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", locale = "zh", timezone = "GMT+8")
    @ApiModelProperty("创建时间")
    private Date createTime;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", locale = "zh", timezone = "GMT+8")
    @ApiModelProperty("更新时间")
    private Date updateTime;
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
