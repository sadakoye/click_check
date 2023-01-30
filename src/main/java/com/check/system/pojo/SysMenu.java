package com.check.system.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

import java.util.Date;

/**
 * @author zzc
 */
@Data
public class SysMenu {
    @TableId(type = IdType.AUTO)
    private Long id;
    private Long pCode;
    private Long subCount;
    private Long type;
    private String title;
    private String name;
    private String component;
    private Long menuSort;
    private String icon;
    private String path;
    private String iFrame;
    private String cache;
    private String hidden;
    private String permission;
    private String createBy;
    private String updateBy;
    private Date createTime;
    private Date updateTime;
    private String code;
    private String casId;
    private String isDelete;

}
