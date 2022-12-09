package com.check.bus.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.check.bus.mapper.GasStationMapper;
import com.check.bus.pojo.GasStation;
import com.check.bus.pojo.dto.GasStationAddDto;
import com.check.bus.pojo.dto.GasStationDto;
import com.check.bus.pojo.dto.GasStationUpdateDto;
import com.check.bus.pojo.vo.GasStationVo;
import com.check.bus.service.GasStationService;
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
public class GasStationServiceImpl extends ServiceImpl<GasStationMapper, GasStation> implements GasStationService {

    /**
     * 列表查询
     *
     * @param dto dto
     * @return Result
     * @author zzc
     */
    @Override
    public Result<PageInfo<GasStationVo>> list(GasStationDto dto) {
        GasStation bean = new GasStation();
        GasStationVo vo = new GasStationVo();
        BeanUtils.copyProperties(dto, bean);
        QueryWrapper<GasStation> queryWrapper = DataUtils.query(bean, dto, vo);
        PageHelper.startPage(dto.getPageNum(), dto.getPageSize());

        List<GasStation> list = list(queryWrapper);

        PageInfo<GasStationVo> page = DataUtils.getPageInfo(list, vo.getClass());

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
    public Result<Object> add(GasStationAddDto dto) {
        GasStation bean = new GasStation();
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
    public Result<Object> update(GasStationUpdateDto dto) {
        GasStation bean = new GasStation();
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
        UpdateWrapper<GasStation> updateWrapper = new UpdateWrapper<>();
        updateWrapper.in("ID", ids);
        GasStation bean = new GasStation();
        bean.setIsDelete("1");
        update(bean, updateWrapper);
        return Result.success();
    }
}
