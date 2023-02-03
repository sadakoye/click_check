package com.check.bus.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.check.bus.pojo.GasStation;
import com.check.bus.pojo.dto.DistrictDto;
import com.check.bus.pojo.dto.GasStationAddDto;
import com.check.bus.pojo.dto.GasStationDto;
import com.check.bus.pojo.dto.GasStationUpdateDto;
import com.check.bus.pojo.vo.GasStationExamineStatisticsVo;
import com.check.bus.pojo.vo.GasStationStatisticsVo;
import com.check.bus.pojo.vo.GasStationVo;
import com.check.common.pojo.bean.Result;
import com.github.pagehelper.PageInfo;

import java.util.List;

/**
 * @author zzc
 */
public interface GasStationService extends IService<GasStation> {

    /**
     * 列表查询
     *
     * @param dto dto
     * @return Result
     * @author zzc
     */
    Result<PageInfo<GasStationVo>> list(GasStationDto dto);

    /**
     * 新增
     *
     * @param dto dto
     * @return Result
     * @author zzc
     */
    Result<Object> add(GasStationAddDto dto);

    /**
     * 修改
     *
     * @param dto dto
     * @return Result
     * @author zzc
     */
    Result<Object> update(GasStationUpdateDto dto);

    /**
     * 删除
     *
     * @param ids ids
     * @return Result
     * @author zzc
     */
    Result<Object> delete(List<Long> ids);

    /**
     * 保存选中状态
     *
     * @param ids ids
     * @return Result
     * @author zzc
     */
    Result<Object> pick(List<Long> ids);

    /**
     * 删除选中状态
     *
     * @param ids ids
     * @return Result
     * @author zzc
     */
    Result<Object> pickOff(List<Long> ids);

    /**
     * 获取选中状态
     *
     * @return Result<List < Long>>
     * @author zzc
     */
    Result<List<Long>> getPick();

    /**
     * 更新选中状态
     *
     * @param ids ids
     * @return Result
     * @author zzc
     */
    Result<Object> updatePick(List<Long> ids);

    /**
     * 批量新增
     *
     * @param dtoList dtoList
     * @return Result
     * @author zzc
     */
    Result<Object> addAll(List<GasStationAddDto> dtoList);

    /**
     * 加油站统计
     *
     * @param dto 区code
     * @return Result
     * @author zzc
     */
    Result<List<GasStationStatisticsVo>> statistics(DistrictDto dto);

    /**
     * 加油站检查状态统计
     *
     * @param dto 区code
     * @return Result
     * @author zzc
     */
    Result<List<GasStationExamineStatisticsVo>> examineStatistics(DistrictDto dto);
}
