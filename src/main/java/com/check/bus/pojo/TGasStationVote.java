package com.check.bus.pojo;

import lombok.Data;

import java.util.Date;

/**
 * 加油站投票表
 *
 * @author zzc
 */
@Data
public class TGasStationVote {

  private long id;
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
