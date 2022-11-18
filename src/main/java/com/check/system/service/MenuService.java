package com.check.system.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.check.common.pojo.bean.Result;
import com.check.system.pojo.SysMenu;
import com.check.system.pojo.dto.MenuAddDto;
import com.check.system.pojo.dto.MenuDto;
import com.check.system.pojo.dto.MenuUpdateDto;
import com.check.system.pojo.vo.MenuVo;
import com.github.pagehelper.PageInfo;

import java.util.List;

/**
 * @author zzc
 */
public interface MenuService extends IService<SysMenu> {

    /**
     * 列表查询
     *
     * @param dto dto
     * @return Result
     * @author zzc
     */
    Result<PageInfo<MenuVo>> list(MenuDto dto);

    /**
     * 新增
     *
     * @param dto dto
     * @return Result
     * @author zzc
     */
    Result<Object> add(MenuAddDto dto);

    /**
     * 修改
     *
     * @param dto dto
     * @return Result
     * @author zzc
     */
    Result<Object> update(MenuUpdateDto dto);

    /**
     * 删除
     *
     * @param ids ids
     * @return Result
     * @author zzc
     */
    Result<Object> delete(List<Long> ids);
}
