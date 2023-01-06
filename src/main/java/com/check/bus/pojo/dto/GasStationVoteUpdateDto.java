package com.check.bus.pojo.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;
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
    @ApiModelProperty(value = "ID", required = true)
    private Long id;
    @NotBlank
    @ApiModelProperty(value = "区code", required = true)
    private String districtCode;
    @NotBlank
    @ApiModelProperty(value = "区name", required = true)
    private String districtName;
    @NotBlank
    @ApiModelProperty(value = "投票人姓名", required = true)
    private String voterName;
    @NotBlank
    @ApiModelProperty(value = "投票人手机号码", required = true)
    private String voterPhone;
    @NotBlank
    @ApiModelProperty(value = "投票人身份证号码", required = true)
    private String voterIdCard;
    @ApiModelProperty("投票ip")
    private String voterIp;
    @NotNull
    @JsonFormat( pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @ApiModelProperty(value = "投票时间", required = true)
    private Date voteTime;
    @NotBlank
    @ApiModelProperty(value = "加油站名称", required = true)
    private String gasStationName;
    @NotBlank
    @ApiModelProperty(value = "加油站code", required = true)
    private String gasStationCode;

}
