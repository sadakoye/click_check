package com.check.bus.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.check.bus.mapper.GasStationMapper;
import com.check.bus.pojo.GasStation;
import com.check.bus.pojo.dto.DistrictDto;
import com.check.bus.pojo.dto.GasStationAddDto;
import com.check.bus.pojo.dto.GasStationDto;
import com.check.bus.pojo.dto.GasStationUpdateDto;
import com.check.bus.pojo.vo.GasStationExamineStatisticsVo;
import com.check.bus.pojo.vo.GasStationStatisticsVo;
import com.check.bus.pojo.vo.GasStationVo;
import com.check.bus.service.GasStationService;
import com.check.common.constant.ConstantException;
import com.check.common.constant.ConstantString;
import com.check.common.pojo.bean.Result;
import com.check.common.util.DataUtils;
import com.check.common.util.RedisUtils;
import com.check.security.config.JwtProperties;
import com.check.security.pojo.bean.User;
import com.check.security.utils.JwtUtils;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

/**
 * @author zzc
 */
@Service
public class GasStationServiceImpl extends ServiceImpl<GasStationMapper, GasStation> implements GasStationService {

    @Resource
    private JwtUtils jwtUtils;

    @Resource
    private JwtProperties jwtProperties;

    @Resource
    private GasStationMapper gasStationMapper;

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
        if (StringUtils.isNotBlank(dto.getDistrictCode())){
            queryWrapper.and(i -> i.eq("DISTRICT_CODE", dto.getDistrictCode()));
        }
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
        LambdaQueryWrapper<GasStation> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(GasStation::getCode, dto.getCode());
        List<GasStation> list = list(queryWrapper);
        if (list.size() > 0) {
            throw ConstantException.GAS_STATION_RE_CODE;
        }
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
        LambdaQueryWrapper<GasStation> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(GasStation::getCode, dto.getCode());
        List<GasStation> list = list(queryWrapper);
        if (list.size() > 0) {
            int error = 1;
            for (GasStation gasStation : list) {
                if (gasStation.getId().equals(dto.getId())) {
                    error = 0;
                    break;
                }
            }
            if (error == 1) {
                throw ConstantException.GAS_STATION_RE_CODE;
            }
        }
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
        LambdaQueryWrapper<GasStation> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.in(GasStation::getId, ids);
        List<GasStation> list = list(queryWrapper);
        for (GasStation gasStation : list) {
            gasStation.setCode("delete-" + gasStation.getId() + "-" + gasStation.getCode());
            gasStation.setIsDelete("1");
            updateById(gasStation);
        }
        return Result.success();
    }

    /**
     * 保存选中状态
     *
     * @param ids ids
     * @return Result
     * @author zzc
     */
    @Override
    public Result<Object> pick(List<Long> ids) {
        if (ids.size() > 0) {
            User user = jwtUtils.getUser();
            String userName = user.getUsername();
            List<Long> idList;
            if (RedisUtils.contain(ConstantString.REDIS_PICK + userName)) {
                Object value = RedisUtils.getValue(ConstantString.REDIS_PICK + userName);
                idList = DataUtils.castList(value, Long.class);
                idList.addAll(ids);
            } else {
                idList = new LinkedList<>(ids);
            }
            RedisUtils.saveValue(ConstantString.REDIS_PICK + userName, idList,
                    jwtProperties.getTokenValidityInSeconds(), TimeUnit.MINUTES);
            return Result.success();
        } else {
            throw ConstantException.GAS_STATION_ID_NULL;
        }
    }

    /**
     * 删除选中状态
     *
     * @param ids ids
     * @return Result
     * @author zzc
     */
    @Override
    public Result<Object> pickOff(List<Long> ids) {
        User user = jwtUtils.getUser();
        String userName = user.getUsername();
        if (RedisUtils.contain(ConstantString.REDIS_PICK + userName)) {
            Object value = RedisUtils.getValue(ConstantString.REDIS_PICK + userName);
            List<Long> idList = DataUtils.castList(value, Long.class);
            idList.removeAll(ids);
            RedisUtils.saveValue(ConstantString.REDIS_PICK + userName, idList,
                    jwtProperties.getTokenValidityInSeconds(), TimeUnit.MINUTES);
        }
        return Result.success();
    }

    /**
     * 获取选中状态
     *
     * @return Result<List < Long>>
     * @author zzc
     */
    @Override
    public Result<List<Long>> getPick() {
        User user = jwtUtils.getUser();
        String userName = user.getUsername();
        List<Long> idList = new LinkedList<>();
        if (RedisUtils.contain(ConstantString.REDIS_PICK + userName)) {
            Object value = RedisUtils.getValue(ConstantString.REDIS_PICK + userName);
            idList = DataUtils.castList(value, Long.class);
        }
        return Result.success(idList);
    }

    /**
     * 更新选中状态
     *
     * @param ids ids
     * @return Result
     * @author zzc
     */
    @Override
    public Result<Object> updatePick(List<Long> ids) {
        User user = jwtUtils.getUser();
        String userName = user.getUsername();
        List<Long> idList = new LinkedList<>(ids);
        RedisUtils.saveValue(ConstantString.REDIS_PICK + userName, idList,
                jwtProperties.getTokenValidityInSeconds(), TimeUnit.MINUTES);
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
    public Result<Object> addAll(List<GasStationAddDto> dtoList) {
        Set<String> codeSet = gasStationMapper.getCodeSet();
        dtoList.parallelStream()
                .filter(gasStationAddDto -> !codeSet.contains(gasStationAddDto.getCode()))
                .map(gasStationAddDto -> {
                    GasStation gasStation = new GasStation();
                    BeanUtils.copyProperties(gasStationAddDto, gasStation);
                    return gasStation;
                })
                .forEach(this::save);
        return Result.success();
    }

    /**
     * 加油站统计
     *
     * @param dto 区code
     * @return Result
     * @author zzc
     */
    @Override
    public Result<List<GasStationStatisticsVo>> statistics(DistrictDto dto) {
        String code = dto.getCode();
        if (ConstantString.ZERO_ZERO.equals(code)) {
            code = null;
        }
        List<GasStationStatisticsVo> gasStationStatisticsVoList = gasStationMapper.statistics(code);
        return Result.success(gasStationStatisticsVoList);
    }

    /**
     * 加油站检查状态统计
     *
     * @param dto 区code
     * @return Result
     * @author zzc
     */
    @Override
    public Result<List<GasStationExamineStatisticsVo>> examineStatistics(DistrictDto dto) {
        String code = dto.getCode();
        if (ConstantString.ZERO_ZERO.equals(code)) {
            code = null;
        }
        Integer notInt = gasStationMapper.examineStatistics(code);
        List<GasStationExamineStatisticsVo> list = new ArrayList<>(2);
        GasStationExamineStatisticsVo not = GasStationExamineStatisticsVo.builder()
                .examine("0").count(notInt)
                .build();
        list.add(not);
        LambdaQueryWrapper<GasStation> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(GasStation::getIsDelete, "0");
        if (code != null) {
            queryWrapper.eq(GasStation::getDistrictCode, code);
        }
        Integer integer = gasStationMapper.selectCount(queryWrapper);
        GasStationExamineStatisticsVo is = GasStationExamineStatisticsVo.builder()
                .examine("1").count(integer - notInt)
                .build();
        list.add(is);
        return Result.success(list);
    }
}
