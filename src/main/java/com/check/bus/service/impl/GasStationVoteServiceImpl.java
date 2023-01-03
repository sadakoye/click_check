package com.check.bus.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.check.bus.mapper.GasStationVoteMapper;
import com.check.bus.pojo.GasStationVote;
import com.check.bus.pojo.dto.GasStationVoteAddDto;
import com.check.bus.pojo.dto.GasStationVoteCountDto;
import com.check.bus.pojo.dto.GasStationVoteDto;
import com.check.bus.pojo.dto.GasStationVoteUpdateDto;
import com.check.bus.pojo.vo.GasStationVoteCountVo;
import com.check.bus.pojo.vo.GasStationVoteVo;
import com.check.bus.service.GasStationVoteService;
import com.check.common.pojo.bean.Result;
import com.check.common.util.DataUtils;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author zzc
 */
@Service
public class GasStationVoteServiceImpl extends ServiceImpl<GasStationVoteMapper, GasStationVote> implements GasStationVoteService {

    @Resource
    GasStationVoteMapper gasStationVoteMapper;

    /**
     * 列表查询
     *
     * @param dto dto
     * @return Result
     * @author zzc
     */
    @Override
    public Result<PageInfo<GasStationVoteVo>> list(GasStationVoteDto dto) {
        GasStationVote bean = new GasStationVote();
        GasStationVoteVo vo = new GasStationVoteVo();
        BeanUtils.copyProperties(dto, bean);
        QueryWrapper<GasStationVote> queryWrapper = DataUtils.query(bean, dto, vo);
        PageHelper.startPage(dto.getPageNum(), dto.getPageSize());

        List<GasStationVote> list = list(queryWrapper);

        PageInfo<GasStationVoteVo> page = DataUtils.getPageInfo(list, vo.getClass());

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
    public Result<Object> add(GasStationVoteAddDto dto) {
        GasStationVote bean = new GasStationVote();
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
    public Result<Object> update(GasStationVoteUpdateDto dto) {
        GasStationVote bean = new GasStationVote();
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
        UpdateWrapper<GasStationVote> updateWrapper = new UpdateWrapper<>();
        updateWrapper.in("ID", ids);
        GasStationVote bean = new GasStationVote();
        bean.setIsDelete("1");
        update(bean, updateWrapper);
        return Result.success();
    }

    /**
     * 投票统计
     *
     * @param dto dto
     * @return Result
     * @author zzc
     */
    @Override
    public Result<PageInfo<GasStationVoteCountVo>> voteCount(GasStationVoteCountDto dto) {
        PageHelper.startPage(dto.getPageNum(), dto.getPageSize());
        dto.setDistrictCode("%" + dto.getDistrictCode() + "%");
        dto.setName("%" + dto.getName() + "%");
        dto.setAddress("%" + dto.getAddress() + "%");

        List<GasStationVoteCountVo> list = gasStationVoteMapper.voteCount(
                dto.getDistrictCode(), dto.getName(), dto.getAddress(), dto.getStartTime(), dto.getEndTime()
        );

        GasStationVoteCountVo vo = new GasStationVoteCountVo();
        PageInfo<GasStationVoteCountVo> page = DataUtils.getPageInfo(list, vo.getClass());

        return Result.success(page);
    }

    /**
     * 投票
     *
     * @param codeList 加油站code集合
     * @return Result
     * @author zzc
     */
    @Override
    public Result<Object> vote(List<String> codeList) {
        return null;
    }
}
