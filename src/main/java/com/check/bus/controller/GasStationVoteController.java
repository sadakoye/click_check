package com.check.bus.controller;

import com.check.bus.pojo.dto.GasStationVoteAddDto;
import com.check.bus.pojo.dto.GasStationVoteCountDto;
import com.check.bus.pojo.dto.GasStationVoteDto;
import com.check.bus.pojo.dto.GasStationVoteUpdateDto;
import com.check.bus.pojo.vo.GasStationVoteCountVo;
import com.check.bus.pojo.vo.GasStationVoteVo;
import com.check.bus.service.GasStationVoteService;
import com.check.common.pojo.bean.Result;
import com.github.pagehelper.PageInfo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * @author zzc
 */
@CrossOrigin
@RequestMapping("/gasStationVote")
@RestController
@Api(tags = "加油站投票")
public class GasStationVoteController {

    @Resource
    GasStationVoteService service;

    /**
     * 列表查询
     *
     * @param dto dto
     * @return Result
     * @author zzc
     */
    @PreAuthorize("hasAnyAuthority('GasStationVote:list') OR hasAnyAuthority('Admin')")
    @ApiOperation(value = "列表查询")
    @PostMapping("/list")
    public Result<PageInfo<GasStationVoteVo>> list(@RequestBody GasStationVoteDto dto) {
        return service.list(dto);
    }

    /**
     * 新增
     *
     * @param dto dto
     * @return Result
     * @author zzc
     */
    @PreAuthorize("hasAnyAuthority('GasStationVote:add') OR hasAnyAuthority('Admin')")
    @ApiOperation(value = "新增")
    @PostMapping("/add")
    public Result<Object> add(@RequestBody @Valid GasStationVoteAddDto dto) {
        return service.add(dto);
    }

    /**
     * 修改
     *
     * @param dto dto
     * @return Result
     * @author zzc
     */
    @PreAuthorize("hasAnyAuthority('GasStationVote:update') OR hasAnyAuthority('Admin')")
    @ApiOperation(value = "修改")
    @PostMapping("/update")
    public Result<Object> update(@RequestBody @Valid GasStationVoteUpdateDto dto) {
        return service.update(dto);
    }

    /**
     * 删除
     *
     * @param ids ids
     * @return Result
     * @author zzc
     */
    @PreAuthorize("hasAnyAuthority('GasStationVote:delete') OR hasAnyAuthority('Admin')")
    @ApiOperation(value = "删除")
    @PostMapping("/delete")
    public Result<Object> delete(@RequestBody @NotNull @ApiParam("id集合") List<Long> ids) {
        return service.delete(ids);
    }

    /**
     * 投票统计
     *
     * @param dto dto
     * @return Result
     * @author zzc
     */
    @PreAuthorize("hasAnyAuthority('GasStationVote:voteCount') OR hasAnyAuthority('Admin')")
    @ApiOperation(value = "投票统计")
    @PostMapping("/voteCount")
    public Result<PageInfo<GasStationVoteCountVo>> voteCount(@RequestBody GasStationVoteCountDto dto) {
        return service.voteCount(dto);
    }

    /**
     * 投票
     *
     * @param codeList 加油站code集合
     * @return Result
     * @author zzc
     */
    @PreAuthorize("hasAnyAuthority('GasStationVote:vote') OR hasAnyAuthority('Admin')")
    @ApiOperation(value = "投票")
    @PostMapping("/vote")
    public Result<Object> vote(@RequestBody @NotNull @ApiParam("加油站code集合") List<String> codeList) {
        return service.vote(codeList);
    }

    /**
     * 获取七天内已投票过的加油站code
     *
     * @return Result<List < String>>
     * @author zzc
     */
    @PreAuthorize("hasAnyAuthority('GasStationVote:getVote') OR hasAnyAuthority('Admin')")
    @ApiOperation(value = "获取七天内已投票过的加油站code")
    @PostMapping("/getVote")
    public Result<List<String>> getVote() {
        return service.getVote();
    }

}
