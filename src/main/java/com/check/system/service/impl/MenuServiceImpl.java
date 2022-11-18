package com.check.system.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.check.common.pojo.bean.Result;
import com.check.common.util.DataUtils;
import com.check.system.mapper.SysMenuMapper;
import com.check.system.pojo.SysMenu;
import com.check.system.pojo.dto.MenuAddDto;
import com.check.system.pojo.dto.MenuDto;
import com.check.system.pojo.dto.MenuUpdateDto;
import com.check.system.pojo.vo.MenuVo;
import com.check.system.service.MenuService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author zzc
 */
@Service
public class MenuServiceImpl extends ServiceImpl<SysMenuMapper, SysMenu> implements MenuService {

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
}
