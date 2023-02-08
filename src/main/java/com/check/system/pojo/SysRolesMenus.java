package com.check.system.pojo;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * @author zzc
 */
@Data
@TableName(value = "T_SYS_ROLES_MENUS")
public class SysRolesMenus {

    private long menuCode;
    private long roleCode;

}
