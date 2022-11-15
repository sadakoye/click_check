package com.check.system.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.check.common.pojo.bean.Result;
import com.check.common.util.DataUtils;

import com.check.system.mapper.SysUserMapper;
import com.check.system.pojo.SysUser;
import com.check.system.pojo.dto.UserAddDto;
import com.check.system.pojo.dto.UserDto;
import com.check.system.pojo.dto.UserUpdateDto;
import com.check.system.pojo.vo.UserVo;
import com.check.system.service.UserService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author zzc
 */
@Service
public class UserServiceImpl extends ServiceImpl<SysUserMapper, SysUser> implements UserService {

    /**
     * 列表查询
     *
     * @param dto dto
     * @return Result
     * @author zzc
     */
    @Override
    public Result<PageInfo<UserVo>> list(UserDto dto) {
        SysUser user = new SysUser();
        BeanUtils.copyProperties(dto, user);
        QueryWrapper<SysUser> queryWrapper = DataUtils.query(user, dto);
        PageHelper.startPage(dto.getPageNum(), dto.getPageSize());

        List<SysUser> list = list(queryWrapper);

        UserVo userVo = new UserVo();

        PageInfo<UserVo> page = DataUtils.getPageInfo(list, userVo.getClass());

        return Result.success(page);
    }

    /**
     * 新增
     *
     * @param dto dto
     * @return Result
     * @author zzc
     */
    @Override
    public Result<Object> add(UserAddDto dto) {
        SysUser user = new SysUser();
        BeanUtils.copyProperties(dto, user);
        save(user);
        return Result.success();
    }

    /**
     * 修改
     *
     * @param dto dto
     * @return Result
     * @author zzc
     */
    @Override
    public Result<Object> update(UserUpdateDto dto) {
        SysUser user = new SysUser();
        BeanUtils.copyProperties(dto, user);
        updateById(user);
        return Result.success();
    }

    @Override
    public Result<Object> delete(List<Long> ids) {
        UpdateWrapper<SysUser> updateWrapper = new UpdateWrapper<>();
        updateWrapper.in("ID", ids);
        SysUser user = new SysUser();
        user.setEnabled(0L);
        update(user, updateWrapper);
        return Result.success();
    }
}
