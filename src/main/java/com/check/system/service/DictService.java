package com.check.system.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.check.system.pojo.SysDict;
import com.check.common.pojo.bean.Result;
import com.check.system.pojo.dto.DictAddDto;
import com.check.system.pojo.dto.DictDto;
import com.check.system.pojo.dto.DictUpdateDto;
import com.check.system.pojo.vo.DictVo;
import com.github.pagehelper.PageInfo;

import java.util.List;

/**
 * @author zzc
 */
public interface DictService extends IService<SysDict> {

    /**
     * 列表查询
     *
     * @param dto dto
     * @return Result
     * @author zzc
     */
    Result<PageInfo<DictVo>> list(DictDto dto);

    /**
     * 新增
     *
     * @param dto dto
     * @return Result
     * @author zzc
     */
    Result<Object> add(DictAddDto dto);

    /**
     * 修改
     *
     * @param dto dto
     * @return Result
     * @author zzc
     */
    Result<Object> update(DictUpdateDto dto);

    /**
     * 删除
     *
     * @param ids ids
     * @return Result
     * @author zzc
     */
    Result<Object> delete(List<Long> ids);
}
