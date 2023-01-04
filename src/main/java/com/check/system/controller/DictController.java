package com.check.system.controller;

import com.check.common.pojo.bean.Result;
import com.check.system.pojo.dto.DictAddDto;
import com.check.system.pojo.dto.DictDto;
import com.check.system.pojo.dto.DictUpdateDto;
import com.check.system.pojo.vo.DictVo;
import com.check.system.service.DictService;
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
@RequestMapping("/dict")
@RestController
@Api(tags = "字典")
public class DictController {

    @Resource
    DictService service;

    /**
     * 列表查询
     *
     * @param dto dto
     * @return Result
     * @author zzc
     */
    @PreAuthorize("hasAnyAuthority('Dict:list') OR hasAnyAuthority('Admin')")
    @ApiOperation(value = "列表查询")
    @PostMapping("/list")
    public Result<PageInfo<DictVo>> list(@RequestBody DictDto dto) {
        return service.list(dto);
    }

    /**
     * 新增
     *
     * @param dto dto
     * @return Result
     * @author zzc
     */
    @PreAuthorize("hasAnyAuthority('Dict:add') OR hasAnyAuthority('Admin')")
    @ApiOperation(value = "新增")
    @PostMapping("/add")
    public Result<Object> add(@RequestBody @Valid DictAddDto dto) {
        return service.add(dto);
    }

    /**
     * 修改
     *
     * @param dto dto
     * @return Result
     * @author zzc
     */
    @PreAuthorize("hasAnyAuthority('Dict:update') OR hasAnyAuthority('Admin')")
    @ApiOperation(value = "修改")
    @PostMapping("/update")
    public Result<Object> update(@RequestBody @Valid DictUpdateDto dto) {
        return service.update(dto);
    }

    /**
     * 删除
     *
     * @param ids ids
     * @return Result
     * @author zzc
     */
    @PreAuthorize("hasAnyAuthority('Dict:delete') OR hasAnyAuthority('Admin')")
    @ApiOperation(value = "删除")
    @PostMapping("/delete")
    public Result<Object> delete(@RequestBody @NotNull @ApiParam("id集合") List<Long> ids) {
        return service.delete(ids);
    }
}
