package com.check.bus.controller;

import com.check.bus.pojo.dto.GasStationAddDto;
import com.check.bus.pojo.dto.GasStationDto;
import com.check.bus.pojo.dto.GasStationUpdateDto;
import com.check.bus.pojo.vo.GasStationStatisticsVo;
import com.check.bus.pojo.vo.GasStationVo;
import com.check.bus.service.GasStationService;
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
@RequestMapping("/gasStation")
@RestController
@Api(tags = "加油站")
public class GasStationController {

    @Resource
    GasStationService service;

    /**
     * 列表查询
     *
     * @param dto dto
     * @return Result
     * @author zzc
     */
    @PreAuthorize("hasAnyAuthority('GasStation:list') OR hasAnyAuthority('Admin')")
    @ApiOperation(value = "列表查询")
    @PostMapping("/list")
    public Result<PageInfo<GasStationVo>> list(@RequestBody GasStationDto dto) {
        return service.list(dto);
    }

    /**
     * 新增
     *
     * @param dto dto
     * @return Result
     * @author zzc
     */
    @PreAuthorize("hasAnyAuthority('GasStation:add') OR hasAnyAuthority('Admin')")
    @ApiOperation(value = "新增")
    @PostMapping("/add")
    public Result<Object> add(@RequestBody @Valid GasStationAddDto dto) {
        return service.add(dto);
    }

    /**
     * 修改
     *
     * @param dto dto
     * @return Result
     * @author zzc
     */
    @PreAuthorize("hasAnyAuthority('GasStation:update') OR hasAnyAuthority('Admin')")
    @ApiOperation(value = "修改")
    @PostMapping("/update")
    public Result<Object> update(@RequestBody @Valid GasStationUpdateDto dto) {
        return service.update(dto);
    }

    /**
     * 删除
     *
     * @param ids ids
     * @return Result
     * @author zzc
     */
    @PreAuthorize("hasAnyAuthority('GasStation:delete') OR hasAnyAuthority('Admin')")
    @ApiOperation(value = "删除")
    @PostMapping("/delete")
    public Result<Object> delete(@RequestBody @NotNull @ApiParam("id集合") List<Long> ids) {
        return service.delete(ids);
    }

    /**
     * 保存选中状态
     *
     * @param ids ids
     * @return Result
     * @author zzc
     */
    @PreAuthorize("hasAnyAuthority('GasStation:pick') OR hasAnyAuthority('Admin')")
    @ApiOperation(value = "保存选中状态")
    @PostMapping("/pick")
    public Result<Object> pick(@RequestBody @NotNull @ApiParam("id集合") List<Long> ids) {
        return service.pick(ids);
    }

    /**
     * 删除选中状态
     *
     * @param ids ids
     * @return Result
     * @author zzc
     */
    @PreAuthorize("hasAnyAuthority('GasStation:pickOff') OR hasAnyAuthority('Admin')")
    @ApiOperation(value = "删除选中状态")
    @PostMapping("/pickOff")
    public Result<Object> pickOff(@RequestBody @NotNull @ApiParam("id集合") List<Long> ids) {
        return service.pickOff(ids);
    }

    /**
     * 获取选中状态
     *
     * @return Result<List<Long>>
     * @author zzc
     */
    @PreAuthorize("hasAnyAuthority('GasStation:getPick') OR hasAnyAuthority('Admin')")
    @ApiOperation(value = "获取选中状态")
    @PostMapping("/getPick")
    public Result<List<Long>> getPick() {
        return service.getPick();
    }

    /**
     * 更新选中状态
     *
     * @param ids ids
     * @return Result
     * @author zzc
     */
    @PreAuthorize("hasAnyAuthority('GasStation:updatePick') OR hasAnyAuthority('Admin')")
    @ApiOperation(value = "更新选中状态")
    @PostMapping("/updatePick")
    public Result<Object> updatePick(@RequestBody @NotNull @ApiParam("id集合") List<Long> ids) {
        return service.updatePick(ids);
    }

    /**
     * 批量新增
     *
     * @param dtoList dtoList
     * @return Result
     * @author zzc
     */
    @PreAuthorize("hasAnyAuthority('GasStation:addAll') OR hasAnyAuthority('Admin')")
    @ApiOperation(value = "批量新增")
    @PostMapping("/addAll")
    public Result<Object> addAll(@RequestBody @Valid List<GasStationAddDto> dtoList) {
        return service.addAll(dtoList);
    }


    /**
     * 加油站品牌数量统计
     *
     * @param code 区code
     * @return Result
     * @author zzc
     */
    @PreAuthorize("hasAnyAuthority('GasStation:statistics') OR hasAnyAuthority('Admin')")
    @ApiOperation(value = "加油站品牌数量统计")
    @PostMapping("/statistics")
    public Result<List<GasStationStatisticsVo>> statistics(@RequestBody @ApiParam("地区code, 传00查全部") String code) {
        return service.statistics(code);
    }
}
