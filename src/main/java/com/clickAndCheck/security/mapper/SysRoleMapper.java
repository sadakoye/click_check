package com.clickAndCheck.security.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.clickAndCheck.security.pojo.SysRole;
import com.clickAndCheck.security.pojo.dto.RoleDto;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @author zzc
 */
@Mapper
public interface SysRoleMapper extends BaseMapper<SysRole> {


    /**
     * 根据用户code查询角色
     *
     * @param code 用户code
     * @return List<RoleDto>
     * @author zzc
     */
    @Select("SELECT r.* FROM sys_role r LEFT JOIN sys_users_roles ur ON r.code = ur.role_code WHERE ur.user_code = #{code}")
    List<RoleDto> getUserRoles(String code);
}
