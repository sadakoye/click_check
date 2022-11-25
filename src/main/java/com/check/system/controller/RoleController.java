package com.check.system.controller;

import com.check.common.pojo.bean.Result;
import com.check.system.pojo.dto.RoleAddDto;
import com.check.system.pojo.dto.RoleDto;
import com.check.system.pojo.dto.RoleUpdateDto;
import com.check.system.pojo.dto.UsersRolesDto;
import com.check.system.pojo.vo.RoleVo;
import com.check.system.service.RoleService;
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
@RequestMapping("/role")
@RestController
@Api(tags = "菜单")
public class RoleController {

    @Resource
    RoleService service;

    /**
     * 列表查询
     *
     * @param dto dto
     * @return Result
     * @author zzc
     */
    @ApiOperation(value = "列表查询")
    @PostMapping("/list")
    public Result<PageInfo<RoleVo>> list(@RequestBody RoleDto dto) {
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
    public Result<Object> add(@RequestBody @Valid RoleAddDto dto) {
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
    public Result<Object> update(@RequestBody @Valid RoleUpdateDto dto) {
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
     * 根据用户查询角色
     *
     * @param userCode 用户code
     * @return Result
     * @author zzc
     */
    @ApiOperation(value = "根据用户查询角色")
    @PostMapping("/listByUserCode")
    public Result<List<RoleVo>> listByUserCode(@RequestBody @ApiParam("用户code") String userCode) {
        return service.listByUserCode(userCode);
    }

    /**
     * 用户新增角色
     *
     * @param dtoList UsersRolesDto
     * @return Result
     * @author zzc
     */
    @ApiOperation(value = "用户新增角色")
    @PostMapping("/addUserRole")
    public Result<Object> addUserRole(@RequestBody @Valid List<UsersRolesDto> dtoList) {
        return service.addUserRole(dtoList);
    }

    /**
     * 用户删除角色
     *
     * @param dtoList UsersRolesDto
     * @return Result
     * @author zzc
     */
    @ApiOperation(value = "用户删除角色")
    @PostMapping("/deleteUserRole")
    public Result<Object> deleteUserRole(@RequestBody @Valid List<UsersRolesDto> dtoList) {
        return service.deleteUserRole(dtoList);
    }
}
