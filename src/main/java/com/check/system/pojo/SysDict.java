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
@TableName(value = "T_SYS_DICT")
public class SysDict {
    @TableId(type = IdType.AUTO)
    private Long id;
    private String dictKey;
    private String dictValue;
    private String dictGroup;
    private Date createTime;
    private Date updateTime;
    private String isDelete;

}
