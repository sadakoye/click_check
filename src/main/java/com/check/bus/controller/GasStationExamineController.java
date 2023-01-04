package com.check.bus.controller;

import com.check.bus.pojo.dto.GasStationExamineAddDto;
import com.check.bus.pojo.dto.GasStationExamineDto;
import com.check.bus.pojo.dto.GasStationExamineUpdateDto;
import com.check.bus.pojo.vo.GasStationExamineVo;
import com.check.bus.service.GasStationExamineService;
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
@RequestMapping("/gasStationExamine")
@RestController
@Api(tags = "加油站检查")
public class GasStationExamineController {

    @Resource
    GasStationExamineService service;

    /**
     * 列表查询
     *
     * @param dto dto
     * @return Result
     * @author zzc
     */
    @PreAuthorize("hasAnyAuthority('GasStationExamine:list') OR hasAnyAuthority('Admin')")
    @ApiOperation(value = "列表查询")
    @PostMapping("/list")
    public Result<PageInfo<GasStationExamineVo>> list(@RequestBody GasStationExamineDto dto) {
        return service.list(dto);
    }

    /**
     * 新增
     *
     * @param dto dto
     * @return Result
     * @author zzc
     */
    @PreAuthorize("hasAnyAuthority('GasStationExamine:add') OR hasAnyAuthority('Admin')")
    @ApiOperation(value = "新增")
    @PostMapping("/add")
    public Result<Object> add(@RequestBody @Valid GasStationExamineAddDto dto) {
        return service.add(dto);
    }

    /**
     * 修改
     *
     * @param dto dto
     * @return Result
     * @author zzc
     */
    @PreAuthorize("hasAnyAuthority('GasStationExamine:update') OR hasAnyAuthority('Admin')")
    @ApiOperation(value = "修改")
    @PostMapping("/update")
    public Result<Object> update(@RequestBody @Valid GasStationExamineUpdateDto dto) {
        return service.update(dto);
    }

    /**
     * 删除
     *
     * @param ids ids
     * @return Result
     * @author zzc
     */
    @PreAuthorize("hasAnyAuthority('GasStationExamine:delete') OR hasAnyAuthority('Admin')")
    @ApiOperation(value = "删除")
    @PostMapping("/delete")
    public Result<Object> delete(@RequestBody @NotNull @ApiParam("id集合") List<Long> ids) {
        return service.delete(ids);
    }
}
