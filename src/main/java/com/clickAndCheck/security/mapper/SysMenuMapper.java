package com.clickAndCheck.security.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.clickAndCheck.security.pojo.SysMenu;
import com.clickAndCheck.security.pojo.dto.MenuDto;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @author zzc
 */
@Mapper
public interface SysMenuMapper extends BaseMapper<SysMenu> {


    /**
     * 根据用户id查询菜单
     *
     * @param userId 用户id
     * @return List<MenuDto>
     * @author zzc
     */
    @Select("SELECT * FROM SYS_MENU m LEFT JOIN sys_roles_menus rm ON m.menu_id = rm.menu_id LEFT JOIN \n" +
            "sys_users_roles ur ON rm.role_id = ur.role_id WHERE ur.user_id = #{userId}")
    List<MenuDto> getUserMenus(Long userId);
}
