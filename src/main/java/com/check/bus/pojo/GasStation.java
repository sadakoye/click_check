package com.check.bus.pojo;


import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.util.Date;

/**
 * 加油站表
 *
 * @author zzc
 */
@Data
@TableName(value = "T_GAS_STATION")
public class GasStation {

    private long id;
    private String code;
    private String districtCode;
    private String districtName;
    private String managementNature;
    private String managementState;
    private String brand;
    private String name;
    private String address;
    private String contactsName;
    private String contactsPhone;
    private String contactsLandlineTelephone;
    private String remark;
    private Date createTime;
    private Date updateTime;
    private String isDelete;

}
