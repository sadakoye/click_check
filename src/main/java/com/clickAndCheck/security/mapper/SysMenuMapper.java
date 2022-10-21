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
     * 根据用户code查询菜单
     *
     * @param code 用户code
     * @return List<MenuDto>
     * @author zzc
     */
    @Select("SELECT * FROM SYS_MENU m LEFT JOIN sys_roles_menus rm ON m.code = rm.menu_code LEFT JOIN \n" +
            "sys_users_roles ur ON rm.role_code = ur.role_code WHERE ur.user_code = #{code}")
    List<MenuDto> getUserMenus(String code);
}
