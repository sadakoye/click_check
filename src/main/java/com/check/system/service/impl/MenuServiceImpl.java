package com.check.system.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.check.common.pojo.bean.Result;
import com.check.common.util.DataUtils;
import com.check.system.mapper.SysMenuMapper;
import com.check.system.mapper.SysRoleMenusMapper;
import com.check.system.pojo.SysMenu;
import com.check.system.pojo.SysRolesMenus;
import com.check.system.pojo.dto.MenuAddDto;
import com.check.system.pojo.dto.MenuDto;
import com.check.system.pojo.dto.MenuUpdateDto;
import com.check.system.pojo.dto.RolesMenusDto;
import com.check.system.pojo.vo.MenuVo;
import com.check.system.service.MenuService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.BeansException;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author zzc
 */
@Slf4j
@Service
public class MenuServiceImpl extends ServiceImpl<SysMenuMapper, SysMenu> implements MenuService {

    @Resource
    SysRoleMenusMapper roleMenusMapper;

    /**
     * 列表查询
     *
     * @param dto dto
     * @return Result
     * @author zzc
     */
    @Override
    public Result<PageInfo<MenuVo>> list(MenuDto dto) {
        SysMenu bean = new SysMenu();
        MenuVo vo = new MenuVo();
        BeanUtils.copyProperties(dto, bean);
        QueryWrapper<SysMenu> queryWrapper = DataUtils.query(bean, dto, vo);
        PageHelper.startPage(dto.getPageNum(), dto.getPageSize());

        List<SysMenu> list = list(queryWrapper);

        PageInfo<MenuVo> page = DataUtils.getPageInfo(list, vo.getClass());

        return Result.success(page);
    }

    /**
     * 新增
     *
     * @param dto dto
     * @return Result
     * @author zzc
     */
    @Override
    public Result<Object> add(MenuAddDto dto) {
        SysMenu bean = new SysMenu();
        BeanUtils.copyProperties(dto, bean);
        save(bean);
        return Result.success();
    }

    /**
     * 修改
     *
     * @param dto dto
     * @return Result
     * @author zzc
     */
    @Override
    public Result<Object> update(MenuUpdateDto dto) {
        SysMenu bean = new SysMenu();
        BeanUtils.copyProperties(dto, bean);
        updateById(bean);
        return Result.success();
    }

    /**
     * 删除
     *
     * @param ids ids
     * @return Result
     * @author zzc
     */
    @Override
    public Result<Object> delete(List<Long> ids) {
        UpdateWrapper<SysMenu> updateWrapper = new UpdateWrapper<>();
        updateWrapper.in("ID", ids);
        SysMenu bean = new SysMenu();
        bean.setIsDelete("1");
        update(bean, updateWrapper);
        return Result.success();
    }

    /**
     * 角色新增菜单
     *
     * @param dtoList List<RolesMenusDto>
     * @return Result
     * @author zzc
     */
    @Override
    public Result<Object> addRoleMenu(List<RolesMenusDto> dtoList) {
        if (dtoList != null && dtoList.size() > 0) {
            dtoList.forEach(rolesMenusDto -> {
                try {
                    SysRolesMenus rolesMenus = new SysRolesMenus();
                    BeanUtils.copyProperties(rolesMenusDto, rolesMenus);
                    roleMenusMapper.insert(rolesMenus);
                } catch (BeansException e) {
                    log.error("角色新增菜单失败" + rolesMenusDto);
                    e.printStackTrace();
                }
            });
        }
        return Result.success();
    }

    /**
     * 角色删除菜单
     *
     * @param dtoList List<RolesMenusDto>
     * @return Result
     * @author zzc
     */
    @Override
    public Result<Object> deleteRoleMenu(List<RolesMenusDto> dtoList) {
        if (dtoList != null && dtoList.size() > 0) {
            dtoList.forEach(rolesMenusDto -> {
                try {
                    LambdaQueryWrapper<SysRolesMenus> queryWrapper = new LambdaQueryWrapper<>();
                    queryWrapper.eq(SysRolesMenus::getMenuCode, rolesMenusDto.getMenuCode())
                            .eq(SysRolesMenus::getRoleCode, rolesMenusDto.getRoleCode());
                    roleMenusMapper.delete(queryWrapper);
                } catch (BeansException e) {
                    log.error("角色删除菜单失败" + rolesMenusDto);
                    e.printStackTrace();
                }
            });
        }
        return Result.success();
    }
}
