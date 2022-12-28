package com.check.bus.controller;

import com.check.bus.pojo.dto.GasStationAddDto;
import com.check.bus.pojo.dto.GasStationDto;
import com.check.bus.pojo.dto.GasStationUpdateDto;
import com.check.bus.pojo.vo.GasStationVo;
import com.check.bus.service.GasStationService;
import com.check.common.pojo.bean.Result;
import com.github.pagehelper.PageInfo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
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
    @ApiOperation(value = "保存选中状态")
    @PostMapping("/pick")
    public Result<Object> pick(@RequestBody @NotNull @ApiParam("id集合") List<Long> ids) {
        return service.pick(ids);
    }
}
