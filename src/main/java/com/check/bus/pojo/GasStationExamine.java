package com.check.bus.pojo;


import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.util.Date;

/**
 * 加油站检查表
 *
 * @author zzc
 */
@Data
@TableName(value = "T_GAS_STATION_EXAMINE")
public class GasStationExamine {
    @TableId(type = IdType.AUTO)
    private Long id;
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
