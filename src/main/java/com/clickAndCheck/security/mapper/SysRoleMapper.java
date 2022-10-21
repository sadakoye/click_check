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
     * 根据用户id查询角色
     *
     * @param userId 用户id
     * @return List<RoleDto>
     * @author zzc
     */
    @Select("SELECT r.* FROM sys_role r LEFT JOIN sys_users_roles ur ON r.role_id = ur.role_id WHERE ur.user_id = #{userId}")
    List<RoleDto> getUserRoles(Long userId);
}
