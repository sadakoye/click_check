package com.check.system.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.check.common.pojo.bean.Result;
import com.check.system.pojo.SysRole;
import com.check.system.pojo.dto.RoleAddDto;
import com.check.system.pojo.dto.RoleDto;
import com.check.system.pojo.dto.RoleUpdateDto;
import com.check.system.pojo.vo.RoleVo;
import com.github.pagehelper.PageInfo;

import java.util.List;

/**
 * @author zzc
 */
public interface RoleService extends IService<SysRole> {

    /**
     * 列表查询
     *
     * @param dto dto
     * @return Result
     * @author zzc
     */
    Result<PageInfo<RoleVo>> list(RoleDto dto);

    /**
     * 新增
     *
     * @param dto dto
     * @return Result
     * @author zzc
     */
    Result<Object> add(RoleAddDto dto);

    /**
     * 修改
     *
     * @param dto dto
     * @return Result
     * @author zzc
     */
    Result<Object> update(RoleUpdateDto dto);

    /**
     * 删除
     *
     * @param ids ids
     * @return Result
     * @author zzc
     */
    Result<Object> delete(List<Long> ids);

    /**
     * 根据用户查询角色
     *
     * @param userCode 用户code
     * @return Result
     * @author zzc
     */
    Result<List<RoleVo>> listByUserCode(String userCode);
}
