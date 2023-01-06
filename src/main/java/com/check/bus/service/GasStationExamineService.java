package com.check.bus.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.check.bus.pojo.GasStationExamine;
import com.check.bus.pojo.dto.GasStationExamineAddDto;
import com.check.bus.pojo.dto.GasStationExamineDto;
import com.check.bus.pojo.dto.GasStationExamineUpdateDto;
import com.check.bus.pojo.vo.GasStationExamineVo;
import com.check.common.pojo.bean.Result;
import com.github.pagehelper.PageInfo;

import java.util.List;

/**
 * @author zzc
 */
public interface GasStationExamineService extends IService<GasStationExamine> {

    /**
     * 列表查询
     *
     * @param dto dto
     * @return Result
     * @author zzc
     */
    Result<PageInfo<GasStationExamineVo>> list(GasStationExamineDto dto);

    /**
     * 新增
     *
     * @param dto dto
     * @return Result
     * @author zzc
     */
    Result<Object> add(GasStationExamineAddDto dto);

    /**
     * 修改
     *
     * @param dto dto
     * @return Result
     * @author zzc
     */
    Result<Object> update(GasStationExamineUpdateDto dto);

    /**
     * 删除
     *
     * @param ids ids
     * @return Result
     * @author zzc
     */
    Result<Object> delete(List<Long> ids);

    /**
     * 批量新增
     *
     * @param dtoList dtoList
     * @return Result
     * @author zzc
     */
    Result<Object> addAll(List<GasStationExamineAddDto> dtoList);
}
