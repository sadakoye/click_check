package com.check.system.pojo;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * @author zzc
 */
@Data
@TableName(value = "T_SYS_USERS_ROLES")
public class SysUsersRoles {

    private long userCode;
    private long roleCode;

}
