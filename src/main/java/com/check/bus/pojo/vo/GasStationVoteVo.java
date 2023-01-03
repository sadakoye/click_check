package com.check.bus.pojo.vo;

import com.check.common.pojo.vo.BaseVo;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Date;

/**
 * 加油站投票输出类
 *
 * @author zzc
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Api("加油站投票输出类")
public class GasStationVoteVo extends BaseVo {

    @ApiModelProperty("ID")
    private Long id;
    @ApiModelProperty("区code")
    private String districtCode;
    @ApiModelProperty("区name")
    private String districtName;
    @ApiModelProperty("投票人姓名")
    private String voterName;
    @ApiModelProperty("投票人手机号码")
    private String voterPhone;
    @ApiModelProperty("投票人身份证号码")
    private String voterIdCard;
    @ApiModelProperty("投票ip")
    private String voterIp;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", locale = "zh", timezone = "GMT+8")
    @ApiModelProperty("投票时间")
    private Date voteTime;
    @ApiModelProperty("加油站名称")
    private String gasStationName;
    @ApiModelProperty("加油站code")
    private String gasStationCode;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", locale = "zh", timezone = "GMT+8")
    @ApiModelProperty("创建时间")
    private Date createTime;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", locale = "zh", timezone = "GMT+8")
    @ApiModelProperty("修改时间")
    private Date updateTime;

}
