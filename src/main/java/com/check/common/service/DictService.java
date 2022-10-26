package com.check.common.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.check.common.pojo.SysDict;
import com.check.common.pojo.bean.Result;
import com.check.common.pojo.dto.DictAddDto;
import com.check.common.pojo.dto.DictDto;
import com.check.common.pojo.dto.DictUpdateDto;

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
    Result list(DictDto dto);

    /**
     * 新增
     *
     * @param dto dto
     * @return Result
     * @author zzc
     */
    Result add(DictAddDto dto);

    /**
     * 修改
     *
     * @param dto dto
     * @return Result
     * @author zzc
     */
    Result update(DictUpdateDto dto);

    /**
     * 删除
     *
     * @param ids ids
     * @return Result
     * @author zzc
     */
    Result delete(List<Long> ids);
}
