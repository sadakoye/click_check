package com.check.bus.pojo;


import lombok.Data;

import java.util.Date;

/**
 * 加油站检查表
 *
 * @author zzc
 */
@Data
public class TGasStationExamine {

  private long id;
  private String districtCode;
  private String districtName;
  private String gasStationName;
  private String gasStationCode;
  private String examineState;
  private String examineResult;
  private Date examineTime;
  private String remark;
  private Date createTime;
  private Date updateTime;
  private String isDelete;
  private String examineDispose;
  private String gasApparatusCode;
  private String gasGunCode;
  private String examineName;
  private String engineerName;

}
