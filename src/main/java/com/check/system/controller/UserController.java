package com.check.system.controller;

import com.check.common.pojo.bean.Result;
import com.check.system.pojo.dto.UserAddDto;
import com.check.system.pojo.dto.UserDto;
import com.check.system.pojo.dto.UserUpdateDto;
import com.check.system.pojo.vo.UserVo;
import com.check.system.service.UserService;
import com.github.pagehelper.PageInfo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
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
    @PreAuthorize("hasAnyAuthority('User:list') OR hasAnyAuthority('Admin')")
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
    @PreAuthorize("hasAnyAuthority('User:add') OR hasAnyAuthority('Admin')")
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
    @PreAuthorize("hasAnyAuthority('User:update') OR hasAnyAuthority('Admin')")
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
    @PreAuthorize("hasAnyAuthority('User:delete') OR hasAnyAuthority('Admin')")
    @ApiOperation(value = "删除")
    @PostMapping("/delete")
    public Result<Object> delete(@RequestBody @NotNull @ApiParam("id集合") List<Long> ids) {
        return service.delete(ids);
    }

    /**
     * 获取单点用户信息
     *
     * @param response    HttpServletResponse
     * @param hidName     用户名
     * @param hidUserId   用户id
     * @param hidUserType 用户类型（1-企业；2-个人）
     * @param cardid      身份证号码
     * @param hidInfo     用户信息（base64 加密）
     * @return String
     * @author zzc
     */
    @ApiOperation(value = "获取单点用户信息")
    @RequestMapping(value = "/info")
    public Result<Object> info(HttpServletResponse response, String hidName, String hidUserId, String hidUserType, String cardid, String hidInfo) {
        return service.info(response, hidName, hidUserId, hidUserType, cardid, hidInfo);
    }
}
