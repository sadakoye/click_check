package com.check.bus.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.check.bus.pojo.GasStationVote;
import com.check.bus.pojo.dto.GasStationVoteAddDto;
import com.check.bus.pojo.dto.GasStationVoteCountDto;
import com.check.bus.pojo.dto.GasStationVoteDto;
import com.check.bus.pojo.dto.GasStationVoteUpdateDto;
import com.check.bus.pojo.vo.GasStationVoteCountVo;
import com.check.bus.pojo.vo.GasStationVoteVo;
import com.check.common.pojo.bean.Result;
import com.github.pagehelper.PageInfo;

import java.util.List;

/**
 * @author zzc
 */
public interface GasStationVoteService extends IService<GasStationVote> {

    /**
     * 列表查询
     *
     * @param dto dto
     * @return Result
     * @author zzc
     */
    Result<PageInfo<GasStationVoteVo>> list(GasStationVoteDto dto);

    /**
     * 新增
     *
     * @param dto dto
     * @return Result
     * @author zzc
     */
    Result<Object> add(GasStationVoteAddDto dto);

    /**
     * 修改
     *
     * @param dto dto
     * @return Result
     * @author zzc
     */
    Result<Object> update(GasStationVoteUpdateDto dto);

    /**
     * 删除
     *
     * @param ids ids
     * @return Result
     * @author zzc
     */
    Result<Object> delete(List<Long> ids);

    /**
     * 投票统计
     *
     * @param dto dto
     * @return Result
     * @author zzc
     */
    Result<PageInfo<GasStationVoteCountVo>> voteCount(GasStationVoteCountDto dto);

    /**
     * 投票
     *
     * @param codeList 加油站code集合
     * @return Result
     * @author zzc
     */
    Result<Object> vote(List<String> codeList);

    /**
     * 获取七天内已投票过的加油站code
     *
     * @return Result<List < String>>
     * @author zzc
     */
    Result<List<String>> getVote();
}
