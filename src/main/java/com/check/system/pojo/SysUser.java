package com.check.system.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

import java.util.Date;

/**
 * @author zzc
 */
@Data
public class SysUser {
    @TableId(type = IdType.AUTO)
    private Long id;
    private Long deptCode;
    private String username;
    private String nickName;
    private String gender;
    private String phone;
    private String email;
    private String avatarName;
    private String avatarPath;
    private String password;
    private String isAdmin;
    private Long enabled;
    private String createBy;
    private String updateBy;
    private Date pwdResetTime;
    private Date createTime;
    private Date updateTime;
    private String code;
    private String casId;
    private String idCard;

}
