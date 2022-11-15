package com.check.system.controller;

import com.check.common.pojo.bean.Result;
import com.check.system.pojo.dto.UserAddDto;
import com.check.system.pojo.dto.UserUpdateDto;
import com.check.system.pojo.dto.UserDto;
import com.check.system.pojo.vo.UserVo;
import com.check.system.service.UserService;
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
@RequestMapping("/user")
@RestController
@Api(tags = "用户")
public class UserController {

    @Resource
    UserService service;

    /**
     * 列表查询
     *
     * @param dto dto
     * @return Result
     * @author zzc
     */
    @ApiOperation(value = "列表查询")
    @PostMapping("/list")
    public Result<PageInfo<UserVo>> list(@RequestBody UserDto dto) {
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
    public Result<Object> add(@RequestBody @Valid UserAddDto dto) {
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
    public Result<Object> update(@RequestBody @Valid UserUpdateDto dto) {
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
}
