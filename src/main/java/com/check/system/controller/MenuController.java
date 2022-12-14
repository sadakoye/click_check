package com.check.system.controller;

import com.check.common.pojo.bean.Result;
import com.check.system.pojo.dto.MenuAddDto;
import com.check.system.pojo.dto.MenuDto;
import com.check.system.pojo.dto.MenuUpdateDto;
import com.check.system.pojo.dto.RolesMenusDto;
import com.check.system.pojo.vo.MenuVo;
import com.check.system.service.MenuService;
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
@RequestMapping("/menu")
@RestController
@Api(tags = "菜单")
public class MenuController {

    @Resource
    MenuService service;

    /**
     * 列表查询
     *
     * @param dto dto
     * @return Result
     * @author zzc
     */
    @PreAuthorize("hasAnyAuthority('Menu:list') OR hasAnyAuthority('Admin')")
    @ApiOperation(value = "列表查询")
    @PostMapping("/list")
    public Result<PageInfo<MenuVo>> list(@RequestBody MenuDto dto) {
        return service.list(dto);
    }

    /**
     * 新增
     *
     * @param dto dto
     * @return Result
     * @author zzc
     */
    @PreAuthorize("hasAnyAuthority('Menu:add') OR hasAnyAuthority('Admin')")
    @ApiOperation(value = "新增")
    @PostMapping("/add")
    public Result<Object> add(@RequestBody @Valid MenuAddDto dto) {
        return service.add(dto);
    }

    /**
     * 修改
     *
     * @param dto dto
     * @return Result
     * @author zzc
     */
    @PreAuthorize("hasAnyAuthority('Menu:update') OR hasAnyAuthority('Admin')")
    @ApiOperation(value = "修改")
    @PostMapping("/update")
    public Result<Object> update(@RequestBody @Valid MenuUpdateDto dto) {
        return service.update(dto);
    }

    /**
     * 删除
     *
     * @param ids ids
     * @return Result
     * @author zzc
     */
    @PreAuthorize("hasAnyAuthority('Menu:delete') OR hasAnyAuthority('Admin')")
    @ApiOperation(value = "删除")
    @PostMapping("/delete")
    public Result<Object> delete(@RequestBody @NotNull @ApiParam("id集合") List<Long> ids) {
        return service.delete(ids);
    }

    /**
     * 角色新增菜单
     *
     * @param dtoList List<RolesMenusDto>
     * @return Result
     * @author zzc
     */
    @PreAuthorize("hasAnyAuthority('Menu:addRoleMenu') OR hasAnyAuthority('Admin')")
    @ApiOperation(value = "角色新增菜单")
    @PostMapping("/addRoleMenu")
    public Result<Object> addRoleMenu(@RequestBody @Valid List<RolesMenusDto> dtoList) {
        return service.addRoleMenu(dtoList);
    }

    /**
     * 角色删除菜单
     *
     * @param dtoList List<RolesMenusDto>
     * @return Result
     * @author zzc
     */
    @PreAuthorize("hasAnyAuthority('Menu:deleteRoleMenu') OR hasAnyAuthority('Admin')")
    @ApiOperation(value = "角色删除菜单")
    @PostMapping("/deleteRoleMenu")
    public Result<Object> deleteRoleMenu(@RequestBody @Valid List<RolesMenusDto> dtoList) {
        return service.deleteRoleMenu(dtoList);
    }
}
