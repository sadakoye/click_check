package com.check.bus.pojo.dto;

import com.check.common.pojo.dto.BaseDto;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Date;

/**
 * 加油站投票查询类
 *
 * @author zzc
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Api("加油站投票查询类")
public class GasStationVoteDto extends BaseDto {

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
    @ApiModelProperty("加油站名称")
    private String gasStationName;
    @ApiModelProperty("加油站code")
    private String gasStationCode;
    @ApiModelProperty(hidden = true)
    private Date voteTime;
    @ApiModelProperty(hidden = true)
    private Date createTime;
    @ApiModelProperty(hidden = true)
    private Date updateTime;

}
