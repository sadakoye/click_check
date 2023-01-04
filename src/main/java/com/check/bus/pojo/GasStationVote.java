package com.check.bus.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * 加油站投票表
 *
 * @author zzc
 */
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
@TableName(value = "T_GAS_STATION_VOTE")
public class GasStationVote {
    @TableId(type = IdType.AUTO)
    private Long id;
    private String districtCode;
    private String districtName;
    private String voterName;
    private String voterPhone;
    private String voterIdCard;
    private String voterIp;
    private Date voteTime;
    private String gasStationName;
    private String gasStationCode;
    private Date createTime;
    private Date updateTime;
    private String isDelete;

}
