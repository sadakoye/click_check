package com.check.system.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.check.security.pojo.bean.User;
import com.check.system.pojo.SysUser;
import com.check.system.pojo.SysUsersRoles;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

/**
 * @author zzc
 */
@Mapper
public interface SysUserRolesMapper extends BaseMapper<SysUsersRoles> {

}
