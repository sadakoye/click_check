package com.check.bus.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.check.bus.mapper.GasStationExamineMapper;
import com.check.bus.pojo.GasStationExamine;
import com.check.bus.pojo.dto.GasStationExamineAddDto;
import com.check.bus.pojo.dto.GasStationExamineDto;
import com.check.bus.pojo.dto.GasStationExamineUpdateDto;
import com.check.bus.pojo.vo.GasStationExamineVo;
import com.check.bus.service.GasStationExamineService;
import com.check.common.pojo.bean.Result;
import com.check.common.util.DataUtils;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author zzc
 */
@Service
public class GasStationExamineServiceImpl extends ServiceImpl<GasStationExamineMapper, GasStationExamine> implements GasStationExamineService {

    /**
     * 列表查询
     *
     * @param dto dto
     * @return Result
     * @author zzc
     */
    @Override
    public Result<PageInfo<GasStationExamineVo>> list(GasStationExamineDto dto) {
        GasStationExamine bean = new GasStationExamine();
        GasStationExamineVo vo = new GasStationExamineVo();
        BeanUtils.copyProperties(dto, bean);
        QueryWrapper<GasStationExamine> queryWrapper = DataUtils.query(bean, dto, vo);
        PageHelper.startPage(dto.getPageNum(), dto.getPageSize());

        List<GasStationExamine> list = list(queryWrapper);

        PageInfo<GasStationExamineVo> page = DataUtils.getPageInfo(list, vo.getClass());

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
    public Result<Object> add(GasStationExamineAddDto dto) {
        GasStationExamine bean = new GasStationExamine();
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
    public Result<Object> update(GasStationExamineUpdateDto dto) {
        GasStationExamine bean = new GasStationExamine();
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
        UpdateWrapper<GasStationExamine> updateWrapper = new UpdateWrapper<>();
        updateWrapper.in("ID", ids);
        GasStationExamine bean = new GasStationExamine();
        bean.setIsDelete("1");
        update(bean, updateWrapper);
        return Result.success();
    }

    /**
     * 批量新增
     *
     * @param dtoList dtoList
     * @return Result
     * @author zzc
     */
    @Override
    public Result<Object> addAll(List<GasStationExamineAddDto> dtoList) {
        dtoList.parallelStream()
                .map(gasStationExamineAddDto -> {
                    GasStationExamine gasStationExamine = new GasStationExamine();
                    BeanUtils.copyProperties(gasStationExamineAddDto, gasStationExamine);
                    return gasStationExamine;
                })
                .forEach(this::save);
        return Result.success();
    }
}
