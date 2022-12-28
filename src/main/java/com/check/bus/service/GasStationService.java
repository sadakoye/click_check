package com.check.bus.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.check.bus.pojo.GasStation;
import com.check.bus.pojo.dto.GasStationAddDto;
import com.check.bus.pojo.dto.GasStationDto;
import com.check.bus.pojo.dto.GasStationUpdateDto;
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
}
