package com.check.bus.pojo.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.util.Date;

/**
 * 加油站投票修改类
 *
 * @author zzc
 */
@Data
@Api("加油站投票修改类")
public class GasStationVoteUpdateDto {

    @NotNull
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
    @JsonFormat( pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @ApiModelProperty("投票时间")
    private Date voteTime;
    @ApiModelProperty("加油站名称")
    private String gasStationName;
    @ApiModelProperty("加油站code")
    private String gasStationCode;

}
