package com.check.system.pojo;


import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.util.Date;

/**
 * @author zzc
 */
@Data
@TableName(value = "T_SYS_ROLE")
public class SysRole {
    @TableId(type = IdType.AUTO)
    private Long id;
    private String name;
    private Long level;
    private String description;
    private String dataScope;
    private String createBy;
    private String updateBy;
    private Date createTime;
    private Date updateTime;
    private String code;
    private String casId;
    private String isDelete;

}
