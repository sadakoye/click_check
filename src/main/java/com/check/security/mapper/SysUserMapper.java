package com.check.security.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.check.security.config.User;
import com.check.security.pojo.SysUser;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

/**
 * @author zzc
 */
@Mapper
public interface SysUserMapper extends BaseMapper<SysUser> {


    /**
     * 根据名称查询
     *
     * @param username 用户名
     * @return user
     * @author zzc
     */
    @Select("SELECT * FROM SYS_USER WHERE USERNAME = #{username}")
    User getSecurityUser(String username);
}