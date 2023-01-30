package com.check.bus.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.check.bus.mapper.GasStationMapper;
import com.check.bus.mapper.GasStationVoteMapper;
import com.check.bus.pojo.GasStation;
import com.check.bus.pojo.GasStationVote;
import com.check.bus.pojo.dto.GasStationVoteAddDto;
import com.check.bus.pojo.dto.GasStationVoteCountDto;
import com.check.bus.pojo.dto.GasStationVoteDto;
import com.check.bus.pojo.dto.GasStationVoteUpdateDto;
import com.check.bus.pojo.vo.GasStationVoteCountVo;
import com.check.bus.pojo.vo.GasStationVoteVo;
import com.check.bus.service.GasStationVoteService;
import com.check.common.constant.ConstantException;
import com.check.common.constant.ConstantInteger;
import com.check.common.pojo.bean.Result;
import com.check.common.util.DataUtils;
import com.check.common.util.RequestUtils;
import com.check.security.pojo.bean.User;
import com.check.security.utils.JwtUtils;
import com.check.system.mapper.SysUserMapper;
import com.check.system.pojo.SysUser;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.*;

/**
 * @author zzc
 */
@Service
public class GasStationVoteServiceImpl extends ServiceImpl<GasStationVoteMapper, GasStationVote> implements GasStationVoteService {

    @Resource
    GasStationVoteMapper gasStationVoteMapper;

    @Resource
    GasStationMapper gasStationMapper;

    @Resource
    SysUserMapper sysUserMapper;

    @Resource
    JwtUtils jwtUtils;

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
        String ip = RequestUtils.getIp();
        if (ip != null) {
            bean.setVoterIp(ip);
        }
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
        if (codeList.size() > ConstantInteger.THREE) {
            throw ConstantException.GAS_STATION_VOTE_COUNT_GT_THREE;
        }

        User user = jwtUtils.getUser();
        SysUser sysUser = sysUserMapper.selectById(user.getId());
        Date date = new Date();
        Date oldDate = new Date(System.currentTimeMillis() - 7 * 24 * 60 * 60 * 1000);
        List<GasStationVote> oldGasStationVoteList = getGasStationVotes(sysUser.getIdCard(), oldDate);

        oldGasStationVoteList.forEach(gasStationVote -> {
            List<String> deleteList = new LinkedList<>();
            codeList.forEach(s -> {
                if (gasStationVote.getGasStationCode().equals(s)) {
                    deleteList.add(s);
                }
                codeList.removeAll(deleteList);
            });
        });

        if (oldGasStationVoteList.size() + codeList.size() > ConstantInteger.THREE) {
            throw ConstantException.GAS_STATION_VOTE_COUNT_GT_THREE;
        }

        LambdaQueryWrapper<GasStation> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.in(GasStation::getCode, codeList);
        List<GasStation> gasStationList = gasStationMapper.selectList(queryWrapper);
        Map<String, GasStation> gasMap = new LinkedHashMap<>();
        gasStationList.forEach(gasStation -> {
            gasMap.put(gasStation.getCode(), gasStation);
        });

        codeList.parallelStream()
                .filter(gasMap::containsKey)
                .map(s -> {
                    GasStation gasStation = gasMap.get(s);
                    return GasStationVote.builder()
                            .districtCode(gasStation.getDistrictCode())
                            .gasStationName(gasStation.getContactsName())
                            .voterName(sysUser.getNickName()).voterPhone(sysUser.getPhone())
                            .voterIp(RequestUtils.getIp()).voteTime(date).gasStationCode(s)
                            .gasStationName(gasStation.getName())
                            .build();
                })
                .forEach(this::save);
        return Result.success();
    }

    /**
     * 获取七天内已投票过的加油站code
     *
     * @return Result<List < String>>
     * @author zzc
     */
    @Override
    public Result<List<String>> getVote() {
        User user = jwtUtils.getUser();
        Date oldDate = new Date(System.currentTimeMillis() - 7 * 24 * 60 * 60 * 1000);
        List<GasStationVote> gasStationVoteList = getGasStationVotes(user.getIdCard(), oldDate);
        List<String> codeList = new LinkedList<>();
        gasStationVoteList.forEach(gasStationVote -> {
            codeList.add(gasStationVote.getGasStationCode());
        });
        return Result.success(codeList);
    }

    /**
     * 根据时间与身份证号码查询投票列表
     *
     * @param idCard 身份证号码
     * @param date   时间，查询此时间之后
     * @return List<GasStationVote>
     * @author zzc
     */
    private List<GasStationVote> getGasStationVotes(String idCard, Date date) {
        LambdaQueryWrapper<GasStationVote> voteQueryWrapper = new LambdaQueryWrapper<>();
        voteQueryWrapper.ge(GasStationVote::getVoteTime, date)
                .eq(GasStationVote::getVoterIdCard, idCard);
        return gasStationVoteMapper.selectList(voteQueryWrapper);
    }
}
